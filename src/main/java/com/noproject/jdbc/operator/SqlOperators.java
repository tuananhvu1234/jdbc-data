package com.noproject.jdbc.operator;

/**
 *
 * @author DELL
 */
public class SqlOperators extends Operator {

    private final String result;
    private final String operator;

    private SqlOperators(String operator, String result) {
        this.operator = operator;
        this.result = result;
    }

    /**
     *
     * @return
     */
    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getOperator() {
        return operator;
    }

    /**
     * Arithmetic
     *
     * add (+) mysql, mssql subtract / minus (-) mysql, mssql multiply (*)
     * mysql, mssql divide (/) mysql, mssql modulo (%) mysql, mssql
     *
     */
    /**
     * Bitwise
     *
     * and (&) mysql, mssql or (|) mysql, mssql exclusive or (^) mysql, mssql
     *
     */
    /**
     * Comparison
     *
     * equal (=) mysql, mssql great than (>) mysql, mssql less than (<) mysql, mssql
     * great than or equal (>=) mysql, mssql less than or equal (<=) mysql,
     * mssql not equal (<>) mysql, mssql
     *
     */
    /**
     *
     * @param left
     * @param right
     * @return
     */
    public static SqlOperators eq(String left, String right) {
        return new SqlOperators("=", new StringBuilder()
                .append(left).append(" = ").append(right)
                .toString());
    }

    /**
     * Compound
     *
     * += -= *= /= %=
     *
     * Bitwise
     *
     * &= ^-= |*=
     *
     */
    /**
     * Logical
     *
     * all (ALL) and (AND) mysql, mssql any (ANY) between (BETWEEN_AND_) mysql,
     * mssql exist (EXISTS) in (IN) mysql, mssql like (LIKE) mysql, mssql
     * not(NOT) mysql, mssql or (OR) mysql, mssql some (SOME)
     *
     */
    /**
     *
     * @param operator
     * @return
     */
    public static SqlOperators and(String operator) {
        return new SqlOperators("AND", new StringBuilder()
                .append("AND ").append(operator)
                .toString());
    }

    /**
     *
     * @param operator
     * @return
     */
    public static SqlOperators and(SqlOperators operator) {
        return and(operator.getResult());
    }

    /**
     *
     * @param value1
     * @param value2
     * @return
     */
    public static SqlOperators like(String value1, String value2) {
        return new SqlOperators("LIKE", new StringBuilder()
                .append(value1).append(" LIKE \'")
                .append(value2).append("\'")
                .toString());
    }

    /**
     *
     * @param value1
     * @param value2
     * @return
     */
    public static SqlOperators between(String value1, String value2) {
//        result = new StringBuilder()
//                .append("BETWEEN ").append(value1)
//                .append(" AND ").append(value2)
//                .toString();
//        return new SqlOperators();
        return null;
    }

    public static SqlOperators not(String operator) {
        return new SqlOperators("NOT", new StringBuilder()
                .append("NOT ")
                .append(operator)
                .toString());
    }

    public static SqlOperators not(SqlOperators operator) {
        return not(operator.getResult());
    }

}
