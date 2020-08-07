package util.concurrent.executors.fork_join;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskDemo {
    public static void main(String[] args) {
        ForkJoinPool fjp = ForkJoinPool.commonPool();
        double[] nums = new double[5000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (i % 2) == 0 ? i : -i;
        }
        Sum task = new Sum(nums, 0, nums.length);
        double summation = fjp.invoke(task);
        System.out.println("sum: " + summation);
    }
}

class Sum extends RecursiveTask<Double> {

    final int threshold = 500;
    double[] data;
    int start, end;

    public Sum(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double sum = 0;
        if ((end - start) < threshold) {
            for (int i = start; i < end; i++) {
                sum += data[i];
            }
        } else {
            int middle = (start + end) / 2;
            Sum subTaskA = new Sum(data, start, middle);
            Sum subTaskB = new Sum(data, middle, end);
            subTaskA.fork();
            subTaskB.fork();
            sum += subTaskA.join() + subTaskB.join();
        }
        return sum;
    }
}
