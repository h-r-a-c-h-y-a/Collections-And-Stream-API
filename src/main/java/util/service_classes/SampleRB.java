package util.service_classes;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class SampleRB extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] resources = new Object[3][2];
        resources[0][1] = "title";
        resources[0][1] = "My program";

        resources[1][0] = "StopText";
        resources[1][1] = "Stop";

        resources[2][0] = "StartText";
        resources[2][1] = "Start";
        return resources;
    }
}

class SampleRB_de extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        Object[][] resources = new Object[3][2];
        resources[0][1] = "title";
        resources[0][1] = "Mein program";

        resources[1][0] = "StopText";
        resources[1][1] = "Anschlag";

        resources[2][0] = "StartText";
        resources[2][1] = "Anfang";
        return resources;
    }
}

class LRBDemo{
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale.getCountry());
        System.out.println(locale.getDisplayCountry());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(locale.getDisplayName());
        System.out.println(locale.getDisplayScript());
        System.out.println(locale.getDisplayVariant());
        System.out.println(locale.getVariant());
        System.out.println(locale.toString());
        System.out.println(locale.toLanguageTag());
        ResourceBundle rd = ResourceBundle.getBundle("SampleRB");
        System.out.println("program english version: ");
        System.out.println("content by key Title: " + rd.getString("title"));
        System.out.println("content by key StopText: " + rd.getString("StopText"));
        System.out.println("content by key StartText: " + rd.getString("StartText"));

        ResourceBundle rd1 = ResourceBundle.getBundle("SampleRB_de", Locale.GERMAN);
        System.out.println("program english version: ");
        System.out.println("content by key Title: " + rd1.getString("title"));
        System.out.println("content by key StopText: " + rd1.getString("StopText"));
        System.out.println("content by key StartText: " + rd1.getString("StartText"));


    }
}
