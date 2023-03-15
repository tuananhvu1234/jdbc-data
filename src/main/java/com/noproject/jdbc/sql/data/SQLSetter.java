package com.noproject.jdbc.sql.data;

import java.sql.Connection;

/**
 * Thiết lập connection, sql statement, command parameter.<p>
 * Nhận vào column setter và row setter để hoàn thiện điều kiện
 *
 * @author
 */
public class SqlSetter {

    // Requirement Field
    // Tạo biến lưu giá trị connection
    private Connection connection = null;
    // Câu lệnh sql cần thực hiện
    private String SQLStatement = "";
    // Non-Requirement Field
    // Các giá trị cần thiết lập cho câu sql
    private Object[] preparedStatementValues = new Object[]{};

    private TableCondition tableCondition = null;

    // Constructors
    public SqlSetter(Connection conn, String sql, TableCondition conditions, Object... parameters) {
        this.setConnection(conn);
        this.setSqlStatement(sql);
        this.tableCondition = conditions;
        this.setCommandParameters(parameters);
    }

    public SqlSetter(Connection conn, String sql, Object... parameters) {
        this(conn, sql, null, parameters);
    }

    public SqlSetter(Connection conn, String sql, TableCondition conditions) {
        this(conn, sql, conditions, new Object[]{});
    }

    public SqlSetter(Connection conn, String sql) {
        this(conn, sql, null, new Object[]{});
    }

    public SqlSetter(Connection conn, TableCondition conditions) {
        this(conn, "select * from " + conditions.getTableName(), conditions, new Object[]{});
    }

    private void setConnection(Connection conn) {
        if (conn == null) {
            throw new NullPointerException();
        }
        this.connection = conn;
    }

    public final SqlSetter setSqlStatement(String sql) {
        if (sql == null || sql.isBlank() == true) {
            throw new NullPointerException("Sql statement cannot be null or empty or contain only spaces!");
        }
        this.SQLStatement = sql;
        return this;
    }

    public final SqlSetter setCommandParameters(Object... parameters) {
        for (Object value : parameters) {
            if (value == null || String.valueOf(value).isBlank() == true) {
                throw new NullPointerException("");
            }
        }
        this.preparedStatementValues = parameters;
        return this;
    }

    // Lấy giá trị connection
    protected Connection getConnection() {
        return this.connection;
    }

    // Lấy giá trị câu lệnh sql
    protected String getSqlStatement() {
        return this.SQLStatement;
    }

    // 
    protected Object[] getPreparedStatementValues() {
        return this.preparedStatementValues;
    }

    public SqlSetter tableConditions(TableCondition conditions) {
        this.tableCondition = conditions;
        return this;
    }

    public SqlSetter columnConditions(ColumnCondition conditions) {
        if (tableCondition == null) {
            this.tableCondition = new TableCondition();
        }
        tableCondition.columnConditions(conditions);
        return this;
    }

    public SqlSetter rowConditions(RowCondition conditions) {
        if (tableCondition == null) {
            this.tableCondition = new TableCondition();
        }
        tableCondition.rowConditions(conditions);
        return this;
    }

    protected TableCondition getTableCondtions() {
        return tableCondition;
    }

    protected ColumnCondition getColumnConditions() {
        return tableCondition.getColumnConditions();
    }

    protected RowCondition getRowConditions() {
        return tableCondition.getRowConditions();
    }

}
