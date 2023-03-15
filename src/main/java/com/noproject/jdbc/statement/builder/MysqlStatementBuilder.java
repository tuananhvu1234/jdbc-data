package com.noproject.jdbc.statement.builder;

import com.noproject.jdbc.statement.builder.query.AbstractQueryBuilder;
import com.noproject.jdbc.statement.builder.query.MysqlQueryBuilder;

/**
 * Lấy ra loại câu lệnh cần build(query, insert, update, delete).
 *
 * @author DELL
 */
public class MysqlStatementBuilder
        implements AbstractStatementBuilder {

    @Override
    public AbstractQueryBuilder createQuery() {
//        return new MysqlQueryBuilder();
        return null;
    }

    @Override
    public AbstractQueryBuilder createInsert() {
        return null;
    }

    @Override
    public AbstractQueryBuilder createUpdate() {
        return null;
    }

    @Override
    public AbstractQueryBuilder createDelete() {
        return null;
    }

}
