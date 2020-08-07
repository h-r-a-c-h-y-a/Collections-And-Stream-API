package util.concurrent.executors;

import java.util.concurrent.*;

public class ScheduledExecutorRunnable {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
        Runnable task2 = () -> System.out.println("Running task2 .....");
        task4();
        ses.schedule(task2, 5, TimeUnit.SECONDS);
        ses.schedule(() -> System.out.println("Running task1 .....")
                , 1, TimeUnit.SECONDS);
        task3();
        ses.shutdown();
    }

    public static void task1(){
        System.out.println("Running task1....");
    }

    public static void task3(){
        System.out.println("Running task3....");
    }
    public static void task4(){
        System.out.println("Running task4....");
    }
}

class ScheduledExecutorCallable{
    public static void main(String[] args)
            throws ExecutionException, InterruptedException {
        ScheduledExecutorService ses =
                Executors.newScheduledThreadPool(1);
        Callable<Integer> task2 = () -> 10;
        task1();
        ScheduledFuture<Integer> schedule =
                ses.schedule(task2, 5, TimeUnit.SECONDS);
        System.out.println(schedule.get());
        task3();
        System.out.println("shutdown!");
        ses.shutdown();
    }

    public static void task1(){
        System.out.println("Running task1...");
    }

    public static void task3(){
        System.out.println("Running task3...");
    }
}
