/*
 */
package com.mycompany.jdbc.sql.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 * @param <T>
 */
public class ObjectMapper<T> {

    private final ResultRow resultRow;
    private final Class<T> classObject;

    public ObjectMapper(Class<T> obj, ResultRow row) {
        this.classObject = obj;
        this.resultRow = row;
    }

    private T getGenericInstance() {
        try {
            return classObject.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException
                | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ObjectMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private List<String> getFieldNames() {
        List<String> result = new ArrayList<>();
        Arrays.asList(classObject.getDeclaredFields()).forEach(field -> {
            if (field.getAnnotation(MapToColumn.class) != null) {
                result.add(field.getName());
            }
        });
        return result;
    }

    public final T getObject() {
        T resultObject = getGenericInstance();
        getFieldNames().forEach((fieldName) -> {
            try {
                Field field = classObject.getDeclaredField(fieldName);
                MapToColumn anno = field.getAnnotation(MapToColumn.class);
                field.setAccessible(true);
                field.set(resultObject, resultRow.getDataOfColumn(anno.name()));
            } catch (NoSuchFieldException | SecurityException
                    | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ObjectMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return resultObject;
    }

}
