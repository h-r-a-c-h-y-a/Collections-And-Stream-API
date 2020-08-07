package util.concurrent.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetExample {

    List<User> list;
    CopyOnWriteArraySet<User> cowSet;

    public CopyOnWriteArraySetExample() {
        list = new ArrayList<User>();
        list.add(new User("Harry"));
        list.add(new User("George"));
        list.add(new User("Michael"));
        cowSet = new CopyOnWriteArraySet<User>(list);
        System.out.println("Cycle changed");
        Iterator<User> iterator = cowSet.iterator();
        int count = 0;
        while (iterator.hasNext()){
            User user = iterator.next();
            System.out.println("  " + user.name);
            if (++count == 2){
                cowSet.add(new User("Bill"));
                user.name += " Cluni";
            }
        }
        System.out.println("\nCycle no changed");
        iterator = cowSet.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            System.out.println(" " + user.name);
        }
    }

    public static void main(String[] args) {
        new CopyOnWriteArraySetExample();
    }

    class User{
        private String name;

        public User(String name) {
            this.name = name;
        }
    }
}
