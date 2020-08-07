package util.collection;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();

        System.out.println("size before addind: " + al.size());
        al.add("C");
        al.add("A");
        al.add("B");
        al.add("F");
        al.add("D");
        al.add("E");
        al.add(1, "A2");
        System.out.println("size after adding: " + al.size());
        al.forEach(System.out::println);
        al.remove("F");
        al.remove(2);
        System.out.println("size after removing: " + al.size());
        al.forEach(System.out::println);
    }
}
