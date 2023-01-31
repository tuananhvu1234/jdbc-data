package Database.jdbc;

import java.sql.Connection;

public interface JDBCConfig {

    public static final String HOSTNAME = "localhost";
    public static final String PORT = "3306";
    public static final String DBNAME = "Pet_Clinic";
    public static final String INTEGRATED_SECURITY = "false";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "12345678";

    public static final String URL = "jdbc:mysql://" + HOSTNAME + ":" + PORT + "/" + DBNAME;

    public static Connection getConnection() {
        JDBCConnect.setJDBCConnection(JDBCConfig.URL, JDBCConfig.USERNAME, JDBCConfig.PASSWORD);
        return JDBCConnect.getJDBCConnection();
    }
}
