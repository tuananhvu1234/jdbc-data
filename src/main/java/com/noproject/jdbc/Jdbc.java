package com.noproject.jdbc;

import Database.jdbc.JDBCConfig;
import static com.noproject.jdbc.Tables.ROLE;
import static com.noproject.jdbc.Tables.USER;
import com.noproject.jdbc.activerecord.MappedColumn;
import com.noproject.jdbc.activerecord.MappingColumn;
import com.noproject.jdbc.activerecord.MappingTable;
import com.noproject.jdbc.clause.AliasClause;
import com.noproject.jdbc.clause.Clause;
import com.noproject.jdbc.clause.JoinClause;
import com.noproject.jdbc.clause.type.MysqlJoinType;
import com.noproject.jdbc.sql.data.ColumnCondition;
import com.noproject.jdbc.sql.data.MapToColumn;
import com.noproject.jdbc.sql.data.MapToTable;
import com.noproject.jdbc.sql.data.ObjectMapper;
import com.noproject.jdbc.sql.data.ResultRow;
import com.noproject.jdbc.sql.data.ResultTable;
import com.noproject.jdbc.sql.data.RowCondition;
import com.noproject.jdbc.sql.data.SqlExecutionAdapter;
import com.noproject.jdbc.sql.data.SqlSetter;
import static com.noproject.jdbc.sql.functions.MysqlFunctions.call;
import com.noproject.jdbc.sql.functions.SqlGenericFunctions;
import com.noproject.jdbc.statement.builder.MysqlStatementBuilder;
import com.noproject.jdbc.statement.builder.query.MysqlQueryBuilder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.noproject.jdbc.sql.data.SqlExecutor;
import com.noproject.jdbc.sql.data.TableCondition;
import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

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

//
        Connection conn = JDBCConfig.getConnection();

        ColumnCondition colSet = new ColumnCondition();
        RowCondition rowSet = new RowCondition();
        TableCondition tableSet = new TableCondition(ServiceModel.class);

        int pageNumber = 1, pageSize = 1;

//        while (pageNumber > 0) {
//            rowSet.skipRows(pageSize * (pageNumber - 1));
//            rowSet.maxResultCount(pageSize);
//            rowSet.countFrom(1);
        tableSet.conditions(colSet, rowSet);

        SqlSetter sqlSetter = new SqlSetter(JDBCConfig.getConnection(), tableSet);

        SqlExecutionAdapter adapter = new SqlExecutionAdapter(sqlSetter);

        ResultTable table = adapter.executeQuery();
//        System.out.println(table);

//            System.out.println("nhap page");
//            Scanner sc = new Scanner(System.in);
//            pageNumber = sc.nextInt();
//            System.out.println(pageNumber);
//        }
        long start, end;
        System.out.println(start = System.currentTimeMillis());

        ObjectMapper<ServiceModel> obj;
        for (ResultRow row : table.rows()) {
            obj = new ObjectMapper(ServiceModel.class, row);
            System.out.println(obj.getObject());
        }
//
        System.out.println(end = System.currentTimeMillis());
        System.out.println((end - start) + " ms");
    }
}