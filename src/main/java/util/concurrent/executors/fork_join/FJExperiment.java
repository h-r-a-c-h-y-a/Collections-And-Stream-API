package util.concurrent.executors.fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class FJExperiment {
    public static void main(String[] args) {
        long beginT, endT;
        beginT = System.nanoTime();
        int pLevel = Runtime.getRuntime().availableProcessors();
        ForkJoinPool fjp = new ForkJoinPool(pLevel);
        double[] nums;
        nums = ThreadLocalRandom.current().doubles().parallel()
                .limit(1000000).map(num-> num * 100).toArray();
//        for (int i = 0; i < nums.length; i++) {
//            if (i % 2 == 0) {                         // 115454903ns
//                nums[i] = Math.sqrt(nums[i]);
//            } else {
//                nums[i] = Math.cbrt(nums[i]);
//            }
//        }
        Transform task = new Transform(nums, 0, nums.length, 1000);
        fjp.invoke(task);
        System.out.println("parallel level: " + pLevel);
        System.out.println("sequence threshold: " + 1000);// 93795649 ns
        endT = System.nanoTime();
        System.out.println("Time for finish task: " + (endT - beginT) + " ns");
    }
}

class Transform extends RecursiveAction {
    int threshold;
    double[] nums;
    int start, end;
    public Transform(double[] nums, int start,
                     int end, int threshold) {
        this.threshold = threshold;
        this.nums = nums;
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        if (end - start < threshold) {
            for (int i = start; i < end; i++) {
                if (i % 2 == 0) {
                    nums[i] = Math.sqrt(nums[i]);
                } else {
                    nums[i] = Math.cbrt(nums[i]);
                }
            }
        } else {
            int middle = (start + end) / 2;
            invokeAll(new Transform(nums, start,
                            middle, threshold),
                    new Transform(nums, middle,
                            end, threshold));
        }
    }
}
