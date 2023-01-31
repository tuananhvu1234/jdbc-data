package jdbc.sql.elements;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLTable {

    private String tableName;
    private SQLColumn[] columns;

    private ResultSet resultSet;

    public SQLTable(String tableName, SQLColumn[] columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public SQLTable(String tableName) {
        this.tableName = tableName;
        try {
            resultSet = SQLSchema.memberGetConnection()
                    .prepareStatement("SELECT * FROM " + tableName)
                    .executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int toltalCol = rsmd.getColumnCount();
            this.columns = new SQLColumn[toltalCol];
            for (int i = 0; i < toltalCol; i++) {
                this.columns[i] = new SQLColumn(rsmd.getColumnName(i + 1),
                        rsmd.getColumnClassName(i + 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLTable.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(SQLTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getTableName() {
        return tableName;
    }

    public SQLColumn[] getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table{");
        sb.append("tableName=").append(tableName);
        sb.append(", columns=").append(Arrays.toString(columns));
        sb.append('}');
        return sb.toString();
    }

}
