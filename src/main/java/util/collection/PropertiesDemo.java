package util.collection;

import java.io.*;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("src/db.properties");
        Properties p = new Properties();
        p.load(reader);
        System.out.println(p.getProperty("host"));
        System.out.println(p.getProperty("user"));
        System.out.println(p.getProperty("password"));

        p = System.getProperties();
        p.forEach((key, value) -> System.out.println(key + "=" + value));

        p.setProperty("name", "Hrach");
        p.setProperty("age", "29");
        p.store(new FileWriter("src/user.properties"), "Chjogi inch parametr e");
    }
}

class PhoneBook{
    public static void main(String[] args) throws IOException {
        Properties ht = new Properties();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name, number;
        FileInputStream fin = null;
        boolean changed = false;
        try{
            fin = new FileInputStream("phonebook.dat");
        } catch (FileNotFoundException e) {
        }
        try{
            if (fin != null){
                ht.load(fin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
               if (fin != null){
                fin.close();
               }
            } catch (IOException e) {
                System.out.println("error than read file.");
            }
            do{
                System.out.println("enter contacts 'exit' for stop program:");
                name = br.readLine();
                if(name.equals("exit")) continue;
                System.out.println("enter phone number: ");
                number = br.readLine();
                ht.put(name.trim(), number.trim());
                changed = true;
            }while (!name.equals("exit"));
            if (changed){
                try(FileOutputStream fout = new FileOutputStream("phonebook.dat")){
                    ht.store(fout, "Phone book");
                }
                do{
                    System.out.println("enter name for search contact 'exit' for stop program:");
                    name = br.readLine();
                    if (name.equals("exit"))continue;
                    number = (String) ht.get(name.trim());
                    System.out.println(number);
                }while (!name.equals("exit"));
            }
        }
    }
}