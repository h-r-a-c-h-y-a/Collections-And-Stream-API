package util.concurrent.executors.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ForkJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println(start);
//        ForkJoinPool fjp = ForkJoinPool.commonPool();//161ms
        int[] nums = IntStream.range(1, 10000000).toArray();
        System.out.println("part source sequence");
//        System.out.println("parallel level: " + fjp.getParallelism() + " free processors: " + Runtime.getRuntime().availableProcessors());
//        Thread th = new Thread(new Task(nums, 0, nums.length));
//        th.start();
//        th.join();
//        System.out.println("\n");
//        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);
//        fjp.invoke(task);
        System.out.println("Part of the converted sequence (up to four decimal places):");
//        if (!task.isCompletedNormally()) System.exit(0);

        System.out.println("data.length: " + nums.length);
        double sum = 0.0;
        Thread.sleep(500);
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
        }
        System.out.println(sum);

//        while (ex.awaitTermination(20000, TimeUnit.MILLISECONDS)) {
//        }
//        Thread.sleep(5000);
//        ex.shutdown();
//        Arrays.stream(nums).forEach(System.out::print);
//        while (!fjp.isQuiescent()) ;


        System.out.printf("%n%s:ms", (System.currentTimeMillis() - start));
    }
}

class SqrtTransform extends RecursiveAction {

    final int seqThreshold = 1000;
    int[] data;
    int start, end;

    public SqrtTransform(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < seqThreshold) {
            for (int i = start; i < end; i++) {
                data[i] = ((int) (Math.round(data[i])) + 2);
            }
            System.out.println(System.currentTimeMillis());
        } else {
            int middle = (start + end) / 2;
            invokeAll(new SqrtTransform(data, start, middle),
                    new SqrtTransform(data, middle, end));
        }
    }
}

class Manager {

    static int i = 0;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        int[] nums = IntStream.range(1, 10000000).toArray();
        new Task(nums, 0, nums.length).bla();
    }

    static List<Th> threads = new ArrayList<>();

    static {
        for (int i = 0; i < 25; i++) {
            Th t = new Th();
            Thread th = new Thread(t);
            threads.add(t);
            th.start();

        }
    }

    public static void invoke(Task task, Task task2) {
        Th.tasks.add(task);
        Th.tasks.add(task2);
    }

    static class Th implements Runnable {

        static Queue<Task> tasks = new LinkedBlockingQueue<>();
        boolean work = true;

        @Override
        public void run() {
            while (work) {
//                System.out.println("blaa");
                Task t = tasks.poll();
                if (t != null) {
                    t.bla();
                }
            }

        }
    }
}

class Task {
    final int seqThreshold = 1000;
    int index;
    int[] data;
    int start, end;

    public Task(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
//        System.out.println("parent-" + Thread.currentThread().getName());

    }

    public void bla() {
//        System.out.println(Thread.currentThread().getName());
        if (end - start < seqThreshold) {
//            System.out.printf("start = %s   end = %s  \n", start, end);
            for (int i = start; i < end; i++) {
                data[i] = ((int) (Math.round(data[i])) + 2);
            }
            System.out.println(System.currentTimeMillis());
        } else {
            int middle = (start + end) / 2;
            Manager.invoke(new Task(data, start, middle), new Task(data, middle, end));
        }
    }
}
