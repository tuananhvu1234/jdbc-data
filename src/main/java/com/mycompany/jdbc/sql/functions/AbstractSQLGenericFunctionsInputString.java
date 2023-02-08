package com.mycompany.jdbc.sql.functions;

/**
 *
 *
 * @author DELL
 */
public interface AbstractSQLGenericFunctionsInputString {

    public AbstractSQLGenericFunctions abs(String number);

    public AbstractSQLGenericFunctions acos(String number);

    /**
     *
     * @param number Một số có giá trị nằm trong khoảng từ -1 đến 1.
     * @return
     */
    public AbstractSQLGenericFunctions asin(String number);

    public AbstractSQLGenericFunctions atan(String number);

    public AbstractSQLGenericFunctions atan2(String number1, String number2);

    public AbstractSQLGenericFunctions ascii(String str);

    public AbstractSQLGenericFunctions avg(String number);

    public AbstractSQLGenericFunctions cast(String expression);

    public AbstractSQLGenericFunctions ceiling(String number);

    public AbstractSQLGenericFunctions cos(String number);

    public AbstractSQLGenericFunctions cot(String number);

    public AbstractSQLGenericFunctions count(String expression);

    public AbstractSQLGenericFunctions degrees(String number);

    public AbstractSQLGenericFunctions exp(String number);

    public AbstractSQLGenericFunctions floor(String number);

}
