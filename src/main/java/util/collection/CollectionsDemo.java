package util.collection;

import java.util.*;

public class CollectionsDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list.add(4); list2.add(2);
        list.add(1); list2.add(9);
        list.add(3); list2.add(5);
        list.add(2); list2.add(3);
        list.add(8); list2.add(7);
        list.add(9); list2.add(1);
        list.add(7);  list2.add(2);
        list.add(6);  list2.add(4);
        list.add(5);  list2.add(6);
//        System.out.println(Collections.binarySearch(list, 6, Integer::compare));
//        System.out.println(Collections.binarySearch(list2, 8, Integer::compareTo));
//        System.out.println(Collections.disjoint(list, list2));
//        Collections.fill(list, 45);
//        System.out.println(Collections.frequency(list, 8));
//        System.out.println(Collections.max(list2));
//        System.out.println(Collections.min(list2));
//        System.out.println(Collections.nCopies(3, 6));
//        Collections.replaceAll(list, 5, 555);
//        Collections.reverseOrder();
//        Collections.rotate(list, -8);
//        Collections.shuffle(list, new Random(6));
//        Collections.shuffle(list);
        List<Integer> immutableList = Collections.unmodifiableList(list);
        immutableList.add(1, 45);
        System.out.println(immutableList);
    }
}
