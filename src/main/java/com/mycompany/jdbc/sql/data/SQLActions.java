package com.mycompany.jdbc.sql.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Áp dụng Adapter Pattern.
 *
 * @author DELL
 */
public class SQLActions
        implements AbstractSQLActions {

    private final SQLSetter setSQL;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public SQLActions(SQLSetter setSQL) {
        this.setSQL = setSQL;
    }

    // Hoàn thiện câu sql với prepared statement
    private PreparedStatement completePreparedStatement(PreparedStatement pstm)
            throws SQLException {
        for (int i = 0; i < setSQL.getPreparedStatementValues().length; i++) {
            pstm.setObject(i + 1, setSQL.getPreparedStatementValues()[i]);
        }
        return pstm;
    }

    // Thiết lập và lấy ra giá trị của preparedStatement
    private PreparedStatement setAndGetPreparedStatement()
            throws SQLException {
        preparedStatement = setSQL.getConnection()
                .prepareStatement(setSQL.getSQLStatement());
        return completePreparedStatement(preparedStatement);
    }

    private int[] convertTotalColumnsToColumnsIndex()
            throws SQLException {
        int totalCol;
        if (setSQL.getTotalColumns() == 0) {
            totalCol = resultSet.getMetaData().getColumnCount();
        } else {
            totalCol = setSQL.getTotalColumns();
        }
        int[] listInt = new int[totalCol];
        int value = 1;
        for (int i = 0; i < totalCol; i++) {
            listInt[i] = value;
            value++;
        }
        return listInt;
    }

    private String[] convertColumnsIndexToColumnNames()
            throws SQLException {
        int[] listIndex;
        if (setSQL.getColumnsIndex().length <= 0) {
            listIndex = convertTotalColumnsToColumnsIndex();
        } else {
            listIndex = setSQL.getColumnsIndex();
        }
        String[] listNames = new String[listIndex.length];
        int colIndex = 1;
        for (int i = 0; i < listIndex.length; i++) {
            listNames[i] = resultSet.getMetaData().getColumnName(colIndex);
            colIndex++;
        }
        return listNames;
    }

    private Map<String, Object> getRowData(ResultSet rs)
            throws SQLException {
        String[] columnNames;
        if (setSQL.getColumnNames().length <= 0) {
            columnNames = convertColumnsIndexToColumnNames();
        } else {
            columnNames = setSQL.getColumnNames();
        }
        final Map<String, Object> data = new HashMap<>();
        for (String colName : columnNames) {
            data.put(colName, rs.getObject(colName));
        }
        return data;
    }

    @Override
    public ArrayList<Map<String, Object>> query() {
        ArrayList<Map<String, Object>> result = new ArrayList<>();
        try {
            resultSet = setAndGetPreparedStatement().executeQuery();
            int rows = 0;
            while (resultSet.next()) {
                if (rows == setSQL.getTotalRows()) {
                    return result;
                } else {
                    result.add(getRowData(resultSet));
                    rows++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLActions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeDatabase();
        }
        return result;
    }

    @Override
    public boolean update() {
        try {
            if (setAndGetPreparedStatement().executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLActions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeDatabase();
        }
        return false;
    }

    private void closeDatabase() {
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            setSQL.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
