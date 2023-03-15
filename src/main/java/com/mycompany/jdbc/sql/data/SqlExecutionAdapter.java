package com.mycompany.jdbc.sql.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class SqlExecutionAdapter
        implements SqlExecutor {

    private final SqlSetter setSQL;
    private final TableCondition tableCondition;
    private final ColumnCondition columnCondition;
    private final RowCondition rowCondition;

    // Constructor
    public SqlExecutionAdapter(SqlSetter sqlSetter) {
        this.setSQL = sqlSetter;
        this.tableCondition = setSQL.getTableCondtions();
        this.columnCondition = setSQL.getColumnConditions();
        this.rowCondition = setSQL.getRowConditions();
    }

    private final PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /*
     * Hoàn thiện câu sql với prepared statement
     */
    private PreparedStatement completePreparedStatement(PreparedStatement pstm, Object[] parameters)
            throws SQLException {
        if (parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                pstm.setObject(i + 1, parameters[i]);
            }
        }
        return pstm;
    }

    /*
     * Thiết lập các giá trị cho preparedStatement và lấy ra giá trị của
     * preparedStatement sau khi thiết lập hoàn tất.
     */
    private PreparedStatement setAndGetPreparedStatement(PreparedStatement pstm)
            throws SQLException {
        pstm = setSQL.getConnection()
                .prepareStatement(setSQL.getSqlStatement());
        return completePreparedStatement(pstm, setSQL.getPreparedStatementValues());
    }

    /*
     * Lấy ra dữ liệu của một dòng.
     *
     * @param rs
     * @return Dữ liệu của một dòng theo tên cột.
     * @throws SQLException
     */
    private ResultRow getOneRowData(int id, List<String> listColNames, ResultSet rs) {
        final ResultRow row = new ResultRow(id);
        listColNames.stream().forEach(colName -> {
            try {
                row.addData(colName, rs.getObject(colName));
            } catch (SQLException ex) {
                Logger.getLogger(SqlExecutionAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return row;
    }

    @Override
    public ResultTable executeQuery() {
        try {
            resultSet = setAndGetPreparedStatement(preparedStatement).executeQuery();
            columnCondition.valueForResultSet(resultSet);
            List<String> listColName = columnCondition.getListMixedColumnNames();
            ResultTable table = new ResultTable(tableCondition.getTableName(), listColName);
            int rowId = rowCondition.getCountFrom(),
                    currentRow = 1;
            final int offsetRow = rowCondition.getOffsetRow(),
                    maxRow = rowCondition.getMaxRow();
            while (resultSet.next() == true) {
                if (currentRow > offsetRow) {
                    if (maxRow < 0 || maxRow > 0 && currentRow <= maxRow + offsetRow) {
                        table.addRow(getOneRowData(rowId++, listColName, resultSet));
                    } else {
                        return table;
                    }
                }
                currentRow++;
            }
            return table;
        } catch (SQLException ex) {
            Logger.getLogger(SqlExecutionAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeDatabase();
        }
        return null;
    }

    @Override
    public boolean executeUpdate() {
        try {
            setSQL.getConnection().setAutoCommit(false);
            if (setAndGetPreparedStatement(preparedStatement).executeUpdate() > 0) {
                setSQL.getConnection().commit();
                return true;
            }
        } catch (SQLException ex) {
            try {
                if (setSQL.getConnection() != null) {
                    setSQL.getConnection().rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(SqlExecutionAdapter.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(SqlExecutionAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeDatabase();
        }
        return false;
    }

    /*
     * Thực hiện công việc đóng các field liên quan đến database.
     */
    private void closeDatabase() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlExecutionAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlExecutionAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (setSQL.getConnection() != null) {
                setSQL.getConnection().close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SqlExecutionAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
