package util.collection;

import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>(String::compareToIgnoreCase);
        ts.add("E");
        ts.add("a");
        ts.add("D");
        ts.add("F");
        ts.add("c");
        ts.add("B");
        ts.add("G");
        System.out.println(ts);
        System.out.println(ts.headSet("c", true));
        System.out.println(ts.subSet("B", false, "G", true));
    }
}
