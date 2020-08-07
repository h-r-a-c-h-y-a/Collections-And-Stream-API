package util.concurrent.executors;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.*;

public class ScheduledExecutorRepeat {
    private static int count = 0;

    public static void main(String[] args)
            throws InterruptedException {
        ScheduledExecutorService ses =
                Executors.newScheduledThreadPool(1);
        Runnable task1 = () -> {
            count++;
            System.out.println("Running...task1 - count : " + count);
        };

        ScheduledFuture<?> scheduledFuture =
                ses.scheduleAtFixedRate(task1, 3, 1, TimeUnit.SECONDS);

        while (true){
            System.out.println("count : " + count);
//               scheduledFuture.cancel(true);
            Thread.sleep(1000);
            if (count == 5){
                scheduledFuture.cancel(true);
                if (scheduledFuture.isCancelled()){
                ses.shutdown();
                }
                break;
            }
        }
    }
}

class ScheduledTimerDemo {
    public static void main(String[] args)
            throws ExecutionException,
            InterruptedException {
        ScheduledExecutorService service =
                Executors.newScheduledThreadPool(1);
        Callable<String> timer = () -> {
            while (LocalTime.now().getMinute() != 45) {
                return LocalTime.now()
                        .format(DateTimeFormatter
                                .ofPattern("hh:mm:ss"));
            }
            service.shutdown();
            return LocalTime.now()
                    .format(DateTimeFormatter
                            .ofPattern("hh:mm:ss"));
        };

        while (!service.isShutdown())
        System.out.println(service.schedule(timer, 1,
                TimeUnit.SECONDS).get());
    }
}
