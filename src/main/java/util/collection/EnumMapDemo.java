package util.collection;

import java.util.EnumMap;

public class EnumMapDemo {
    public enum DayOfWeek{
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public static void main(String[] args) {
        EnumMap<DayOfWeek, String> questionsToWeek = new EnumMap<DayOfWeek, String>(DayOfWeek.class);
        questionsToWeek.put(DayOfWeek.THURSDAY, "work work");
        questionsToWeek.put(DayOfWeek.SUNDAY, "relax in Bali");
        questionsToWeek.put(DayOfWeek.WEDNESDAY, "work work");
        questionsToWeek.put(DayOfWeek.TUESDAY, "go to work place");
        questionsToWeek.put(DayOfWeek.FRIDAY, "work work");
        questionsToWeek.put(DayOfWeek.SATURDAY, "take ticket to Bali");
        questionsToWeek.put(DayOfWeek.MONDAY, "Take care from park");
        System.out.println(questionsToWeek.keySet() + " " + questionsToWeek.values());
        System.out.println(questionsToWeek);
    }

}
