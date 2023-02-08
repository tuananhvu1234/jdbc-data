package com.mycompany.jdbc.statement.builder.query;

import com.mycompany.jdbc.sql.functions.AbstractMysqlFunctions;

/**
 *
 * @author DELL
 */
public interface AbstractMysqlQueryBuilder extends AbstractQueryBuilder {

    public AbstractQueryBuilder select(AbstractMysqlFunctions expression);

}
