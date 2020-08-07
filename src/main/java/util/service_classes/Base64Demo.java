package util.service_classes;

import java.util.Base64;
import java.util.UUID;

public class Base64Demo {
    public static void main(String[] args) {
//        String originalInput = "test input";
//        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//        System.out.println(originalInput);
//        System.out.println(encodedString);
//        System.out.println();
//        byte[] content = Base64.getDecoder().decode(encodedString);
//        encodedString = new String(content);
//        System.out.println(originalInput);
//        System.out.println(encodedString);
//        System.out.println();
//        encodedString = Base64.getEncoder().withoutPadding().encodeToString(originalInput.getBytes());
//        System.out.println(originalInput);
//        System.out.println(encodedString);
//        System.out.println();
//        content = Base64.getDecoder().decode(encodedString);
//        encodedString = new String(content);
//        System.out.println(originalInput);
//        System.out.println(encodedString);

        String originalURL ="https://drive.google.com/file/d/16kAsrizvahsWKWFlszECOlO7kjbZIZ-8/view";
        String encodedURL = Base64.getUrlEncoder().encodeToString(originalURL.getBytes());
        System.out.println(originalURL);
        System.out.println(encodedURL);
        byte[] content = Base64.getUrlDecoder().decode(encodedURL);
        encodedURL = new String(content);
        System.out.println(originalURL);
        System.out.println(encodedURL);
        String mimeContent = getMimeBuffer().toString();
        System.out.println(mimeContent);
        System.out.println();
        mimeContent = Base64.getMimeEncoder().encodeToString(mimeContent.getBytes());
        System.out.println(mimeContent);
        System.out.println();
        content = Base64.getMimeDecoder().decode(mimeContent);
        mimeContent = new String(content);
        System.out.println(mimeContent);
    }

    private static StringBuilder getMimeBuffer(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(UUID.randomUUID().toString());
        }
        return sb;
    }
}
