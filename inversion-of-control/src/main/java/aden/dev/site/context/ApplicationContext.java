package aden.dev.site.context;

import aden.dev.site.anotation.AdenInstance;
import aden.dev.site.reflection.ReflectionHelper;
import aden.dev.site.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.*;

public class ApplicationContext {
    private static final Map<String, Object> instanceByName = new HashMap<>();
    private static final Map<Class<?>, List<Object>> instanceByType = new HashMap<>();

    private static final Set<Class<?>> classSet = new HashSet<>();

    private ApplicationContext() {

    }

    public static void run(Class<?> applicationClazz) {
        try {
            ApplicationContext.classSet.addAll(ReflectionHelper.findAllClassesUsingClassLoader(applicationClazz));

            for (Class<?> clazz : ApplicationContext.classSet) {
                if (clazz.isAnnotationPresent(AdenInstance.class) && !clazz.isInterface()) {
                    AdenInstance annotation = clazz.getAnnotation(AdenInstance.class);
                    String instanceName = annotation.name().isEmpty()
                            ? StringUtils.lowercaseFirstChar(clazz.getSimpleName())
                            : annotation.name();

                    Object newInstance = (Object) clazz.getConstructor().newInstance();

                    ApplicationContext.addInstanceByType(clazz, newInstance);
                    ApplicationContext.instanceByName.put(instanceName, newInstance);
                    for (Class<?> interfaceClass : clazz.getInterfaces()) {
                        ApplicationContext.addInstanceByType(interfaceClass, newInstance);
                    }
                }
            }
            for(Object instance: ApplicationContext.instanceByName.values()) {
                ReflectionHelper.setInstanceToProperty(instance, ApplicationContext.instanceByType, ApplicationContext.instanceByName);
            }
            System.out.println("debug");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addInstanceByType(Class<?> type, Object object) {
        if(ApplicationContext.instanceByType.containsKey(type)) {

            List<Object> instanceList =  ApplicationContext.instanceByType.get(type);
            instanceList.add(object);

        } else {
            List<Object> newArray = new ArrayList<>();
            newArray.add(object);
            ApplicationContext.instanceByType.put(type, (List<Object>) newArray);
        }
    }

    public static <T extends Object> T getInstance(Class<?> clazz) throws ClassNotFoundException {
        if(ApplicationContext.instanceByType.containsKey(clazz) == false) {
            throw new ClassNotFoundException();
        }

        List<Object> instanceListByType = ApplicationContext.instanceByType.get(clazz);
        if(instanceListByType.size() > 1) {
            String simpleClazzName = StringUtils.lowercaseFirstChar(clazz.getSimpleName());
            if (ApplicationContext.instanceByName.containsKey(simpleClazzName)) {
                return (T) ApplicationContext.instanceByName.get(simpleClazzName);
            } else {
                throw new ClassNotFoundException();
            }
        } else {
            return (T) instanceListByType.get(0);
        }
    }

    public static <T extends Object> T getInstance(String simpleClazzName) throws ClassNotFoundException {
        if (ApplicationContext.instanceByName.containsKey(simpleClazzName)) {
            return (T) ApplicationContext.instanceByName.get(simpleClazzName);
        } else {
            throw new ClassNotFoundException();
        }
    }

}
