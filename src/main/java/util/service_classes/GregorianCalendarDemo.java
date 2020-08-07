package util.service_classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregorianCalendarDemo {
    public static void main(String[] args) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int year;
        GregorianCalendar gCalendar = new GregorianCalendar();
        System.out.print("Date: ");
        System.out.print(" " + gCalendar.get(Calendar.DATE) + " ");
        System.out.print(months[gCalendar.get(Calendar.MONTH)] + " ");
        System.out.println(year = gCalendar.get(Calendar.YEAR));
        System.out.print("Time: ");
        System.out.print(gCalendar.get(Calendar.HOUR) + ":");
        System.out.print(gCalendar.get(Calendar.MINUTE) + ":");
        System.out.println(gCalendar.get(Calendar.SECOND));
        System.out.println();
        if (gCalendar.isLeapYear(year)){
            System.out.println(year + " is leap");
        }else System.out.println(year + " is not leap");
    }
}
