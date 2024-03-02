package aden.dev.site;

import aden.dev.site.context.ApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext.run(Main.class);
//        Phone iPhone = (Phone) ApplicationContext.getObject(Phone.class);
        try {
            Phone iPhone = (Phone) ApplicationContext.getObject(Phone.class);
            System.out.println(iPhone.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}