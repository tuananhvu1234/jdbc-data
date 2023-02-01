package com.mycompany.jdbc;

import Database.jdbc.JDBCConfig;
import jdbc.sql.data.Actions;
import jdbc.sql.data.SetSQL;
import jdbc.sql.elements.SQLSchema;
import jdbc.statement.builder.SQLStatementBuilder;
import jdbc.type.DatabaseType;
import jdbc.type.StatementType;
import jdbc.sql.data.SQLActions;

/**
 *
 * @author DELL
 */
public class Jdbc {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());

        SetSQL setter = SetSQL.using(SQLSchema.connectToDatabase(JDBCConfig.getConnection()))
                .setSQLStatement("select * from role")
                .setColumnNames(new String[]{"role_name"})
                .setTotalRows(2);

        SQLActions action = new Actions(setter);
        System.out.println(action.query());

        System.out.println(System.currentTimeMillis());

    }
}
