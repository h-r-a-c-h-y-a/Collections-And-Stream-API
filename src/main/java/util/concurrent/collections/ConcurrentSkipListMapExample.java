package util.concurrent.collections;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {
    public static void main(String[] args) {
        ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<>();
        concurrentSkipListMap.put("5555", "John Abraham");
        concurrentSkipListMap.put("3333", "Jim Anderson");
        concurrentSkipListMap.put("1111", "Tom Smith");
        concurrentSkipListMap.put("4444", "Brad Ashler");
        concurrentSkipListMap.put("2222", "David Jones");
        System.out.println("The name associated with the id 1111 is " + concurrentSkipListMap.get("1111"));
        NavigableSet navigableSet = concurrentSkipListMap.keySet();
        for (Iterator itr = navigableSet.iterator(); itr.hasNext(); ) {
            System.out.println(itr.next());
        }
        ConcurrentNavigableMap<String, String> subMap = concurrentSkipListMap.subMap("1111", "3333");
        NavigableSet navigableSubKeySet = subMap.keySet();
        System.out.println("The keys associated with the submap keys from 1111 to 3333 are");
        for (Iterator subMapItr = navigableSubKeySet.iterator(); subMapItr.hasNext(); ) {
            System.out.println(subMapItr.next());
        }
        ConcurrentNavigableMap<String, String> headerMap = concurrentSkipListMap.headMap("2222", true);

        System.out.println("The keys associated with the headMap less than 2222");

        NavigableSet navigableHeadMapKeySet = headerMap.keySet();

        for (Iterator headMapIterator = navigableHeadMapKeySet.iterator(); headMapIterator.hasNext(); ) {
            System.out.println(headMapIterator.next());
        }

        ConcurrentNavigableMap<String, String> tailMap = concurrentSkipListMap.tailMap("1111", false);

        System.out.println("Thekeys associated with the tailMap less than 1111");
        NavigableSet navigableTailMapKeySet = tailMap.keySet();

        for (Iterator tailMapIterator = navigableTailMapKeySet.iterator(); tailMapIterator.hasNext(); ) {
            System.out.println(tailMapIterator.next());
        }
    }
}
