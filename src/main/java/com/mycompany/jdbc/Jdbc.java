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
import com.mycompany.jdbc.sql.data.SQLActions;
import com.mycompany.jdbc.sql.data.SQLSetter;
import com.mycompany.jdbc.sql.elements.SQLDatabase;
import com.mycompany.jdbc.statement.builder.SqlStatementBuilder;
import com.mycompany.jdbc.sql.type.DatabaseType;
import com.mycompany.jdbc.sql.type.StatementType;
import com.mycompany.jdbc.sql.data.AbstractSQLActions;
import com.mycompany.jdbc.sql.elements.SQLColumn;
import com.mycompany.jdbc.sql.elements.SQLTable;
import com.mycompany.jdbc.statement.builder.AbstractStatementBuilder;
import com.mycompany.jdbc.statement.builder.query.MysqlQueryBuilder;
import com.mycompany.jdbc.sql.functions.AbstractMysqlFunctions;
import com.mycompany.jdbc.sql.functions.MysqlFunctions;
import com.mycompany.jdbc.sql.type.MysqlUnitArguments;
import java.sql.Date;
import com.mycompany.jdbc.clause.builder.AbstractJoinClause;

/**
 *
 * @author DELL
 */
public class Jdbc {

    public static void main(String[] args) throws SQLException {

        long start, end;

        System.out.println(start = System.currentTimeMillis());

        AbstractStatementBuilder builder = SqlStatementBuilder.building(DatabaseType.MYSQL);

        MysqlQueryBuilder create = (MysqlQueryBuilder) builder.createQuery();

//        System.out.println(create.preparedFormat("`abc`"));
//        
        create.select(new String[]{"role_id", "role_name"}).from("role");
        
        System.out.println(create.get());
        
        System.out.println(SqlOperators.and(SqlOperators.like("abc", "def")));
        
//
        System.out.println(end = System.currentTimeMillis());

        System.out.println((end - start) + " ms");
    }
}
