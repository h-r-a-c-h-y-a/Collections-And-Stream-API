package util.collection;

import java.util.LinkedHashMap;

public class LinkedHashMapDemo {

    public static void main(String[] args) {
        LinkedHashMap<String, Integer> wordNumberMapping = new LinkedHashMap<>();
        wordNumberMapping.put("one", 1);
        wordNumberMapping.put("two", 2);
        wordNumberMapping.put("three", 3);
        wordNumberMapping.put("four", 4);
        wordNumberMapping.put("five", 8);
        wordNumberMapping.computeIfPresent("five", (key, value) -> value = 5);
        System.out.println(wordNumberMapping);
    }
}
