package util.collection;

import java.util.ArrayList;
import java.util.Spliterator;

public class SpliteratorDemo {
    public static void main(String[] args) {
        ArrayList<Double> vals = new ArrayList<>();
        vals.add(1.0);
        vals.add(2.0);
        vals.add(3.0);
        vals.add(4.0);
        vals.add(5.0);
        System.out.print("elements in nums: \n");
        Spliterator<Double> spl = vals.spliterator();
        while (spl.tryAdvance(System.out::println));
        System.out.println();
        spl = vals.spliterator();
        ArrayList<Double> sqrs = new ArrayList<>();
        while (spl.tryAdvance(num -> sqrs.add(Math.sqrt(num))));
        System.out.print("elements in sqrs: \n");
        spl = sqrs.spliterator();
        spl.forEachRemaining(System.out::println);
    }
}
