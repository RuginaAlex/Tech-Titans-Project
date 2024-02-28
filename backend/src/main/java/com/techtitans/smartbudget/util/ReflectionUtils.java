package com.techtitans.smartbudget.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

public class ReflectionUtils {
    public static HashMap<String, Object> getMapFromObject(Object object, Class<? extends Annotation> annotation) {
        HashMap<String, Object> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(annotation)) {
                field.setAccessible(true);
                try {
                    var value = field.get(object);
                    map.put(field.getName(), value);
                } catch (
                        IllegalAccessException e) {
                    // will not be thrown due to having the accessibility changed before performing the get
                }
            }
        }
        return map;
    }
}
