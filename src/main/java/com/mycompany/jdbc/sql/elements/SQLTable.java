package com.mycompany.jdbc.sql.elements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLTable extends AbstractSQLElement {

    private final Connection connection;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;

    private String tableName;

    int totalColumns;
    private String[] listColumnNames = new String[]{};

    public SQLTable(SQLDatabase database, String tableName)
            throws SQLException {
        this.connection = database.getConnection();
        if (checkExistSubElement(database, tableName) == true) {
            this.tableName = tableName;
        } else {
            throw new SQLException("This table does not exist in your database.");
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getElementName() {
        return tableName;
    }

    @Override
    protected void setAllSubElementNames() {
        try {
            resultSet = connection
                    .prepareStatement("SELECT * FROM " + tableName)
                    .executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            totalColumns = resultSetMetaData.getColumnCount();
            listColumnNames = new String[totalColumns];
            int colIndex = 1;
            for (int i = 0; i < totalColumns; i++) {
                listColumnNames[i] = resultSetMetaData.getColumnName(colIndex);
                colIndex++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] getAllSubElementNames() {
        setAllSubElementNames();
        return listColumnNames;
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
        return listColumnNames[index - 1];
    }

}
