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
 * Ghi chú một class trong java đang liên kết với table nào trong database.
 *
 * @author DELL
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MapToTable {

    String value();
}
