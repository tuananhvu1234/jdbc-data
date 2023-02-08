package com.mycompany.jdbc.statement.builder;

import com.mycompany.jdbc.statement.builder.query.AbstractQueryBuilder;
import com.mycompany.jdbc.statement.builder.query.MysqlQueryBuilder;

/**
 * Lấy ra loại câu lệnh cần build(query, insert, update, delete).
 *
 * @author DELL
 */
public class MysqlStatementBuilder
        implements AbstractStatementBuilder {

    @Override
    public AbstractQueryBuilder createQuery() {
        return new MysqlQueryBuilder();
    }

}
