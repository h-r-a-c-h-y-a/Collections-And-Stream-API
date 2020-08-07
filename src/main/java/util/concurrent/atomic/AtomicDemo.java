package util.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(6);
        try {
            es.execute(new AtomThread("first"));
            es.execute(new AtomThread("second"));
            es.execute(new AtomThread("third"));
            System.out.println("Thread " + es.toString() + " started operation of decrement");
            for (int i = 0; i < 3; i++) {
                es.execute(() -> {
                    for (int j = 0; j < 1000000; j++) {
                        Shared.count.decrementAndGet();
                    }
                });
            }
        } finally {
            es.shutdown();
            System.out.println("Thread " + es.toString() + " finished operation of decrement");
        }
        Thread.currentThread().join(100);
        System.out.println(Thread.currentThread().getName());
        System.out.println(Shared.count);
    }
}

class Shared {
    static AtomicInteger count = new AtomicInteger();
}

class AtomThread implements Runnable {

    String name;

    public AtomThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " started operation of increment");


        for (int i = 0; i < 1000000; i++) {
            Shared.count.incrementAndGet();
        }


        System.out.println("Thread " + name + " finished operation of increment : " + Shared.count.get());
    }
}