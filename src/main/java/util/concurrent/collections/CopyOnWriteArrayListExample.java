package util.concurrent.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    List<String> list;
    public CopyOnWriteArrayListExample(){
        List<String> lst = new ArrayList<>();
        lst.add("Java");
        lst.add("J2EE");
        lst.add("J2SE");
        lst.add("Collection");
        lst.add("Concurrent");
        list =  new CopyOnWriteArrayList<>(lst);
        System.out.println("Cycle with changes");
        printCollection(true);
        System.out.println("\nCycle no changed");
        printCollection(false);
    }

    private void printCollection(boolean change){
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String element = iterator.next();
            System.out.printf("  %s %n", element);
            if (change){
                if(element.equals("J2SE")){
                    list.add("New String");
                    list.remove(element);
                }
            }
        }
    }

    public static void main(String[] args) {
        new CopyOnWriteArrayListExample();
        System.exit(0);
    }
}
