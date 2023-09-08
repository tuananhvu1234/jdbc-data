/*
 * 
 */
package com.noproject.jdbc.sql.data;

import com.noproject.jdbc.helper.StringEditor;
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
        if (optional.isPresent() == true) {
            return optional.get();
        } else {
            return null;
        }
    }

    private String resultJson = "";

    @Override
    public String toString() {
//        resultJson = StringEditor.convertOneString(resultJson, "@tableName", tableName);
//        StringJoiner joiner = new StringJoiner("\",\n    \"", "\"", "\"");
//        for (String column : columns) {
//            joiner.add(column);
//        }
//        resultJson = StringEditor.convertOneString(resultJson, "@listColuumns", joiner.toString());
//        joiner = new StringJoiner(",\n");
//        for (ResultRow row : rows) {
//            joiner.add(row.toString());
//        }
//        String join = joiner.toString();
//        resultJson = StringEditor.convertOneString(resultJson, "@listRows", join);
//        return resultJson;
        return tableName + "\n" + rows.toString();
    }

}
