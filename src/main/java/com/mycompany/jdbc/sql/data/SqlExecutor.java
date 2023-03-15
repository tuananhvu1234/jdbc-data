package com.mycompany.jdbc.sql.data;

import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public interface SqlExecutor {

    /**
     * Method thực hiện câu lệnh query.
     *
     * @return Danh sách dữ liệu theo tên cột.
     * @throws java.sql.SQLException
     */
    public ResultTable executeQuery() throws SQLException;

    /**
     * Method thực hiện các câu lệnh liên quan việc thay đổi dữ liệu.
     *
     * @return Kết quả của việc thực hiện câu lệnh.
     * @throws java.sql.SQLException
     */
    public boolean executeUpdate() throws SQLException;
}
