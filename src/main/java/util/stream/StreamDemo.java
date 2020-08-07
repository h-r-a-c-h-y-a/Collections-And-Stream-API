package util.stream;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(7);
        myList.add(18);
        myList.add(10);
        myList.add(24);
        myList.add(17);
        myList.add(5);
        System.out.println(myList);
        Stream<Integer> myStream = myList.stream();
        Optional<Integer> minVal = myStream.min(Integer::compareTo);
        System.out.println(minVal.orElseThrow(NoSuchElementException::new));
        Stream<Integer> sortedStream = myList.stream().sorted();
        sortedStream.forEach(n -> System.out.print(n + " "));

    }
}
