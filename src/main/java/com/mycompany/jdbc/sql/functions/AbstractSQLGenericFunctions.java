package com.mycompany.jdbc.sql.functions;

/**
 * Build-in Functions giống nhau về cách dùng và đầu vào của mysql và mssql
 *
 *
 *
 * @author DELL
 */
public interface AbstractSQLGenericFunctions extends AbstractSQLGenericFunctionsInputString {

    public AbstractSQLGenericFunctions abs(int number);

    public AbstractSQLGenericFunctions acos(float number);

    /**
     *
     * @param number Một số có giá trị nằm trong khoảng từ -1 đến 1.
     * @return
     */
    public AbstractSQLGenericFunctions asin(float number);

    public AbstractSQLGenericFunctions atan(float number);

    /**
     * Tương đương với function atn2 của mssql.
     *
     * @param number1
     * @param number2
     * @return
     */
    public AbstractSQLGenericFunctions atan2(float number1, float number2);

    public AbstractSQLGenericFunctions avg(double number);

    public AbstractSQLGenericFunctions ceiling(double number);

    public AbstractSQLGenericFunctions cos(float number);

    public AbstractSQLGenericFunctions cot(float number);

    public AbstractSQLGenericFunctions degrees(double number);

    public AbstractSQLGenericFunctions exp(float number);

    public AbstractSQLGenericFunctions floor(double number);

}
