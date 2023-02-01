package jdbc.sql.data;

import java.sql.Connection;
import jdbc.sql.elements.SQLSchema;

/**
 *
 * @author
 */
public class SetSQL {

    private SetSQL() {
    }

    private static class SetSQLInstance {

        private static final SetSQL INSTANCE = new SetSQL();
    }

    private static Connection connection = null;

    public static SetSQL using(SQLSchema schema) {
        SetSQL.connection = schema.getConnection();
        return SetSQLInstance.INSTANCE;
    }

    public static SetSQL using(Connection connection) {
        SetSQL.connection = connection;
        return SetSQLInstance.INSTANCE;
    }

    Connection getConnection() {
        return SetSQL.connection;
    }

    private String SQLStatement = "";

    public SetSQL setSQLStatement(String sql) {
        this.SQLStatement = sql;
        return SetSQLInstance.INSTANCE;
    }

    String getSQLStatement() {
        return SQLStatement;
    }

    private Object[] preparedStatementValues = new Object[]{};

    public SetSQL setPreparedStatementValues(Object[] preparedStatementValues) {
        this.preparedStatementValues = preparedStatementValues;
        return SetSQLInstance.INSTANCE;
    }

    Object[] getPreparedStatementValues() {
        return preparedStatementValues;
    }

    private int totalColumns = 0;

    public SetSQL setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
        return SetSQLInstance.INSTANCE;
    }

    int getTotalColumns() {
        return totalColumns;
    }

    private int[] columnsIndex = new int[]{};

    public SetSQL setColumnsIndex(int[] columnsIndex) {
        this.columnsIndex = columnsIndex;
        return SetSQLInstance.INSTANCE;
    }

    int[] getColumnsIndex() {
        return columnsIndex;
    }
    private String[] columnNames = new String[]{};

    public SetSQL setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
        return SetSQLInstance.INSTANCE;
    }

    String[] getColumnNames() {
        return columnNames;
    }

    private int totalRows = -1;

    public SetSQL setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        return SetSQLInstance.INSTANCE;
    }

    int getTotalRows() {
        return totalRows;
    }

}
