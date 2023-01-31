package Database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class JDBCConnect {

    private static String url;

    private static String username;

    private static String password;

    public static final void setJDBCConnection(String url, String username, String password) {
        JDBCConnect.url = url;
        JDBCConnect.username = username;
        JDBCConnect.password = password;
    }

    public static final Connection getJDBCConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Where is your MySQL JDBC Driver?");
            Logger.getLogger(JDBCConnect.class.getName()).log(Level.SEVERE, null, ex);
            return con;
        }
        try {
            con = DriverManager.getConnection(JDBCConnect.url, JDBCConnect.username, JDBCConnect.password);
        } catch (SQLException ex) {
            System.err.println("Connection Failed! Check output console");
            Logger.getLogger(JDBCConnect.class.getName()).log(Level.SEVERE, null, ex);
            return con;
        }
        return con;
    }

    public static final void disconnectFromDatabase(
            ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            System.out.println("Close ResultSet fails");
            Logger.getLogger(JDBCConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Close PreparedStatement fails");
            Logger.getLogger(JDBCConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Close Connection fails");
            Logger.getLogger(JDBCConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeJDBCConnection(ResultSet rs, PreparedStatement ps, Connection con) {
        JDBCConnect.closeResultSet(rs);
        JDBCConnect.closePreparedStatement(ps);
        JDBCConnect.closeConnection(con);
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Close ResultSet fails");
        }
    }

    public static void closePreparedStatement(PreparedStatement prepare) {
        try {
            if (prepare != null) {
                prepare.close();
            }
        } catch (SQLException e) {
            System.out.println("Close PreparedStatement fails");
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Close Connection fails");
        }
    }
}
