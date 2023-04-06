/*
 * 
 */
package com.noproject.jdbc.sql.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ghi chú các field của class trong java tương ứng với các cột trong database.
 *
 * @author DELL
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MapToColumn {

    String value();
}
