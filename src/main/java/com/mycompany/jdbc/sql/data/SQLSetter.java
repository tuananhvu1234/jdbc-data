package com.mycompany.jdbc.sql.data;

import java.sql.Connection;
import com.mycompany.jdbc.sql.elements.SQLDatabase;

/**
 *
 * @author
 */
public class SQLSetter {

    // Singleton Pattern
    // Hạn chế tiếp cận bằng constructor
    private SQLSetter() {
    }

    private static class SetSQLInstance {

        private static final SQLSetter INSTANCE = new SQLSetter(); // Tạo instance
    }

    // Tạo connection
    private static Connection connection = null;

    // Thiết lập giá trị cho connection
    public static SQLSetter using(SQLDatabase schema) {
        SQLSetter.connection = schema.getConnection();
        return SetSQLInstance.INSTANCE;
    }

    public static SQLSetter using(Connection connection) {
        SQLSetter.connection = connection;
        return SetSQLInstance.INSTANCE;
    }

    // Tạo biến lưu trữ câu lệnh sql
    private String SQLStatement = "";

    // Thiết lập câu lệnh sql.
    public SQLSetter setSQLStatement(String sql) {
        this.SQLStatement = sql;
        return SetSQLInstance.INSTANCE;
    }

    // Biến lưu giữ các giá trị cần thiết lập
    private Object[] preparedStatementValues = new Object[]{};

    public SQLSetter setPreparedStatementValues(Object[] preparedStatementValues) {
        this.preparedStatementValues = preparedStatementValues;
        return SetSQLInstance.INSTANCE;
    }

    // Tổng số cột cần lấy ra
    private int totalColumns = 0;

    public SQLSetter setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
        return SetSQLInstance.INSTANCE;
    }

    // Vị trí các cột cần lấy ra
    private int[] columnsIndex = new int[]{};

    public SQLSetter setColumnsIndex(int[] columnsIndex) {
        this.columnsIndex = columnsIndex;
        return SetSQLInstance.INSTANCE;
    }

    // Tên các cột cần lấy ra
    private String[] columnNames = new String[]{};

    public SQLSetter setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        return SetSQLInstance.INSTANCE;
    }

    // Tổng số dòng cần lây ra
    private int totalRows = -1;

    public SQLSetter setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        return SetSQLInstance.INSTANCE;
    }

    // Các method chỉ cho phép các class trong package sử dụng.
    Connection getConnection() {
        return connection;
    }

    String getSQLStatement() {
        return SQLStatement;
    }

    Object[] getPreparedStatementValues() {
        return preparedStatementValues;
    }

    int getTotalColumns() {
        return totalColumns;
    }

    int[] getColumnsIndex() {
        return columnsIndex;
    }

    String[] getColumnNames() {
        return columnNames;
    }

    int getTotalRows() {
        return totalRows;
    }

}
