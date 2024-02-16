package aden.dev.site;

import aden.dev.site.anotation.Instance;

@Instance
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
