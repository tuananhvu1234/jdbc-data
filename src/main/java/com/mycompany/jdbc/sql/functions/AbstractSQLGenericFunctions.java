package com.mycompany.jdbc.sql.functions;

/**
 * Build-in Functions giống nhau về cách dùng và đầu vào của mysql và mssql
 *
 *
 *
 * @author DELL
 */
public interface AbstractSqlGenericFunctions extends AbstractSQLGenericFunctionsInputString {

    public String getResult();

    public AbstractSqlGenericFunctions abs(int number);

    public AbstractSqlGenericFunctions acos(float number);

    /**
     *
     * @param number Một số có giá trị nằm trong khoảng từ -1 đến 1.
     * @return
     */
    public AbstractSqlGenericFunctions asin(float number);

    public AbstractSqlGenericFunctions atan(float number);

    /**
     * Tương đương với function atn2 của mssql.
     *
     * @param number1
     * @param number2
     * @return
     */
    public AbstractSqlGenericFunctions atan2(float number1, float number2);

    public AbstractSqlGenericFunctions avg(double number);

    public AbstractSqlGenericFunctions ceiling(double number);

    public AbstractSqlGenericFunctions cos(float number);

    public AbstractSqlGenericFunctions cot(float number);

    public AbstractSqlGenericFunctions degrees(double number);

    public AbstractSqlGenericFunctions exp(float number);

    public AbstractSqlGenericFunctions floor(double number);

}
