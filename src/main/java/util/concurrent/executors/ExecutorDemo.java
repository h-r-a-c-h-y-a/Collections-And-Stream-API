package util.concurrent.executors;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InterruptedException {
        CountDownLatch cdl = new CountDownLatch(5);
        CountDownLatch cd2 = new CountDownLatch(5);
        CountDownLatch cd3 = new CountDownLatch(5);
        CountDownLatch cd4 = new CountDownLatch(5);
        ExecutorService es = Executors.newFixedThreadPool(2);
        System.out.println("Threads start!");
        es.execute(new MyThread("first", cdl));
        es.execute(new MyThread("second", cd2));
        es.execute(new MyThread("third", cd3));
        es.execute(new MyThread("fourth", cd4));
        try{
            cd3.await();
            cd2.await();
            cdl.await();
            cd4.await();
        } catch (InterruptedException e) {
            System.out.println(e);
        }



        TimeUnit.DAYS.sleep(5);
        System.out.println(TimeUnit.MINUTES.toDays(1000));
        System.out.println(TimeUnit.MINUTES.toHours(1000));
        System.out.println(TimeUnit.MINUTES.toMinutes(1000));
        System.out.println(TimeUnit.MINUTES.toSeconds(1000));
        System.out.println(TimeUnit.MINUTES.toMillis(1000));
        System.out.println(TimeUnit.MINUTES.toMicros(1000));
        System.out.println(TimeUnit.MINUTES.toNanos(1000));
        System.out.println(TimeUnit.NANOSECONDS.convert(
                1000, TimeUnit.MICROSECONDS));



    }


}
class MyThread implements Runnable {
    String name;
    CountDownLatch latch;
    public MyThread(String name, CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
        new Thread(this);
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(name + ": " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
