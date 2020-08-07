package util.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlatMapDemo {
    public static void main(String[] args) {
        String[][] data = {{"a", "b"}, {"c", "d"}, {"e", "f"}};
        Stream<String[]> temp = Arrays.stream(data);
        Stream<String> stringStream = temp.flatMap(Arrays::stream);
        Stream<String> stream = stringStream.filter("a"::equals);
        stream.forEach(System.out::println);
    }
}

class FlatDemo2 {
    public static void main(String[] args) {
        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in action");
        obj1.addBook("Java 8 in action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2rd edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Pithon 5th edition");
        obj2.addBook("Effective Java (3rd edition)");
        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);
        List<String> collect = list.stream()
                .map(Student::getBook)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}

class Student {
    private String name;
    private List<String> book;

    public void addBook(String book) {
        if (this.book == null) {
            this.book = new ArrayList<>();
        }
        this.book.add(book);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBook() {
        return book;
    }

    public void setBook(List<String> book) {
        this.book = book;
    }
}

class FlatMapDemo3 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6};
        Stream<int[]> streamArr = Stream.of(array);
        IntStream intStream = streamArr.flatMapToInt(Arrays::stream);
        intStream.forEach(System.out::println);
    }
}