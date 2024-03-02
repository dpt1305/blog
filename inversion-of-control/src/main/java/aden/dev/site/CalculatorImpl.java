package aden.dev.site;

import aden.dev.site.anotation.AdenInstance;

@AdenInstance
public class CalculatorImpl implements Calculator {
    public CalculatorImpl() {

    }

    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
