package aden.dev.site.context;

import aden.dev.site.anotation.Instance;
import aden.dev.site.reflection.ReflectionHelper;

import java.util.*;

public class ApplicationContext {
    private static final Map<Class<?>, Object> contextObject = new HashMap<>();
    private static final Set<Class<?>> classSet = new HashSet<>();

    private ApplicationContext() {

    }

    public static void run(Class<?> applicationClazz) {
        try {
            ApplicationContext.classSet.addAll(ReflectionHelper.findAllClassesUsingClassLoader(applicationClazz));

            for (Class<?> clazz : ApplicationContext.classSet) {
                if (clazz.isAnnotationPresent(Instance.class)) {
                    Object newObject = clazz.getConstructor().newInstance();
                    ApplicationContext.contextObject.put(clazz, newObject);
                }
            }
            for (Class<?> clazz : ApplicationContext.classSet) {
                ReflectionHelper.injectObjectToProperty(clazz, ApplicationContext.contextObject);
            }
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
