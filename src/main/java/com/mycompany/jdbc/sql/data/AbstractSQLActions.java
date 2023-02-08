package com.mycompany.jdbc.sql.data;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface AbstractSQLActions {

    public ArrayList<Map<String, Object>> query();

    public boolean update();
}
