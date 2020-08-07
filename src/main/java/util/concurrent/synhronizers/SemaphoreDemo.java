package util.concurrent.synhronizers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


class SemaphoreExample {
    private static final List<Integer> LIST = new ArrayList<>();
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1, true);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sem.acquire();
                    Thread.sleep(100);
                    LIST.add(i);
                    sem.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ex.execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    sem.acquire();
                    System.out.printf("%d, %n", LIST.get(i));
                    System.out.println(LIST);
                    sem.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ex.shutdown();
    }
}


public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1, true);
        new IncThread("incr", sem);
        new DecThread("decr", sem);
        new IncThread("incr2", sem);
        new DecThread("decr2", sem);
    }
}

class Shared {
    static int count = 0;
}

class IncThread implements Runnable {
    String name;
    Semaphore sem;

    public IncThread(String name, Semaphore sem) {
        this.name = name;
        this.sem = sem;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.printf("Thread %s run%n", name);
        try {
            System.out.println("Thread " + name + " waiting to start");
            sem.acquire(1);
            System.out.println("Thread " + name + " take start");

            for (int i = 0; i < 5; i++) {
                Shared.count++;
                System.out.println(name + ": " + Shared.count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + name + " finished word");
        sem.release(2);
    }
}

class DecThread implements Runnable {
    String name;
    Semaphore sem;

    public DecThread(String name, Semaphore sem) {
        this.name = name;
        this.sem = sem;
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.printf("Thread %s run%n", name);
        try {
            System.out.println("Thread " + name + " waiting to start");
            sem.acquire();
            System.out.println("Thread " + name + " take start");

            for (int i = 0; i < 5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + name + " finished word");
        sem.release();
    }
}

class Q {
    int n;
    Semaphore semCon = new Semaphore(0);
    Semaphore semProd = new Semaphore(1);

    void get() {
        try {
            semCon.acquire();
        } catch (InterruptedException e) {
            System.err.println("catch InterruptedException");
        }
        System.out.println("get: " + n);
        semProd.release();
    }

    void put(int n) {
        try {
            semProd.acquire();
        } catch (InterruptedException e) {
            System.err.println("catch InterruptedException");
        }
        this.n = n;
        System.out.println("sending: " + n);
        semCon.release();
    }
}

class Producer implements Runnable {

    Q q;

    Producer(Q q) {
        this.q = q;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            q.put(i);
        }
    }
}

class Consumer implements Runnable {

    Q q;

    public Consumer(Q q) {
        this.q = q;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            q.get();
        }
    }
}

class ProdCon {
    public static void main(String[] args) {
        Q q = new Q();
        new Consumer(q);
        new Producer(q);
    }
}

class MyThread2 extends Thread {
    Semaphore sem;
    String threadName;

    public MyThread2(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(threadName + " is waiting for a permit.");
        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " gets a permit.");
        for (int i = 0; i < 2; i++) {
            boolean b = sem.hasQueuedThreads();
            if (b) {
                System.out.println("Length of Queue : " + sem.getQueueLength());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(threadName + " release the permit.");
        sem.release();
    }
}

class SemaphoreDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(3, true);
        System.out.println("is Fairness enabled : " + sem.isFair());
        sem.tryAcquire(2);
        System.out.println("Available permits : " + sem.availablePermits());
        System.out.println("number of permits drain by Main thread : " + sem.drainPermits());
        sem.release(1);
        MyThread2 mt1 = new MyThread2(sem, "first");
        MyThread2 mt2 = new MyThread2(sem, "second");
        mt1.start();
        mt2.start();
        System.out.println(sem.toString());
        mt1.join();
        mt2.join();
    }
}

class Parking {
    private static final BlockingQueue<Car> PARKING_PLACES = new LinkedBlockingQueue<>(5);
    private static final Semaphore SEMAPHORE = new Semaphore(5, true);
    private static AtomicInteger parkingNumber = new AtomicInteger(1);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 10; i++) {
            new Thread(new Car(i)).start();
        }
    }

    public static class Car implements Runnable {
        private int carNumber;
        private int carParkingNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        public int setCarParkingNumber(int carParkingNumber) {
            return this.carParkingNumber = carParkingNumber;
        }

        public int getCarParkingNumber() {
            return carParkingNumber;
        }

        @Override
        public void run() {
            System.out.printf("The car №%d drove up to the parking.%n", carNumber);
            try {

                SEMAPHORE.acquire();
                PARKING_PLACES.put(this);
                this.setCarParkingNumber(parkingNumber.getAndIncrement());
                System.out.printf("The car №%d parked place in %d%n",
                        this.carNumber, this.getCarParkingNumber());
                Thread.sleep(100);
                PARKING_PLACES.remove(this);
                System.out.printf("The car №%d left the parking lot %d%n", carNumber, this.getCarParkingNumber());
                parkingNumber.set(this.getCarParkingNumber());
                SEMAPHORE.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

