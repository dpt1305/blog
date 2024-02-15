package aden.dev.site;

import aden.dev.site.anotation.Inject;
import aden.dev.site.anotation.Instance;

@Instance
public class Student {

    @Inject
    private Calculator calculator;

    @Inject
    private Ruler ruler;

    public Student() {}

    public int sumInput(Integer... args) {
        int sum = 0;
        for (Integer arg: args
             ) {
            if( arg != null) {
                sum = calculator.sum(arg, sum);
            }
        }
        return sum;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }

    public Ruler getRuler() {
        return ruler;
    }
}
