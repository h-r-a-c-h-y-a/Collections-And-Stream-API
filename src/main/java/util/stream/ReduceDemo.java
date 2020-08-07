package util.stream;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ReduceDemo {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);
        Optional<Integer> prodObj = myList.stream().reduce((a, b) -> a * b);
        System.out.println(prodObj.orElseThrow(NoSuchElementException::new));
        int productObj = myList.stream().reduce(1, (a, b) -> a * b);
        System.out.println(productObj);
        int evenProduct = myList.stream().reduce(1, (a, b) -> {
            if (b % 2 == 0) {
                return a * b;
            }
            return a;
        });
        System.out.println(evenProduct);
        int parallelProduct = myList.parallelStream().reduce(1, (a, b) -> a * b,
                (a, b) -> a * b);
        System.out.println(parallelProduct);
    }
}

class ReduceDemo2 {
    public static void main(String[] args) {
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);
        double prodOfSqrRoots = myList.parallelStream().reduce(1.0, (a, b) -> a * Math.sqrt(b),
                                                                        (a, b) -> a * b);
        System.out.println(prodOfSqrRoots);
    }
}