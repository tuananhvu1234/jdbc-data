package com.mycompany.jdbc.statement.builder.query;

import com.mycompany.jdbc.sql.functions.AbstractSQLGenericFunctions;

/**
 *
 * @author
 */
public interface AbstractQueryBuilder {

    public String get();

    // select, select all, select distinct
    public AbstractQueryBuilder select(String[] selectExpressions);

    public AbstractQueryBuilder select(AbstractSQLGenericFunctions expression);

    /**
     * tableReference = tableFactor || joinedTable
     * <p>
     * tableFactor = tableName [PARTITION (partitionNames)] [AS aliasName]
     * [indexHintList]
     *
     * @param tableReferences
     * @return
     */
    public AbstractQueryBuilder from(String tableReferences);

    public AbstractQueryBuilder join(String joinedTable);

    public AbstractQueryBuilder on(String searchCondition);

    // join, where, group by, having, order by, limit, offset
}
