package org.example.demo;

import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.beans.beancontext.BeanContext;
import java.util.HashMap;
import java.util.Map;

public class CustomContext {
    private Map<String, Object> beanMap = new HashMap<>();


    public CustomContext (){
        init();
    }

    private void init(){
        //scan current package
        // find all classes that requires bean creation
        // create object for each class
        //add objcet to the beanMap
        var currentPackage = getClass().getPackage().getName();
        var reflections = new Reflections(currentPackage);
        reflections.getTypesAnnotatedWith(Bean.class)
                .stream()
                .forEach(type -> registerBean(type));
    }

    @SneakyThrows
    private void registerBean(Class<?> type) {
        var beanAnnotation =type.getAnnotation(Bean.class);
        var beanId = beanAnnotation.value();
        var constructor = type.getConstructor();
        var beanInstance = constructor.newInstance();
        beanMap.put(beanId, beanInstance);
    }

    public<T> T getBean(Class<T> type) {
        return beanMap.values()
                .stream()
                .filter(type::isInstance)
                .findAny()
                .map(type::cast)
                .orElseThrow();
    }
}
