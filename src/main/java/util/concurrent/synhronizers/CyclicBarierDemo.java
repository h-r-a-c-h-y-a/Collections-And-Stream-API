package util.concurrent.synhronizers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ForkJoinPool;

public class CyclicBarierDemo {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier cb = new CyclicBarrier(3, () -> System.out.println("barier was broken"));
        System.out.println("threads starts");
        ForkJoinPool ex = ForkJoinPool.commonPool();
        for (int i = 0; i < 30; i++) {
            ex.execute(new MyThread1(cb, "first:" + i));
            ex.execute(new MyThread1(cb, "second:" + i));
            ex.execute(new MyThread1(cb, "third:" + i));
        }
        while (cb.getNumberWaiting() == 0) {}
        while (!ex.isQuiescent()){}
        System.err.println("main thread finished work");
    }
}

class MyThread1 implements Runnable {

    CyclicBarrier cb;
    String name;

    public MyThread1(CyclicBarrier cb, String name) {
        this.cb = cb;
        this.name = name;
    }

    @Override
    public void run() {

        try {
            System.out.println(name + " come to barier");
            cb.await();
            System.out.println(name + " start work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

class Ferry {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3,
            () -> System.out.println("Ferry ferried cars!"));

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(10);
        }
    }

    static class Car implements Runnable {

        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("The car №%d drove up to the ferry.\n", carNumber);
                BARRIER.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

//@RestController
//class ProductController {
//
//    @GetMapping("/products/product/{id}")
//    public ResponseEntity<Product> getInfo(@PathVariable("id") Integer id){
//        Product product = new Product();
//        CyclicBarrier cb = new CyclicBarrier(3, () -> System.out.println(product));
//        ExecutorService ex = Executors.newFixedThreadPool(3);
//        ex.execute(() -> {
//            product.setName("cheese");
//            cb.await();
//        });
//        ex.execute(() -> {
//            product.setPrice(200.50);
//            cb.await();
//        });
//        ex.execute(() -> {
//            product.setDate(LocalDateTime.now());
//            cb.await();
//        });
//        return ResponseEntity.ok(product);
//    }
//    @Data
//    private static class Product{
//        Integer id;
//        double price;
//        String name;
//        LocalDateTime date;
//    }
//}
