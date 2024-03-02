package aden.dev.site;

import aden.dev.site.anotation.AdenInstance;

@AdenInstance(name = "samsungCamera")
public class SamsungCamera implements ICamera{

    @Override
    public String getCameraCompany() {
        return "Samsung";
    }
}
