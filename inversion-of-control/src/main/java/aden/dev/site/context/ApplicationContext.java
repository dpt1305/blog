package aden.dev.site.context;

import aden.dev.site.anotation.AdenInstance;
import aden.dev.site.reflection.ReflectionHelper;

import java.lang.annotation.Annotation;
import java.util.*;

public class ApplicationContext {
    private static final Map<Class<?>, Object> contextObject = new HashMap<>();
    private static final Map<String, Object> instanceByName = new HashMap<>();
    private static final Set<Class<?>> classSet = new HashSet<>();

    private ApplicationContext() {

    }

    public static void run(Class<?> applicationClazz) {
        try {
            ApplicationContext.classSet.addAll(ReflectionHelper.findAllClassesUsingClassLoader(applicationClazz));

            for (Class<?> clazz : ApplicationContext.classSet) {
                if (clazz.isAnnotationPresent(AdenInstance.class) && !clazz.isInterface()) {
                    AdenInstance annotation = clazz.getAnnotation(AdenInstance.class);
                    String instanceName = annotation.name();

                    Object newInstance = clazz.getConstructor().newInstance();

                    ApplicationContext.contextObject.put(clazz, newInstance);
                    ApplicationContext.instanceByName.put(instanceName, newInstance);
                    for (Class<?> interfaceClass : clazz.getInterfaces()) {
                        ApplicationContext.contextObject.put(interfaceClass, newInstance);
                    }
                }
            }
            for (Class<?> clazz : ApplicationContext.classSet) {
                ReflectionHelper.injectObjectToProperty(clazz, ApplicationContext.contextObject, ApplicationContext.instanceByName);
            }
            System.out.println("debug");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getObject(Class<?> clazz) throws Exception {
        Object object = ApplicationContext.contextObject.get(clazz);
        if (object != null) {
            return object;
        } else {
            throw new Exception();
        }
    }
}
