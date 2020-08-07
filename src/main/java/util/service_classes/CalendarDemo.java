package util.service_classes;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarDemo {
    public static void main(String[] args) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                           "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Calendar calendar = Calendar.getInstance();
        System.out.print("Date: ");
        System.out.print(" " + calendar.get(Calendar.DATE) + " ");
        System.out.print(months[calendar.get(Calendar.MONTH)] + " ");
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.print("Time: ");
        System.out.print(calendar.get(Calendar.HOUR) + ":");
        System.out.print(calendar.get(Calendar.MINUTE) + ":");
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println();

        calendar.set(Calendar.HOUR, 11);
        calendar.set(Calendar.MINUTE, 53);
        calendar.set(Calendar.SECOND, 8);
        System.out.print("changed time: ");
        System.out.print(calendar.get(Calendar.HOUR) + ":");
        System.out.print(calendar.get(Calendar.MINUTE) + ":");
        System.out.println(calendar.get(Calendar.SECOND));
    }
}
