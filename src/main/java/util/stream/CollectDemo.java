package util.stream;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class CollectDemo {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmail1> myList = new ArrayList<>();
        NamePhoneEmail1 merry = new NamePhoneEmail1("Mэpи", "555-3333",
                "Mary@HerbSchildt.com");
        myList.add(new NamePhoneEmail1("Лappи", " 555-5555 ",
                "Larry@HerbSchildt.com"));
        myList.add(new NamePhoneEmail1("Джeймc", " 555-4444",
                " James@HerbSchildt.com"));
        myList.add(merry);
        myList.add(merry);

        Map<String, String> map1 = myList.stream().collect(toMap(NamePhoneEmail1::getName, NamePhoneEmail1::getPhoneNumber));
        map1.forEach((key, value) -> System.out.println(key + ": " + value));

        Stream<NamePhone1> nameAndPhone = myList.stream().map(obj -> new NamePhone1(obj.name, obj.phoneNumber));
        List<NamePhone1> npList = nameAndPhone.collect(Collectors.toList());
        npList.forEach(obj -> System.out.println(obj.name + ": " + obj.phoneNumber));
        nameAndPhone = myList.stream().map(obj -> new NamePhone1(obj.name, obj.phoneNumber));
        Set<NamePhone1> npSet = nameAndPhone.collect(Collectors.toSet());
        npSet.forEach(obj -> System.out.println(obj.name + ": " + obj.phoneNumber));
        System.out.println();

        Map<String, String> map = myList.stream().collect(HashMap::new, (m, obj) -> m.put(obj.name, obj.phoneNumber), HashMap::putAll);
        map.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}

class NamePhoneEmail1 {
    String name;
    String phoneNumber;
    String email;

    public NamePhoneEmail1(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class NamePhone1 {
    String name;
    String phoneNumber;

    public NamePhone1(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

class SumOfListDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(23, 43, 12, 25);
        IntSummaryStatistics stats = list.stream().collect(Collectors.summarizingInt(i -> i + i));
        System.out.println(stats.toString());
    }
}

class JoiningExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Ram", "Shyam", "Shiv", "Mahesh");
        String result = list.stream().collect(Collectors.joining(", "));
        System.out.println(result);
    }
}

class AveragingIntExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Double result = list.stream().collect(Collectors.averagingInt(v -> v * 2));
        System.out.println(result);
    }
}

class CountingExample {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4);
        long result=  list.stream().collect(Collectors.counting());
        System.out.println("Count: "+ result);
    }
}