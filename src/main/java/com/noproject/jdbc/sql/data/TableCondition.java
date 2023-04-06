/*
 * 
 */
package com.noproject.jdbc.sql.data;

import com.noproject.jdbc.sql.annotations.MapToTable;

/**
 *
 * @author DELL
 */
public class TableCondition {

    //
    private String tableName;
    //
    private ColumnCondition columnCondition = null;
    private RowCondition/**/ rowCondition = null;

    protected TableCondition() {
    }

    public TableCondition(String table) {
        if (table == null || table.isEmpty()== true) {
            throw new NullPointerException();
        }
        this.tableName = table;
    }

    public TableCondition(Class table) {
        if (table == null) {
            throw new NullPointerException();
        }
        MapToTable mapTable = (MapToTable) table.getAnnotation(MapToTable.class);
        this.tableName = mapTable.value();
    }

    public TableCondition(ColumnCondition columnConditions, RowCondition rowConditions) {
        this.columnCondition = columnConditions;
        this.rowCondition = rowConditions;
    }

    public TableCondition(String table, ColumnCondition columnConditions, RowCondition rowConditions) {
        if (table == null || table.isEmpty()== true) {
            throw new NullPointerException();
        }
        this.tableName = table;
        this.columnCondition = columnConditions;
        this.rowCondition = rowConditions;
    }

    public TableCondition(Class table, ColumnCondition columnConditions, RowCondition rowConditions) {
        if (table == null) {
            throw new NullPointerException();
        }
        MapToTable mapTable = (MapToTable) table.getAnnotation(MapToTable.class);
        this.tableName = mapTable.value();
        this.columnCondition = columnConditions;
        this.rowCondition = rowConditions;
    }

    public TableCondition columnConditions(ColumnCondition conditions) {
        this.columnCondition = conditions;
        return this;
    }

    public TableCondition rowConditions(RowCondition conditions) {
        this.rowCondition = conditions;
        return this;
    }

    public TableCondition conditions(ColumnCondition columnConditions, RowCondition rowConditions) {
        return columnConditions(columnConditions).rowConditions(rowConditions);
    }

    protected String getTableName() {
        return tableName;
    }

    protected ColumnCondition getColumnConditions() {
        return columnCondition != null ? columnCondition : new ColumnCondition();
    }

    protected RowCondition getRowConditions() {
        return rowCondition != null ? rowCondition : new RowCondition();
    }

}
