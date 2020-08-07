package util.collection;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class ArrayListToArray {
    public static void main(String[] args) {

        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        System.out.println("array list elements: " + al);
        Integer[] ia = new Integer[al.size()];
        ia = al.toArray(ia);
        //System.out.println("Sum: " + Arrays.stream(ia).reduce(Integer::sum).get());
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        ArrayList<Integer> arrayList = new ArrayList<>(1000000);
        Stream.generate(tlr::nextInt).limit(1000000).parallel().forEach(arrayList::add);
        long start = System.currentTimeMillis();
        //ia = new Integer[arrayList.size()];
//        ia = arrayList.toArray(ia);
        arrayList.stream().sorted().forEach(System.out::println);//3800ms pararell,  1830ms stream
//        Arrays.parallelSort(ia);
//        Arrays.stream(ia).parallel().peek(n -> System.out.println(Thread.currentThread().getName())).count();  //forEach(n -> System.out.println(Thread.currentThread().getName() + " " + n));
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
