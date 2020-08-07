package util.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");
        System.out.print("al elements ");
        System.out.println();
        Iterator<String> itr = al.iterator();
        itr.forEachRemaining(el -> System.out.print(el + " "));
        System.out.println();
        ListIterator<String> litr = al.listIterator();
        //litr.forEachRemaining(e -> litr.set(e + "+"));
        while (litr.hasNext()) {
            //String el = litr.next();
            litr.set(litr.next() + "+");
        }
        System.out.println();
        System.out.print("changing the al: ");
        itr = al.iterator();
        itr.forEachRemaining(el -> System.out.print(el + " "));
        System.out.println();
        System.out.print("changing in reverse order ");
        while (litr.hasPrevious()) {
            String el = litr.previous();
            System.out.print(el + " ");
        }
    }
}
