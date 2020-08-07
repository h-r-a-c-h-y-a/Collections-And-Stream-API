package util.concurrent.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapExample {
    Map<String, String> map;

    public ConcurrentHashMapExample() {
        System.out.println("ConcurrentHashMap");
        createMap(true);
        addValue(true);
        System.out.println("\n\nHashMap");
        createMap(false);
        addValue(false);
    }

    private void addValue(boolean concurrent) {
        System.out.println(" before iterator : " + map);
        Iterator<String> it = map.keySet().iterator();
        System.out.print(" cycle : ");
        while (it.hasNext()) {
            String key = it.next();
            if (key.equals("6")) {
                map.put(key + "new", "222");
                if (!concurrent) break;
            } else {
                System.out.print("  " + key + "=" + map.get(key));
            }
        }
        System.out.println();
        System.out.println("  after iterator : " + map);
    }

    private void createMap(boolean concurrent) {
        if (concurrent) {
            map = new ConcurrentHashMap<String, String>();
        } else {
            map = new HashMap<>();
        }
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        map.put("5", "1");
        map.put("6", "1");
    }

    public static void main(String[] args) {
        new ConcurrentHashMapExample();
        System.exit(0);
    }
}

class ConcurrentHashMapDemo {
    private final ConcurrentHashMap<Integer, String> conHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        ConcurrentHashMapDemo obj = new ConcurrentHashMapDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.execute(obj.new WriteThreasOne());
        executorService.execute(obj.new WriteThreasTwo());
        executorService.execute(obj.new ReadThread());
//        new Thread(obj.new WriteThreasOne()).start();
//        new Thread(obj.new WriteThreasTwo()).start();
//        new Thread(obj.new ReadThread()).start();


        executorService.shutdown();
    }

    class WriteThreasOne implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                conHashMap.put(i, "A" + i);
            }
        }
    }

    class WriteThreasTwo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 5; i++) {
                conHashMap.put(i, "B" + i);
            }
        }
    }

    class ReadThread implements Runnable {
        @Override
        public void run() {
            Iterator<Integer> ite = conHashMap.keySet().iterator();
            while (ite.hasNext()){
                Integer key =ite.next();
                System.out.println(key + " : " + conHashMap.get(key));
            }
        }
    }
}