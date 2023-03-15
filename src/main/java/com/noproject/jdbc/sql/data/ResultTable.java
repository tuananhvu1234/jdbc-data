/*
 * 
 */
package com.noproject.jdbc.sql.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 *
 * @author DELL
 */
public class ResultTable {

    private final String tableName;
    private final List<ResultRow> rows;
    private final List<String> columns;

    protected ResultTable(String tableName, List<String> cols) {
        this.tableName = tableName;
        this.rows = new ArrayList<>();
        this.columns = cols;
    }

    protected void addRow(ResultRow row) {
        rows.add(row);
    }

    public List<ResultRow> rows() {
        return rows;
    }

    public ResultRow rows(int index) {
        return rows.get(index);
    }

    public ResultRow getRow(int rowId) {
        Optional<ResultRow> optional = rows.stream().filter(row -> row.getRowId() == rowId).findFirst();
        if (optional.isEmpty() == false) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("table=").append(tableName);
        builder.append("\ncolumns=").append(columns);
        builder.append("\nsize=").append(rows.size());
        builder.append("\nrows=");
        StringJoiner joiner = new StringJoiner(",\n", "[\n", "\n]");
        rows.stream().forEach(row -> {
            joiner.add("\t" + row.toString());
        });
        builder.append(joiner.toString());
        return builder.toString();
    }

}
