package util.concurrent.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceDemo {
    static AtomicReference<Person> p = new AtomicReference<>(new Person(20));

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                p.set(new Person(p.get().age + 10));
                System.out.println("Atomic Check by first thread: " +
                        Thread.currentThread().getName() + " is " + p.get().age);
            }
        });
        Thread t2 = new Thread(() -> {
            Person per = p.get();
            for (int i = 1; i <= 3; i++) {
                System.err.println(p.get().equals(per) + "_" + per.age + "_" + p.get().age);
                p.compareAndSet(per, new Person(p.get().age + 10));
                System.out.println("Atomic Check by second thread : " +
                        Thread.currentThread().getName() + " is " + p.get().age);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final value: " + p.get().age);
    }
}

class Person {
    int age;

    public Person(int i) {
        age = i;
    }
}


class AtomicRefEg {

    static int stampVal = 1;
    static AtomicStampedReference<Person1> s = new AtomicStampedReference<>(new Person1(20), stampVal);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("stamp value for first thread:" + stampVal);
                s.compareAndSet(s.getReference(), new Person1(s.getReference().age + 10), stampVal, ++stampVal);
                System.out.println("Atomic Check by first thread: " + Thread.currentThread().getName() + " is " + s.getReference().age + " " + s.getReference());
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("stamp value for second thread:" + stampVal);
                s.compareAndSet(s.getReference(), new Person1(s.getReference().age + 10), stampVal, ++stampVal);
                System.out.println("Atomic Check by second thread : " + Thread.currentThread().getName() + " is " + s.getReference().age + " " + s.getReference());
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final value: " + s.getReference().age);
    }
}

class Person1 {
    int age;

    public Person1(int i) {
        age = i;
    }
}

class Solution{
    static List<AtomicReference<Integer>> seats;

    public static void main(String[] args) throws InterruptedException {
        seats = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            seats.add(new AtomicReference<>());
        }
        Thread[] threads = new Thread[22];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyTh(seats, i);
            threads[i].start();
        }
        for (Thread t: threads) {
            t.join();
        }
        for (AtomicReference<Integer> seat: seats) {
            System.out.print(" " + seat.get());
        }
    }

    static class MyTh extends Thread{
        static AtomicInteger full = new AtomicInteger(0);
        List<AtomicReference<Integer>> l;
        int id;
        int seats;

        public MyTh(List<AtomicReference<Integer>> l, int id) {
            this.l = l;
            this.id = id;
            seats = l.size();
        }

        @Override
        public void run() {
            boolean reversed = false;
            try {
                while (!reversed && full.get() < seats){
                    Thread.sleep(50);
                    int r = ThreadLocalRandom.current().nextInt(0, seats);
                    AtomicReference<Integer> el = l.get(r);
                    reversed = el.compareAndSet(null, id);
                    if (reversed){
                        full.getAndIncrement();
                    }
                }
                if (!reversed && full.get() == seats){
                    System.out.println("user " + id + " did not get a seat");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}