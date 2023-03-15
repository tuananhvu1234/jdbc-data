package com.mycompany.jdbc.statement.builder;

import com.mycompany.jdbc.statement.builder.query.AbstractQueryBuilder;

/**
 * Làm khuôn mẫu cho các class lấy lệnh.
 *
 * @author DELL
 */
public interface AbstractStatementBuilder {

    public AbstractQueryBuilder createQuery();

    public AbstractQueryBuilder createInsert();

    public AbstractQueryBuilder createUpdate();

    public AbstractQueryBuilder createDelete();

    // createInsert, createUpdate, createDelete
}
