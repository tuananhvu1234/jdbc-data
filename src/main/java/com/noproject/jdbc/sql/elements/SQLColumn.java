package com.noproject.jdbc.sql.elements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlColumn extends AbstractSqlElement {

    private final Connection connection;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;

    private String tableName;
    private String columnName;
    private String[] dataType;

    public SqlColumn(SqlTable table, String columnName)
            throws SQLException {
//        if (checkExistSubElement(table, columnName)) {
            this.connection = table.getConnection();
            this.tableName = table.getElementName();
            this.columnName = columnName;
//        } else {
//            throw new SQLException("This column does not exist in your table.");
//        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getElementName() {
        return columnName;
    }

    @Override
    protected void setAllSubElementNames() {
        try {
            resultSet = connection
                    .prepareStatement("SELECT " + columnName
                            + " FROM " + tableName)
                    .executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            dataType = new String[resultSetMetaData.getColumnCount()];
            int colIndex = 1;
            for (int i = 0; i < dataType.length; i++) {
                dataType[i] = resultSetMetaData.getColumnClassName(colIndex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] getAllSubElementNames() {
        setAllSubElementNames();
        return dataType;
    }

    @Override
    public int getSubElementCount() {
        return getAllSubElementNames().length;
    }

    @Override
    public String getSubElementName(int index) {
        if (validateIndex(index) == false) {
            return null;
        }
        return getAllSubElementNames()[index - 1];
    }

}
