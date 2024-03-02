package aden.dev.site;

import aden.dev.site.anotation.AdenInstance;

@AdenInstance
public class Ruler {

    private int LENGTH = 10;
    private String METRIC = "CM";

    public int getLENGTH() {
        return LENGTH;
    }

    public String getMETRIC() {
        return METRIC;
    }
}
