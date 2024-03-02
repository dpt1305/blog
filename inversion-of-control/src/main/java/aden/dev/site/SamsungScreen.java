package aden.dev.site;

import aden.dev.site.anotation.AdenInstance;

@AdenInstance
public class SamsungScreen implements  IScreen{
    @Override
    public String getScreen() {
        return "Samsung";
    }
}
