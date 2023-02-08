package com.mycompany.jdbc.sql.data;

import java.sql.Connection;
import com.mycompany.jdbc.sql.elements.SQLDatabase;

/**
 *
 * @author
 */
public class SqlSetter {

    // Singleton Pattern
    // Hạn chế tiếp cận bằng constructor
    private SqlSetter() {
    }

    // Tạo connection
    private static Connection connection = null;

    // Thiết lập giá trị cho connection
    public static SqlSetter using(SQLDatabase schema) {
        SqlSetter.connection = schema.getConnection();
        return new SqlSetter();
    }

    public static SqlSetter using(Connection connection) {
        SqlSetter.connection = connection;
        return new SqlSetter();
    }

    // Tạo biến lưu trữ câu lệnh sql
    private String SQLStatement = "";

    // Thiết lập câu lệnh sql.
    public SqlSetter setSQLStatement(String sql) {
        this.SQLStatement = sql;
        return new SqlSetter();
    }

    // Biến lưu giữ các giá trị cần thiết lập
    private Object[] preparedStatementValues = new Object[]{};

    public SqlSetter setPreparedStatementValues(Object[] preparedStatementValues) {
        this.preparedStatementValues = preparedStatementValues;
        return new SqlSetter();
    }

    // Tổng số cột cần lấy ra
    private int totalColumns = 0;

    public SqlSetter setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
        return new SqlSetter();
    }

    // Vị trí các cột cần lấy ra
    private int[] columnsIndex = new int[]{};

    public SqlSetter setColumnsIndex(int[] columnsIndex) {
        this.columnsIndex = columnsIndex;
        return new SqlSetter();
    }

    // Tên các cột cần lấy ra.
    private String[] columnNames = new String[]{};

    public SqlSetter setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        return new SqlSetter();
    }

    // Tổng số dòng cần lây ra.
    // Nếu nhỏ hơn 0 thì sẽ lấy tất cả.
    private int totalRows = -1;

    public SqlSetter setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        return new SqlSetter();
    }

    // Vị trí dòng đầu lấy ra
    // Nếu nhỏ hơn hoặc bằng 0 thì sẽ lấy từ 0
    private int offsetRow = 0;

    public SqlSetter setOffsetRow(int offsetRow) {
        this.offsetRow = offsetRow;
        return new SqlSetter();
    }

    // Các method getter sẽ chỉ cho phép các class trong package sử dụng.
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

    int getOffsetRow() {
        return offsetRow;
    }

}
