package aden.dev.site;

import aden.dev.site.anotation.AdenInstance;

@AdenInstance
public class SonyCamera implements ICamera {
    @Override
    public String getCameraCompany() {
        return "Sony";
    }
}
