package aden.dev.site.reflection;

import aden.dev.site.anotation.Inject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectionHelper {

    public static <T> void injectObjectToProperty(Class<?> clazz, Map<Class<?>, Object> contextObject, Map<String, Object> contextByName)
            throws IllegalAccessException {
        Object object = contextObject.get(clazz);

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Inject annotation = field.getAnnotation(Inject.class);
                String nameInstance = annotation.name();

                field.setAccessible(true);
                Class<?> propertyClazz = field.getType();

                if (nameInstance.equals("")) {
                    field.set(object, contextObject.get(propertyClazz));
                } else {
                    field.set(object, contextByName.get(nameInstance) );
                }
            }
        }
    }

    public static Set<Class<?>> findAllClassesUsingClassLoader(Class<?> application) {
        String packageName = application.getPackageName();

        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> line.replaceAll(".class", ""))
                .map((className) -> {
                    try {
                        return Class.forName(packageName + "." + className);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
