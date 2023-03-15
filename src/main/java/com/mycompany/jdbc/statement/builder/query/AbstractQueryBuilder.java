package com.mycompany.jdbc.statement.builder.query;

import com.mycompany.jdbc.activerecord.MappingColumn;
import com.mycompany.jdbc.activerecord.MappingTable;
import com.mycompany.jdbc.sql.functions.AbstractSqlGenericFunctions;

/**
 *
 * @author
 */
public interface AbstractQueryBuilder {

    public String get();

    // select, select all, select distinct
    public AbstractQueryBuilder select(MappingColumn[] selectExpressions);

    public AbstractQueryBuilder select(AbstractSqlGenericFunctions expression);

    /**
     * tableReference = tableFactor || joinedTable
     * <p>
     * tableFactor = tableName [PARTITION (partitionNames)] [AS aliasName]
     * [indexHintList]
     *
     * @param tableReferences
     * @return
     */
    public AbstractQueryBuilder from(MappingTable tableReferences);

    public AbstractQueryBuilder join(String joinedTable);

    public AbstractQueryBuilder on(String searchCondition);

    // join, where, group by, having, order by, limit, offset
}
