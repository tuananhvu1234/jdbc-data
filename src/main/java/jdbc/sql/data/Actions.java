package jdbc.sql.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Actions
        implements SQLActions {

    private final SetSQL setSQL;

    public Actions(SetSQL setSQL) {
        this.setSQL = setSQL;
    }

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private PreparedStatement completePreparedStatement(
            PreparedStatement pstm)
            throws SQLException {
        for (int i = 0; i < setSQL.getPreparedStatementValues().length; i++) {
            pstm.setObject(i + 1, setSQL.getPreparedStatementValues()[i]);
        }
        return pstm;
    }

    private PreparedStatement getPreparedStatement()
            throws SQLException {
        this.preparedStatement = setSQL.getConnection().prepareStatement(setSQL.getSQLStatement());
        return completePreparedStatement(this.preparedStatement);
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
            resultSet = getPreparedStatement().executeQuery();
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
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean update() {
        try {
            if (getPreparedStatement().executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
