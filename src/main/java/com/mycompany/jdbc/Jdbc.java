package com.mycompany.jdbc;

import com.mycompany.jdbc.*;
import com.mycompany.jdbc.clause.builder.*;

import Database.jdbc.JDBCConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.jdbc.clause.builder.AliasClause;
import com.mycompany.jdbc.clause.builder.SqlOperators;
import com.mycompany.jdbc.clause.type.JoinType;
import com.mycompany.jdbc.clause.type.MysqlJoinType;
import com.mycompany.jdbc.helper.StringConvertor;
import com.mycompany.jdbc.connection.JDBCConnectionPool;
import com.mycompany.jdbc.sql.data.SqlActions;
import com.mycompany.jdbc.sql.data.SqlSetter;
import com.mycompany.jdbc.sql.elements.SQLDatabase;
import com.mycompany.jdbc.statement.builder.SqlStatementBuilder;
import com.mycompany.jdbc.sql.type.DatabaseType;
import com.mycompany.jdbc.sql.type.StatementType;
import com.mycompany.jdbc.sql.elements.SQLColumn;
import com.mycompany.jdbc.sql.elements.SQLTable;
import com.mycompany.jdbc.statement.builder.AbstractStatementBuilder;
import com.mycompany.jdbc.statement.builder.query.MysqlQueryBuilder;
import com.mycompany.jdbc.sql.functions.AbstractMysqlFunctions;
import com.mycompany.jdbc.sql.functions.MysqlFunctions;
import com.mycompany.jdbc.sql.type.MysqlUnitArguments;
import java.sql.Date;
import com.mycompany.jdbc.clause.builder.AbstractJoinClause;
import com.mycompany.jdbc.sql.data.AbstractSqlActions;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Jdbc {

    public static void main(String[] args) throws SQLException {

        long start, end;

        System.out.println(start = System.currentTimeMillis());

        AbstractStatementBuilder builder = SqlStatementBuilder.building(DatabaseType.MYSQL);

        JDBCConnectionPool pool = new JDBCConnectionPool() {
            @Override
            public void actionWhileWaiting() {
                System.out.println("wait");
            }
        };
        pool.setProperties(JDBCConfig.DRIVER,
                JDBCConfig.URL,
                JDBCConfig.USERNAME,
                JDBCConfig.PASSWORD);

        SqlSetter setter = SqlSetter.using(JDBCConfig.getConnection());
        setter.setSQLStatement("select * from role");
//        setter.setTotalRows(1);
        setter.setOffsetRow(2);

        SqlActions action = new SqlActions(setter);

        ArrayList arr = action.query();

        System.out.println(arr);

        System.out.println(end = System.currentTimeMillis());

        System.out.println((end - start) + " ms");
    }
}
