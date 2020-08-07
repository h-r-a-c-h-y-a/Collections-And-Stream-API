package util.concurrent.atomic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SequenceGeneratorExample {

    public static void main(String[] args) {
        SequenceGenerator sg = new SequenceGenerator();
        List<Sequence> sequences = new ArrayList<Sequence>();
        for (int i = 0; i < 10; i++) {
            Sequence seq = new Sequence(i + 1, 3, sg);
            sequences.add(seq);
        }
        System.out.println("\nРасчет последовательностей\n");
        int sum;

        do {
            sum = 0;
            for (int i = 0; i < sequences.size(); i++) {
                if (!sequences.get(i).thread.isAlive()) {
                    sequences.get(i).printSequence();
                    sum++;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        } while (sum < sequences.size());
        System.out.println("\n\nРабота потоков завершена");
        System.exit(0);
    }
}

class Sequence implements Runnable {

    Thread thread;
    int id;
    int count;
    SequenceGenerator sg;
    List<BigInteger> sequence = new ArrayList<>();
    boolean printed = false;

    Sequence(final int id, final int count, SequenceGenerator sg) {
        this.count = count;
        this.id = id;
        this.sg = sg;
        thread = new Thread(this);

        System.out.println("Создан поток " + id);
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
                sequence.add(sg.next());
                Thread.sleep((long) (
                        (Math.random() * 2 + 1) * 30));
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + id
                    + " прерван");
        }
        System.out.print("Поток " + id + " завершён");
        printSequence();
    }

    public void printSequence() {
        if (printed)
            return;
        String tmp = "[";
        for (int i = 0; i < sequence.size(); i++) {
            if (i > 0)
                tmp += ", ";
            String nb = String.valueOf(sequence.get(i));
            while (nb.length() < 9)
                nb = " " + nb;
            tmp += nb;
        }
        tmp += "]";
        System.out.println("Последовательность потока "
                + id + " : " + tmp);
        printed = true;
    }
}

class SequenceGenerator {
    private static BigInteger MULTIPLIER;
    private AtomicReference<BigInteger> element;

    public SequenceGenerator() {
        if (MULTIPLIER == null) {
            MULTIPLIER = BigInteger.valueOf(2);
        }
        element = new AtomicReference<>(BigInteger.ONE);
    }

    public BigInteger next() {
        BigInteger value;
        BigInteger next;
        do {
            value = element.get();
            next = value.multiply(MULTIPLIER);
        } while (!element.compareAndSet(value, next));
        return value;
    }
}