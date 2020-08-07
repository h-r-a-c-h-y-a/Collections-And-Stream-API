package util.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MapDemo {
    public static void main(String[] args) {
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(7.0);
        myList.add(18.0);
        myList.add(10.0);
        myList.add(24.0);
        myList.add(17.0);
        myList.add(5.0);
        Stream<Double> sqrtRootStrm = myList.stream().map(Math::sqrt);
        double prodOfSqrRoots = sqrtRootStrm.reduce(1.0, (a, b) -> a * b);
        System.out.println(prodOfSqrRoots);
    }
}

class MapDemo2 {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();
        myList.add(new NamePhoneEmail("Лappи", " 555-5555 ",
                "Larry@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("Джeймc", " 555-4444",
                " James@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("Mэpи", "555-33 33",
                "Mary@HerbSchildt.com"));

        System.out.println("myList elements:");
        myList.forEach(a -> {
            System.out.println(a.name + " " + a.phoneNumber + " " + a.email);
        });
        Stream<NamePhone> nameAndPhoneNumber = myList.stream().map(a -> new NamePhone(a.name, a.phoneNumber));
        List<NamePhone> namePhones = myList.stream().map(obj -> new NamePhone(obj.name, obj.phoneNumber)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        nameAndPhoneNumber.forEach(obj -> System.out.println(obj.name + " " + obj.phoneNumber));
        namePhones.forEach(System.out::println);
    }
}

class NamePhoneEmail {
    String name;
    String phoneNumber;
    String email;

    public NamePhoneEmail(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

class NamePhone {
    String name;
    String phoneNumber;

    public NamePhone(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "NamePhone{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

class MapDemo3 {
    public static void main(String[] args) {
        ArrayList<Double> myList = new ArrayList<>();
        myList.add(1.1);
        myList.add(3.6);
        myList.add(9.2);
        myList.add(4.7);
        myList.add(12.1);
        myList.add(5.0);
        System.out.println("myList elements:");
        myList.forEach(num -> System.out.print(num + " "));
        System.out.println();
        IntStream cStrm = myList.stream().mapToInt(num -> (int) Math.ceil(num));
        cStrm.forEach(num -> System.out.print(num + " "));
    }
}