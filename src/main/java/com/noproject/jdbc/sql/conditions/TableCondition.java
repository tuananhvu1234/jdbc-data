/*
 * 
 */
package com.noproject.jdbc.sql.conditions;

import com.noproject.jdbc.sql.annotations.MapToTable;

/**
 *
 * @author No
 */
public class TableCondition {

    // Tên bảng
    private final String name;

    public TableCondition(String table) {
        this.name = table;
    }

    public TableCondition(Class table) {
        MapToTable mapper = (MapToTable) table.getAnnotation(MapToTable.class);
        this.name = mapper.value();
    }

    public String getName() {
        return name;
    }

}
