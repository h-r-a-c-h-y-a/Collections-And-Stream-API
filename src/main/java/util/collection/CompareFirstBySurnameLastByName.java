package util.collection;

import java.util.Comparator;
import java.util.TreeSet;

public class CompareFirstBySurnameLastByName {

    public static void main(String[] args) {
        Comparator<String> comp = Comparator.comparing(str -> str.substring(str.lastIndexOf(" ")));
        TreeSet<String> ts = new TreeSet<>(comp.thenComparing(Comparator.naturalOrder()));
        ts.add("Aharon Smith");
        ts.add("James Brunk");
        ts.add("Elizabeth Teylor");
        ts.add("George Teylor");
        ts.add("Elizabeth Smith");
        System.out.println(ts);
    }
}




