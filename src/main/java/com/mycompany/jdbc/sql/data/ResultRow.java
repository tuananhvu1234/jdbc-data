/*
 *
 */
package com.mycompany.jdbc.sql.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class ResultRow {

    private final int rowId;
    private final Map<String, Object> rowData;

    protected ResultRow(int id) {
        this.rowId = id;
        this.rowData = new LinkedHashMap<>();
    }

    protected void addData(String column, Object data) {
        rowData.put(column, data);
    }

    public int getRowId() {
        return rowId;
    }

    public Map<String, Object> getData() {
        return rowData;
    }

    public Object getDataOfColumn(String columnName) {
        return rowData.get(columnName);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("rowId=").append(rowId);
        builder.append(", data=");
        builder.append(rowData.toString());
        return builder.toString();
    }

}
