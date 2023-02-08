package com.mycompany.jdbc.sql.data;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface AbstractSqlActions {

    /**
     * Method thực hiện câu lệnh query.
     *
     * @return Danh sách dữ liệu theo tên cột.
     */
    public ArrayList<Map<String, Object>> query();

    /**
     * Method thực hiện các câu lệnh liên quan việc thay đổi dữ liệu.
     *
     * @return Kết quả của việc thực hiện câu lệnh.
     */
    public boolean update();
}
