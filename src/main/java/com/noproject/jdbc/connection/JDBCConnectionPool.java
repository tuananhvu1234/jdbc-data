package com.noproject.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public abstract class JDBCConnectionPool extends AbstractObjectPool<Connection> {

    /**
     *
     * @param totalConnection Số lượng connection có thể gọi ra cùng lúc.
     * @param expirationTime Thời gian tồn tại của 1 connection.
     * @param waitingTime Thời gian chờ khi không còn connection chưa sử dụng.
     */
    public JDBCConnectionPool(int totalConnection, long expirationTime, long waitingTime) {
        super(expirationTime);
        limitCountObject(totalConnection);
        waiting(waitingTime);
    }

    /**
     *
     * @param totalConnection Số lượng connection có thể gọi ra cùng lúc.
     * @param expirationTime Thời gian tồn tại của 1 connection.
     */
    public JDBCConnectionPool(int totalConnection, long expirationTime) {
        super(expirationTime);
        limitCountObject(totalConnection);
    }

    /**
     *
     * @param expirationTime Thời gian tồn tại của 1 connection.
     */
    public JDBCConnectionPool(long expirationTime) {
        super(expirationTime);
    }

    /**
     *
     * @param totalConnection Số lượng connection có thể gọi ra cùng lúc.
     */
    public JDBCConnectionPool(int totalConnection) {
        super();
        limitCountObject(totalConnection);
    }

    /**
     *
     */
    public JDBCConnectionPool() {
        super();
    }

    // Các thuộc tính cần để thiết lập connection.
    private final Properties properties = new Properties();

    /**
     * Thiết lập các thuộc tính cần thiết để kết nối database.
     *
     * @param driver Tên Driver được sử dụng để kết nối database.
     * @param url
     * @param username
     * @param password
     * @return
     */
    public JDBCConnectionPool setProperties(
            String driver, String url, String username, String password
    ) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        properties.setProperty("URL", url);
        properties.setProperty("Username", username);
        properties.setProperty("Password", password);
        return this;
    }

    @Override
    protected Connection create() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty("URL"),
                    properties.getProperty("Username"),
                    properties.getProperty("Password")
            );
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean validate(Connection o) {
        try {
            return !o.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void expire(Connection o) {
        try {
            o.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
