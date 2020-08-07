package util.concurrent.synhronizers;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class PhaserDemo {
    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);
        int curPhase;
        System.out.println("threads start");
        new MyThread3(phaser, "first");
        new MyThread3(phaser, "second");
        new MyThread3(phaser, "third");
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " finished");
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " finished");
        curPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + curPhase + " finished");
        phaser.arriveAndDeregister();
        if (phaser.isTerminated()) {
            System.out.println("Synhronizer phase finished");
        }
    }
}

class MyThread3 implements Runnable {
    Phaser phaser;
    String name;

    public MyThread3(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Thread " + name + " start first phase");
        phaser.arrive();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + name + " start second phase");
        phaser.arrive();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + name + " start third phase");
        phaser.arriveAndDeregister();
    }
}

class MyPhaser extends Phaser {

    int numPhases;

    public MyPhaser(int parties, int phaseCount) {
        super(parties);
        numPhases = phaseCount;
    }

    @Override
    protected boolean onAdvance(int p, int regParties) {
        System.out.println("Phase " + p + " is over.\n");
        return p == numPhases || regParties == 0;
    }
}

class PhaserDemo2 {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser(1, 4);
        System.out.println("Threads start!\n");
        new MyThread4(phaser, "first");
        new MyThread4(phaser, "second");
        new MyThread4(phaser, "third");
        while (!phaser.isTerminated()){
            phaser.arriveAndAwaitAdvance();
        }
        System.out.println("Phase synhronizer over");
    }
}

class MyThread4 implements Runnable {

    Phaser phaser;
    String name;

    public MyThread4(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (!phaser.isTerminated()) {
            System.out.println("Thread " + name + " start phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance();
            try {
                System.out.println("Thread " + name + " work");
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class Bus {
    //Сразу регистрируем главный поток
    private static final Phaser PHASER = new Phaser(1);
    //Фазы 0 и 6 - это автобусный парк, 1 - 5 остановки

    public static void main(String[] args) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i < 5; i++) {//Сгенерируем пассажиров на остановках
            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, i + 1));//Этот пассажир выходит на следующей
            }
            if ((int) (Math.random() * 2) > 0) {
                passengers.add(new Passenger(i, 5)); //Этот пассажир выходит на конечной
            }
        }
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("Bus start race at park");
                    PHASER.arrive();//В фазе 0 всего 1 участник - автобус
                    break;
                case 6:
                    System.out.println("Bus finish race and go to park.");
                    PHASER.arriveAndDeregister();//Снимаем главный поток, ломаем барьер
                    break;
                default:
                    int currentBusStop = PHASER.getPhase();
                    System.out.println("Departure № " + currentBusStop);

                    for (Passenger p : passengers)          //Проверяем, есть ли пассажиры на остановке
                        if (p.departure == currentBusStop) {
                            PHASER.register();//Регистрируем поток, который будет участвовать в фазах
                            p.start();        // и запускаем
                        }

                    PHASER.arriveAndAwaitAdvance();//Сообщаем о своей готовности
            }
        }
    }

    static class Passenger extends Thread {

        private int departure;
        private int destination;

        public Passenger(int departure, int destination) {
            this.departure = departure;
            this.destination = destination;
            System.out.printf("%s wait in departure №%d%n", this, this.departure);
        }

        @Override
        public void run() {
            System.out.println(this + " sit on bus.");
            while (PHASER.getPhase() < destination) {
                PHASER.arriveAndAwaitAdvance(); //заявляем в каждой фазе о готовности и ждем
            }
            try {
                Thread.sleep(1);
                System.out.println(this + " exit out of bus.");//Пока автобус не приедет на нужную остановку(фазу)
                PHASER.arriveAndDeregister();//Отменяем регистрацию на нужной фазе
            } catch (InterruptedException e) {
            }

        }

        @Override
        public String toString() {
            return "Passenger{" + departure + " -> " + destination + '}';
        }
    }
}

class PhaserExample {
    public static void main(String[] args) throws InterruptedException{
        Phaser phaser = new Phaser();
        ExecutorService ex = Executors.newFixedThreadPool(4);
        ex.execute(() -> {
            phaser.register();
            System.out.println("first thread start phase 1");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(50) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndDeregister();
            System.out.println("first thread finished phase");
        });
        ex.execute(() -> {
            phaser.register();
            System.out.println("second thread start phase 1");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(50) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("second thread start phase 2");
            phaser.arriveAndDeregister();
            System.out.println("second thread finished phase");
        });
        ex.execute(() -> {
            phaser.register();
            System.out.println("third thread start phase 1");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(50) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arrive();
            System.out.println("third thread start phase 2");
            phaser.arriveAndDeregister();
            System.out.println("third thread finished phase");
        });
        ex.execute(() -> {
            phaser.register();
            System.out.println("fourth thread start phase 1");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(50) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();
            System.out.println("fourth thread start phase 2");
            phaser.arriveAndDeregister();
            System.out.println("fourth thread finished phase");
        });
        ex.shutdown();
    }
}
