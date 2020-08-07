package util.concurrent.synhronizers;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> ex = new Exchanger<>();
        new UserString(ex);
        new MakeString(ex);
    }
}

class MakeString implements Runnable {

    Exchanger<String> ex;
    String str;

    public MakeString(Exchanger<String> ex) {
        this.ex = ex;
        str = "";
        new Thread(this).start();
    }

    @Override
    public void run() {
        char ch = 'A';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                str += (char) ch++;
            }
            try {
                str = ex.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class UserString implements Runnable {

    Exchanger<String> ex;
    String str = "1";

    public UserString(Exchanger<String> ex) {
        this.ex = ex;
        new Thread(this).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                str = ex.exchange("");
                System.out.println("coming: " + str);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}


class ExchangerDemo2 {
    public static void main(String[] args) {
        Exchanger<String> ex = new Exchanger<>();
        new FileReaderDemo(ex, "src/test.txt");
        new Outputer(ex);
    }
}

class FileReaderDemo implements Runnable {

    Exchanger<String> readerExchanger;
    String str;
    String filePath;

    public FileReaderDemo(Exchanger<String> ex, String filePath) {
        this.readerExchanger = ex;
        new Thread(this).start();
        this.filePath = filePath;
        str = "";
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            while ((str = br.readLine()) != null) {
                str = readerExchanger.exchange(str);
            }
            readerExchanger.exchange(str);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}

class Outputer implements Runnable {
    Exchanger<String> ex;
    String str;


    public Outputer(Exchanger<String> ex) {
        this.ex = ex;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                str = ex.exchange(new String());
                if (str == null) break;
                System.out.println(str + " -->line: " + i++);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ExchangerExample {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<>();
        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.execute(() -> {
            try(Reader reader = new FileReader(
                    new ClassPathResource("test.txt").getFile())) {
                int num = 0;
                do {
                    num = reader.read();
                    exchanger.exchange(num);
                }while (num != -1);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
       ex.execute(() -> {
            int num = 0;
            do {
                try {
                    num = exchanger.exchange(num);
                    if (num == -1) break;
                    System.out.print((char) num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (num != -1);
        });
       ex.shutdown();
    }
}
