package com.noproject.jdbc.sql.elements;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * nhiệm vụ kết nối database thiết lập table thiết lập column
 *
 * trả về dữ liệu database trả về dữ liệu table trả về dữ liệu column
 *
 * @author
 */
public class SqlDatabase extends AbstractSqlElement {

    private SqlDatabase() {
    }

    private static class SQLDatabaseHolder {

        private static final SqlDatabase INSTANCE = new SqlDatabase();
    }

    private static Connection connection;
    private static DatabaseMetaData databaseMetaData;
    private ResultSet resultSet;

    private final Map<Integer, String> listTableNames = new HashMap<>();

    public static SqlDatabase connect(Connection con) {
        connection = con;
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(SqlDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SQLDatabaseHolder.INSTANCE;
    }

    public void disconnect() {
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection = null;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getElementName() {
        try {
            return connection.getCatalog();
        } catch (SQLException ex) {
            Logger.getLogger(SqlDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void setAllSubElementNames() {
        try {
            int index = 1;
            resultSet = databaseMetaData.getTables(getElementName(), null, "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                listTableNames.put(index, resultSet.getString("TABLE_NAME"));
                index++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] getAllSubElementNames() {
        setAllSubElementNames();
        return listTableNames.values().toArray(String[]::new);
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
        return listTableNames.get(index);
    }

}
