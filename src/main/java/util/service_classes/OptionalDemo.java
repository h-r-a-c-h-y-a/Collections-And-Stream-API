package util.service_classes;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> noval = Optional.empty();
        Optional<String> hasval = Optional.of("ABCDEFG");
        // if(noval.isPresent()) System.out.println("nothing to output.");
        noval.ifPresent(v -> System.out.println("nothing to output."));
        noval.ifPresent(System.out::println);
        System.out.println(noval.orElse("object noval not contain value."));
        //if (hasval.isPresent()) System.out.println("hasval contain value: " + hasval.get());
        hasval.filter(val -> val.equals("ABCDEFG"))
                .map(val -> val = val.substring(2))
                .ifPresent(System.out::println);
        String defStr = noval.orElse("GFEDCBA");
        System.out.println(defStr);
    }
}
