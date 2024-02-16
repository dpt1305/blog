package aden.dev.site;

import aden.dev.site.anotation.Instance;

@Instance
public class Calculator {
    public Calculator() {

    }

    public int sum(int a, int b) {
        return a + b;
    }
}
