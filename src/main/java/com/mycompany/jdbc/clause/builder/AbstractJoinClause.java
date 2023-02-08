package com.mycompany.jdbc.clause.builder;

import com.mycompany.jdbc.clause.type.JoinType;

/**
 *
 * @author DELL
 */
public interface AbstractJoinClause {

    public static final String FORMAT
            = "@{joinType} @{tableFactor} ON @{searchCondition}";

    /**
     * Custom join
     *
     * @param joinClause
     * @return
     */
    public AbstractJoinClause join(String joinClause);

    /**
     * Custom join type
     *
     * @param joinType
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause join(JoinType joinType, String tableFactor1, String tableFactor2);

    public AbstractJoinClause join(JoinType joinType, AliasClause tableFactor1, AliasClause tableFactor2);

    /**
     * join type = inner join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause join(String tableFactor1, String tableFactor2);

    /**
     * join type = inner join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause join(AliasClause tableFactor1, AliasClause tableFactor2);

    /**
     * join type = cross join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause joinCross(String tableFactor1, String tableFactor2);

    /**
     * join type = cross join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause joinCross(AliasClause tableFactor1, AliasClause tableFactor2);

    /**
     * join type = left outer join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause joinLeft(String tableFactor1, String tableFactor2);

    /**
     * join type = left outer join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause joinLeft(AliasClause tableFactor1, AliasClause tableFactor2);

    /**
     * join type = right outer join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause joinRight(String tableFactor1, String tableFactor2);

    /**
     * join type = right outer join
     *
     * @param tableFactor1
     * @param tableFactor2
     * @return
     */
    public AbstractJoinClause joinRight(AliasClause tableFactor1, AliasClause tableFactor2);

    /**
     * custom condition
     *
     * @param searchCondition
     * @return
     */
    public AbstractJoinClause on(String searchCondition);

    /**
     * col1 = col2
     *
     * @param colOfTableFactor1
     * @param colOfTableFactor2
     * @return
     */
    public AbstractJoinClause on(String colOfTableFactor1, String colOfTableFactor2);

    public AbstractJoinClause on(SqlOperators operator);

    public String getResult();

}
