package util.concurrent.synhronizers;

import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Math.random;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(4);
        System.out.println("start executor thread");
        new MyThread(cdl);
        try {
            cdl.await();
            System.out.println("....");
            new MyThread(cdl);
            cdl.await(1, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("end of executor thread");
    }
}

class MyThread implements Runnable {

    CountDownLatch latch;

    public MyThread(CountDownLatch latch) {
        this.latch = latch;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            latch.countDown();
        }
    }
}

class CDLDemo {
    public static void main(String[] args) throws InterruptedException {
        // Let us create task that is going to
        // wait for four threads before it starts
        CountDownLatch latch = new CountDownLatch(4);
        // Let us create four worker
        // threads and start them.
        Worker first = new Worker("Worker-1", 1000, latch);
        Worker second = new Worker("Worker-2", 2000, latch);
        Worker third = new Worker("Worker-3", 3000, latch);
        Worker fourth = new Worker("Worker-4", 4000, latch);
        first.start();
        second.start();
        third.start();
        fourth.start();
        // The main task waits for four threads
        latch.await();
        // Main thread has started
        System.out.println(Thread.currentThread().getName() + " has finished");
    }
}

// A class to represent threads for which
// the main thread waits.
class Worker extends Thread {

    private int delay;
    private CountDownLatch latch;

    public Worker(String name, int delay, CountDownLatch latch) {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
            latch.countDown();
            System.out.println(Thread.currentThread() + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Race1 {
    private static final CountDownLatch START = new CountDownLatch(8);
    private static final int TRACK_LENGTH = 50000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 5; i++) {
            new Thread(new Car(i, (int) (random() * 10000 + 50))).start();
            Thread.sleep(1000);
        }
        while (START.getCount() > 3) {
            Thread.sleep(100);
        }
        Thread.sleep(1000);
        System.out.println("To start!");
        START.countDown();
        Thread.sleep(1000);
        System.out.println("Attention!");
        START.countDown();
        Thread.sleep(1000);
        System.out.println("Go!");
        START.countDown();
    }

    static class Car implements Runnable {

        private int carNumber;
        private int carSpeed;


        public Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        @Override
        public void run() {
            try {
                System.out.printf("The car  №%d drove up to the starting line. \n", carNumber);
                START.countDown();
                START.await();
                Thread.sleep(TRACK_LENGTH / carSpeed);
                System.out.printf("Car №%d finished! \n", carNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Race {
    private static int carNumber = 1;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(5);
        System.out.println("To start!");
        ScheduledExecutorService sheduleEx = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 5; i++) {
            sheduleEx.schedule(() -> {
                cdl.countDown();
                System.out.printf("The car  №%d drove up to the starting line. \n", carNumber++);
            }, ThreadLocalRandom.current().nextInt(50) * 100,  TimeUnit.MILLISECONDS);
        }
        cdl.await();
        System.out.println("Attention!");
        System.out.println("Go!");
        sheduleEx.shutdown();
    }
}
