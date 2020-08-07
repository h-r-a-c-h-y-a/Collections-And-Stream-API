package util.collection;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.IntConsumer;

public class ArraysDemo {
    public static void main(String[] args) {
        int[] intNums = {8,3,9,3,23,7,12,8,9,0,13,6,1,2};
        String[] strings = {"bca","cab","abc","cba","acb","bac"};
        System.out.println(Arrays.binarySearch(intNums, 23));
        Spliterator.OfInt spliterator = Arrays.spliterator(intNums);
        spliterator.forEachRemaining((IntConsumer) Integer::reverse);
//        Arrays.stream(intNums).sorted().forEach(System.out::println);
//        Arrays.stream(intNums).filter(e -> e % 2 == 0).forEachOrdered(System.out::println);
//        System.out.println(Arrays.stream(intNums).average().getAsDouble());
//        System.out.println(Arrays.stream(intNums).findAny().getAsInt());
//        System.out.println(Arrays.stream(intNums).findFirst().getAsInt());
//        System.out.println(Arrays.stream(intNums).max().getAsInt());
//        System.out.println(Arrays.stream(intNums).min().getAsInt());
//        System.out.println(Arrays.stream(intNums).reduce(Integer::sum).getAsInt());
        System.out.println(Arrays.stream(intNums).average().getAsDouble());
    }

}
