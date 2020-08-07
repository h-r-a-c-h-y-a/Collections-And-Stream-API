package util.concurrent.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class LockDemo {
    private static int count = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new LockedThread("first", lock);
        new LockedThread("second", lock);
    }

    static class LockedThread implements Runnable {
        String name;
        ReentrantLock lock;

        public LockedThread(String name, ReentrantLock lock) {
            this.name = name;
            this.lock = lock;
            new Thread(this).start();
        }

        @Override
        public void run() {
            System.out.printf("Thread %s start%n", name);
            System.out.printf("Thread %s waiting for block counter%n", name);
            for (int i = 0; i < 10; i++) {
                lock.lock();
                count++;
                System.out.printf("Thread %s shared count: %d%n", name, count);
                lock.unlock();
            }
            System.out.printf("Thread %s unlock counter%n", name);
        }
    }
}


class CounterST {
    int c = 0;

    public static void main(String[] args) {
        StampedLock sl = new StampedLock();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CounterST cst = new CounterST();
        Runnable readTask = () -> {
            for (int i = 0; i < 5; i++) {
                long stamp = sl.readLock();
                try {
                    System.out.println("value " + cst.getValue());

                } finally {
                    sl.unlockRead(stamp);
                }
            }
        };

        Runnable writeTask = () -> {
            for (int i = 0; i < 10; i++) {
                long stamp = sl.writeLock();
                try {
                    cst.increment();
                } finally {
                    sl.unlockWrite(stamp);
                }
            }
        };

        executor.execute(writeTask);
        executor.execute(writeTask);
        executor.execute(writeTask);

        executor.execute(readTask);
        executor.shutdown();

    }

    public void increment() {
        System.out.println("in increment " + (++c));
    }

    public int getValue() {
        return c;
    }
}

class StampedLockDemo {
    private Map<String, String> map = new HashMap<>();
    private StampedLock lock = new StampedLock();

    public void put(String key, String value) {
        long stamp = lock.writeLock();
        try {
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public String get(String key) {
        long stamp = lock.readLock();
        try {
            return map.get(key);
        } finally {
            lock.unlockRead(stamp);
        }
    }

    public String readWithOptimisticLock(String key) {
        long stamp = lock.tryOptimisticRead();
        String value = map.get(key);
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                return map.get(key);
            } finally {
                lock.unlock(stamp);
            }
        }
        return value;
    }
}

class ConditionExample {
    private static Queue<Integer> box = new LinkedBlockingQueue<>(1);

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition isFull = lock.newCondition();
        Condition isEmpty = lock.newCondition();
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Runnable consumer = () -> {
            int i = 0;
            do {
                lock.lock();
                try {
                    while (box.isEmpty()) {
                        isEmpty.await();
                    }
                    System.out.println(box.poll());
                    isFull.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    i++;
                    lock.unlock();
                }
            } while (i != 20);
        };
        Runnable producer = () -> {
            int i = 0;
            do {
                lock.lock();
                try {
                    while (!box.isEmpty()) {
                        isFull.await();
                    }
                    box.offer(i++);
                    isEmpty.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } while (i != 20);
        };
        ex.execute(consumer);
        ex.execute(producer);
        ex.shutdown();
    }
}

class Test implements Runnable {

    static StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            Test test = new Test();
            for (int j = 0; j < 100; j++) {
                Thread th = new Thread(test);
                th.start();
                th.join();

            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Override
    public void run() {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void test() throws InterruptedException {
        long stmpt = lock.tryWriteLock();
        try {
            synchronized (Test.class) {
                Thread.sleep(1);
            }
        } finally {
            lock.unlockWrite(stmpt);
        }
    }
}
