package util.concurrent.executors;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Long> sum;
        Future<Double> hypotenuze;
        Future<Long> factorial;

        System.out.println("Threads start!");
        sum = es.submit(new Sum(1000));
        hypotenuze = es.submit(new Hypot(86, 45));
        factorial = es.submit(new Factorial(30));

        try {
            System.out.println("hip: " +
                    hypotenuze.get(4, TimeUnit.SECONDS));
            System.out.println("sum: " +
                    sum.get(500, TimeUnit.MILLISECONDS));
            System.out.println("fac: " +
                    factorial.get(4, TimeUnit.SECONDS));
        } catch (InterruptedException |
                ExecutionException |
                TimeoutException e) {
            e.printStackTrace();
        }
        es.shutdown();
        System.out.println("threads over work");
    }
}

class Sum implements Callable<Long> {
    int stop;
    public Sum(int stop) {
        this.stop = stop;
    }
    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = 1; i < stop; i++) {
            sum += i;
        }
        return sum;
    }
}

class Hypot implements Callable<Double> {
    double side1, side2;
    public Hypot(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    public Double call() throws Exception {
        return Math.sqrt((side1 * side1) + (side2 * side2));
    }
}

class Factorial implements Callable<Long> {
    int stop;
    public Factorial(int stop) {
        this.stop = stop;
    }
    @Override
    public Long call() throws Exception {
        return factorial(stop);
    }
    private long factorial(int n) {
        if (n == 1) return n;
        return factorial(n - 1) * n;
    }
}
