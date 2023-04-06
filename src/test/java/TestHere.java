
import com.noproject.jdbc.sql.conditions.ColumnCondition;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author DELL
 */
public class TestHere {

    @Test
    public void testGetListColumnIndex() {
        ColumnCondition cc = new ColumnCondition();
        cc.columns(3, 4, 8, 6, 9, 4, 7).columns(1, 5);
        assertEquals(9, cc.getListColumnIndex().size());
    }

    public static void main(String[] args)
            throws SQLException, SecurityException, NoSuchMethodException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        long start, end;
        System.out.println(start = System.currentTimeMillis());

        ColumnCondition cc = new ColumnCondition();
        cc.columns(3, 4, 8, 6, 9, 4, 7).columns(1, 5);

        System.out.println(cc.getListColumnIndex());

        System.out.println(end = System.currentTimeMillis());
        System.out.println((end - start) + " ms");
    }
}
