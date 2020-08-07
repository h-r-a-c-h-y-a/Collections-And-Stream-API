package util.service_classes;

import java.util.StringTokenizer;

public class StringTokenizerDemo {
    static String in = "Xrefs  are string with a specific format used for referencing objects internal and external to\n" +
            "a package. An xref with the format \"#id\" references an object within the current package with\n" +
            "the given ID. Xrefs can also have the format \"file#id\", where \"file\" is the       name of package file\n" +
            "(relative to the location of the current package) and \"id\" is the unique identifier of an object\n" +
            "in that bundle." ;

    public static void main(String[] args) {
        StringTokenizer st = new StringTokenizer(in, ".,;\"# ");
        System.out.println(st.countTokens());
//        System.out.println(st.nextElement());
//        System.out.println(st.nextToken());
        while (st.hasMoreElements()) {
//            String key = st.nextToken();
//            String val = st.nextToken();
            System.out.println(st.nextToken() + "\n" + st.nextElement());
        }
    }
}
