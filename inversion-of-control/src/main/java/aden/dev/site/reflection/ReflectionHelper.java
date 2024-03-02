package aden.dev.site.reflection;

import aden.dev.site.anotation.InjectInstance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectionHelper {

    public static <T> void setInstanceToProperty(Object instance, Map<Class<?>, List<Object>> instanceByType, Map<String, Object> instanceByName) throws ClassNotFoundException, IllegalAccessException {
        Class<?> clazz = instance.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectInstance.class)) {
                InjectInstance annotation = field.getAnnotation(InjectInstance.class);
                String nameInstance = annotation.name();

                field.setAccessible(true);

                Class<?> propertyClazz = field.getType();

                List<Object> instanceList = (List<Object>) instanceByType.get(propertyClazz);

                if(nameInstance.isEmpty() && instanceList.size()>1) {
                    throw new ClassNotFoundException("Can not find suitable instance for " + propertyClazz.getSimpleName());
                }
                if (nameInstance.isEmpty()) {
                    field.set(instance, instanceList.get(0));
                } else {
                    field.set(instance, instanceByName.get(nameInstance) );
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
