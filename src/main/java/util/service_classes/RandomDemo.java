package util.service_classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        List<Double> doubles = null;
        Random r = new Random();
        doubles = r.doubles().limit(1000).
                collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        doubles.forEach(System.out::println);
    }
}
