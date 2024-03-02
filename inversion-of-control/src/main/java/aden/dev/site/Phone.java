package aden.dev.site;

import aden.dev.site.anotation.Inject;
import aden.dev.site.anotation.AdenInstance;

@AdenInstance
public class Phone {
    @Inject
    private IBattery battery;

    @Inject
    private ICamera camera;

    @Inject
    private IScreen screen;


    public Phone() {
    }

    @Override
    public String toString() {
        return "Phone{" +
                "battery=" + battery.getManufactory() +
                ", camera=" + camera.getCameraCompany() +
                ", screen=" + screen.getScreen() +
                '}';
    }
}
