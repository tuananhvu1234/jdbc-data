package com.noproject.jdbc;

import Database.jdbc.JDBCConfig;
import com.noproject.jdbc.helper.StringEditor;
import com.noproject.jdbc.sql.data.ColumnCondition;
import com.noproject.jdbc.sql.data.ObjectMapper;
import com.noproject.jdbc.sql.data.ResultRow;
import com.noproject.jdbc.sql.data.ResultTable;
import com.noproject.jdbc.sql.data.RowCondition;
import com.noproject.jdbc.sql.data.SqlExecutionAdapter;
import com.noproject.jdbc.sql.data.SqlSetter;
import com.noproject.jdbc.sql.data.TableCondition;
import com.noproject.pattern.BaseMemento;
import com.noproject.pattern.BaseObjectPool;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Jdbc {

    public static void main(String[] args)
            throws SQLException, SecurityException, NoSuchMethodException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//
//        TestClass test = new TestClass(TestType.ONE);
//        System.out.println(TestClass.class.getAnnotation(TestAnnotationType.class).value()[0].getName());
//        TestAnnotationType t = TestClass.class.getDeclaredField("testField").getAnnotation(TestAnnotationType.class);
//        System.out.println(t.value()[0].getName());
//        Field testField = TestClass.class.getDeclaredField("testField");
//        testField.setAccessible(true);
//        System.out.println(testField.getGenericType().getTypeName());
//        testField.set(test, 6);
//        System.out.println(test.getTestField());
        Connection conn = JDBCConfig.getConnection();
        System.out.println(conn.getCatalog());
//        int pageNumber = 1, pageSize = 1;
//        while (pageNumber > 0) {
//            rowSet.skipRows(pageSize * (pageNumber - 1));
//            rowSet.maxResultCount(pageSize);
//            System.out.println("nhap page");
//            Scanner sc = new Scanner(System.in);
//            pageNumber = sc.nextInt();
//            System.out.println(pageNumber);
//        }
//        ColumnCondition colSet = new ColumnCondition();
//        RowCondition rowSet = new RowCondition();
//        TableCondition tableSet = new TableCondition(RoleModel.class);
//        rowSet.firstRowId(1);
//        tableSet.conditions(colSet, rowSet);
//        SqlSetter sqlSetter = new SqlSetter(JDBCConfig.getConnection(), tableSet);
//        SqlExecutionAdapter adapter = new SqlExecutionAdapter(sqlSetter);
//        ResultTable table = adapter.executeQuery();
//        System.out.println(table);
//        ServiceModel service = new ServiceModel();
//        service.setServiceId(7);
//        service.setServiceName("tam cho cho");
//        service.setServiceType("tam");
//        service.setServicePrice(new BigDecimal(20.00));
//        System.out.println(service);
//        ObjectMapper<ServiceModel> mapper = new ObjectMapper<>(service);
//        System.out.println(mapper.getResultRow());
//        for (ResultRow row : table.rows()) {
//            RoleModel obj = new ObjectMapper<>(RoleModel.class, row).getObject();
//            System.out.println(obj);
//        }
//ver6
// base ver
//ver0
//ver1
//ver2
//ver3
//ver4
//ver5
//ver6
        long start, end;
        System.out.println(start = System.currentTimeMillis());

        System.out.println(end = System.currentTimeMillis());
        System.out.println((end - start) + " ms");
    }
}
