package aden.dev.site;

import aden.dev.site.anotation.AdenInstance;

@AdenInstance
public class AppleBattery implements IBattery{

    @Override
    public String getManufactory() {
        return "Apple";
    }
}
