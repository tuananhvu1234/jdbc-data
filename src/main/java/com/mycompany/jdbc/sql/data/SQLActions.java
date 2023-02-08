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
public class SqlActions
        implements AbstractSqlActions {

    /**
     * Các điều kiện để thực hiện câu lệnh sql sẽ được lấy ra từ class SqlSetter
     */
    private final SqlSetter setSQL;

    /**
     * Khai báo PreparedStatement và ResultSet
     */
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * Đối tượng yêu cầu đầu vào là class SqlSetter.
     *
     * @param setSQL Các giá trị đã được thiết lập để thực hiện.
     */
    public SqlActions(SqlSetter setSQL) {
        this.setSQL = setSQL;
    }

    /**
     * Hoàn thiện câu sql với prepared statement
     */
    private PreparedStatement completePreparedStatement(PreparedStatement pstm)
            throws SQLException {
        for (int i = 0; i < setSQL.getPreparedStatementValues().length; i++) {
            pstm.setObject(i + 1, setSQL.getPreparedStatementValues()[i]);
        }
        return pstm;
    }

    /**
     * Thiết lập các giá trị cho preparedStatement và lấy ra giá trị của
     * preparedStatement sau khi thiết lập hoàn tất.
     */
    private PreparedStatement setAndGetPreparedStatement()
            throws SQLException {
        preparedStatement = setSQL.getConnection()
                .prepareStatement(setSQL.getSQLStatement());
        return completePreparedStatement(preparedStatement);
    }

    /**
     * Chuyển đổi tổng số cột thành danh sách vị trí các cột cânf lấy dữ liệu.
     * Danh sách vị trí các cột sẽ bắt đầu từ 1 tương đương vói cột đầu tiên của
     * bảng trong database.
     *
     * @return Danh sách vị trí các cột cần lấy dữ liệu.
     * @throws SQLException
     */
    private int[] convertTotalColumnsToColumnsIndex()
            throws SQLException {
        int totalCol;
        if (setSQL.getTotalColumns() <= 0) {
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

    /**
     * Chuyển đổi từ danh sách vị trí các cột sang danh sách tên các cột cần lấy
     * dữ liệu.
     *
     * @return Danh sách tên các cột cần lấy dữ liệu.
     * @throws SQLException
     */
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

    /**
     * Lấy ra dữ liệu của một dòng.
     *
     * @param rs
     * @return Dữ liệu của một dòng theo tên cột.
     * @throws SQLException
     */
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
            while (resultSet.next() == true) {
                if (rows >= setSQL.getOffsetRow()) {
                    if (rows == setSQL.getTotalRows()) {
                        return result;
                    } else {
                        result.add(getRowData(resultSet));
                    }
                }
                rows++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlActions.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SqlActions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeDatabase();
        }
        return false;
    }

    /**
     * Thực hiện công việc đóng các field liên quan đến database.
     */
    private void closeDatabase() {
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlActions.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            setSQL.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
