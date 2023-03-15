package com.mycompany.jdbc.clause.type;

/**
 *
 * @author
 */
public enum MysqlJoinType implements JoinType {
    /**
     * inner join
     */
    INNER,
    CROSS,
    STRAIGHT_JOIN,
    /**
     * left outer join
     */
    LEFT,
    /**
     * right outer join
     */
    RIGHT,
    /**
     * NATURAL INNER JOIN
     */
//    NATURAL,
//    NATURAL_LEFT,
//    NATURAL_RIGHT

}
