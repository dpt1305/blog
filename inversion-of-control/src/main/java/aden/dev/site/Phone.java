package aden.dev.site;

import aden.dev.site.anotation.InjectInstance;
import aden.dev.site.anotation.AdenInstance;

@AdenInstance
public class Phone {
    @InjectInstance
    private IBattery battery;

    @InjectInstance(name = "samsungCamera")
    private ICamera camera;

    @InjectInstance
    private IScreen screen;


    public Phone() {
    }

    public IBattery getBattery() {
        return battery;
    }

    public ICamera getCamera() {
        return camera;
    }

    public IScreen getScreen() {
        return screen;
    }

//    @Override
//    public String toString() {
//        return "Phone{" +
//                "battery=" + battery.getManufactory() +
//                ", camera=" + camera.getCameraCompany() +
//                ", screen=" + screen.getScreen() +
//                '}';
//    }
}
