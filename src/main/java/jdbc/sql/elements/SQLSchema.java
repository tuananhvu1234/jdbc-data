package jdbc.sql.elements;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * nhiệm vụ kết nối database thiết lập table thiết lập column
 *
 * trả về dữ liệu database trả về dữ liệu table trả về dữ liệu column
 *
 * @author
 */
public class SQLSchema {

    private static String databaseName;
    private static final List<SQLTable> databaseTables = new ArrayList<>();

    private static Connection connection;

    private SQLSchema() {
    }

    private static class DatabaseInstance {

        private static final SQLSchema INSTANCE = new SQLSchema();
    }

    public static SQLSchema connectToDatabase(Connection connection) {
        SQLSchema.connection = connection;
        try {
            SQLSchema.databaseName = connection.getCatalog();
            setSchemaTables();
        } catch (SQLException ex) {
            Logger.getLogger(SQLSchema.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DatabaseInstance.INSTANCE;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLSchema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static Connection memberGetConnection() {
        return connection;
    }

    private static void setSchemaTables() {
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(databaseName, null, "%", new String[]{"TABLE"});
            while (rs.next()) {
                SQLSchema.databaseTables.add(new SQLTable(rs.getString("TABLE_NAME")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLSchema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getSchemaName() {
        return SQLSchema.databaseName;
    }

    public SQLTable getTable(String tableName) {
        for (SQLTable table : databaseTables) {
            if (table.getTableName().equals(tableName)) {
                return table;
            }
        }
        return null;
    }
}
