package aden.dev.site;

import aden.dev.site.anotation.Instance;
import aden.dev.site.enums.InstanceType;

import java.util.Arrays;

@Instance
public class Calculator {
    public Calculator() {

    }
    public int sum(int a, int b) {
        return a + b;
    }
}
