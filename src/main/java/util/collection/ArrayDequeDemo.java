package util.collection;

import java.util.ArrayDeque;

public class ArrayDequeDemo {
    public static void main(String[] args) {
        ArrayDeque<String> adq = new ArrayDeque<>();
        adq.push("A");
        adq.push("C");
        adq.push("B");
        adq.push("D");
        adq.push("A");
        adq.push("E");
        System.out.println("pop at stack.. ");
        System.out.println(adq.getLast());
        while (adq.peek() != null){
            System.out.print(adq.pop() + " ");
        }
        System.out.println();
    }
}
