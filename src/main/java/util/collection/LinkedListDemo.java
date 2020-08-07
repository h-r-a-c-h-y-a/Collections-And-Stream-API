package util.collection;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("F");
        ll.add("B");
        ll.add("E");
        ll.addFirst("A");
        ll.add("D");
        ll.addLast("C");
        ll.add(1, "A2");
        System.out.println("ll elements: " + ll);
        ll.remove("F");
        ll.remove(2);
        System.out.println("ll elem after removing: " + ll);
        String val = ll.get(2);
        ll.set(2, val + " Changing");
        System.out.println("ll after changings" + ll);
    }
}
