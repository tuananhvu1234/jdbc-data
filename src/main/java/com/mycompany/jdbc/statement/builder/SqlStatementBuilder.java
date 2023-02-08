package com.mycompany.jdbc.statement.builder;

import com.mycompany.jdbc.sql.type.DatabaseType;

/**
 * Lấy ra loại database cần build lệnh.
 *
 * @author
 */
public class SqlStatementBuilder {

    private SqlStatementBuilder() {
    }

    public static AbstractStatementBuilder building(DatabaseType sqlType) {
        if (sqlType.equals(DatabaseType.MYSQL) == true) {
            return new MysqlStatementBuilder();
        } else if (sqlType.equals(DatabaseType.MSSQL) == true) {
            return null;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
