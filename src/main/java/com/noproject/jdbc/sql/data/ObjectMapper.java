/*
 */
package com.noproject.jdbc.sql.data;

import com.noproject.jdbc.sql.annotations.MapToColumn;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author No
 * @param <T>
 */
public class ObjectMapper<T> {

    private final ResultRow resultRow;
    private final Class<T> classObject;
    private final T object;

    public ObjectMapper(T object) {
        this.object = object;
        this.resultRow = new ResultRow(0);
        this.classObject = (Class<T>) this.object.getClass();
    }

    public ObjectMapper(Class<T> obj, ResultRow row) {
        this.classObject = obj;
        this.resultRow = row;
        this.object = getGenericInstance();
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
            MapToColumn anno = field.getAnnotation(MapToColumn.class);
            if (anno != null && anno.value().isEmpty()== false) {
                result.add(field.getName());
            }
        });
        return result;
    }

    public final T getObject() {
        getFieldNames().forEach((fieldName) -> {
            try {
                Field field = classObject.getDeclaredField(fieldName);
                MapToColumn anno = field.getAnnotation(MapToColumn.class);
                field.setAccessible(true);
                field.set(object, resultRow.getDataOfColumn(anno.value()));
            } catch (NoSuchFieldException | SecurityException
                    | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ObjectMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return object;
    }

    public ResultRow getResultRow() {
        getFieldNames().forEach((fieldName) -> {
            try {
                Field field = classObject.getDeclaredField(fieldName);
                MapToColumn anno = field.getAnnotation(MapToColumn.class);
                field.setAccessible(true);
                resultRow.addData(anno.value(), field.get(object));
            } catch (NoSuchFieldException | SecurityException
                    | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(ObjectMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return resultRow;
    }
}
