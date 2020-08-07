package util.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

public class AccumulatorAdderSpec {
    public static void main(String[] args) throws InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        AtomicLong num = new AtomicLong(0);
        ExecutorService exec = Executors.newFixedThreadPool(processors);
        for (int i = 0; i < 100; i++) {
            exec.execute(num::incrementAndGet);
        }
        Thread.sleep(1000);
        System.out.println(num.get());
        num.set(0);
        for (int i = 0; i < 100; i++) {
            exec.submit(() -> num.updateAndGet(v -> v + 3));
        }
        Thread.sleep(1000);
        System.out.println(num.get());
        num.set(0);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            exec.submit(() -> num.accumulateAndGet(finalI, Long::sum));
        }
        Thread.sleep(1000);
        System.out.println(num.get());

        LongAdder adder = new LongAdder();
        for (int i = 0; i < 100; i++) {
            exec.submit(adder::increment);
        }
        Thread.sleep(100);
        System.out.println(adder.sum());
        adder.reset();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            exec.submit(()-> adder.add(finalI));
        }
        Thread.sleep(100);
        System.out.println(adder.sum());
        System.out.println(adder.sumThenReset());
        System.out.println(adder.longValue());
        Thread.sleep(100);

        LongBinaryOperator func = (acc, c) -> acc + c * c;
        LongAccumulator accum = new LongAccumulator(func, 1);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            exec.submit(()-> accum.accumulate(finalI));
        }
        Thread.sleep(100);
        System.out.println(accum.getThenReset());
        exec.shutdown();
    }
}
