package aden.dev.site;

import aden.dev.site.context.ApplicationContext;
import aden.dev.site.reflection.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static void main(String[] args) {
        ApplicationContext.run(Main.class);
        try {
            Student student = (Student) ApplicationContext.getObject(Student.class);
            int result = student.getCalculator().sum(1, 2);
            System.out.println("Student calculate result : " + result);
            System.out.println("Student has Ruler: " + student.getRuler().getLENGTH() + student.getRuler().getMETRIC());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}