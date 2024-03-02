package aden.dev.site;

import aden.dev.site.context.ApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext.run(Main.class);
        try {
            Phone iPhone = (Phone) ApplicationContext.getInstance(Phone.class);
            System.out.println(
                    "Phone{" +
                            "battery=" + iPhone.getBattery().getManufactory() +
                            ", camera=" + iPhone.getCamera().getCameraCompany() +
                            ", screen=" + iPhone.getScreen().getScreen() +
                            '}');
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}