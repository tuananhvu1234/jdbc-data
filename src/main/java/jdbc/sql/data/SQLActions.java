package jdbc.sql.data;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author DELL
 */
public interface SQLActions {

    public ArrayList<Map<String, Object>> query();

    public boolean update();
}
