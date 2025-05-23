package com.school.utils;

import java.lang.reflect.Field;
import java.util.List;

public class BaseControllerActions {
    public void requires(List<String> fields, Object requestBody) {
        if (requestBody == null) {
            throw new IllegalArgumentException("Request body is null");
        }
        for (String field : fields) {
            try{
                Field f = requestBody.getClass().getDeclaredField(field);
                f.setAccessible(true);
                Object value = f.get(requestBody);
                if(value == null || (value instanceof String str && str.isBlank())) {
                    throw new IllegalArgumentException(field + " field is required");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Missing or null field: " + field);
            }
        }
    }
}
