package util.concurrent.collections;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedDequeDemo {
    static ConcurrentLinkedDeque<String> cld = new ConcurrentLinkedDeque<>();

    public static void main(String[] args) {
        ConcurrentLinkedDequeDemo obj = new ConcurrentLinkedDequeDemo();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(obj.new ThreadOne());
        service.execute(obj.new ThreadTwo());
        service.shutdown();
    }

    class ThreadOne implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                cld.add("A" + i);
            }
        }
    }

    class ThreadTwo implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                String s = cld.poll();
                System.out.println("Element received is: " + s);
            }
        }
    }
}
