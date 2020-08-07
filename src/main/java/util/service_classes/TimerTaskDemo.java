package util.service_classes;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskDemo {
    public static void main(String[] args) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task  executing by timer");
            }
        };
        Timer timer = new Timer();
        timer.schedule( task,1000, 500);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            timer.cancel();
        }
    }
}
