package com.mycompany.jdbc.sql.functions;

import com.mycompany.jdbc.activerecord.MappingColumn;

/**
 *
 *
 * @author DELL
 */
public interface AbstractSQLGenericFunctionsInputString {

    public AbstractSqlGenericFunctions abs(String number);

    public AbstractSqlGenericFunctions acos(String number);

    /**
     *
     * @param number Một số có giá trị nằm trong khoảng từ -1 đến 1.
     * @return
     */
    public AbstractSqlGenericFunctions asin(String number);

    public AbstractSqlGenericFunctions atan(String number);

    public AbstractSqlGenericFunctions atan2(String number1, String number2);

    public AbstractSqlGenericFunctions ascii(String str);

    public AbstractSqlGenericFunctions avg(String number);

    public AbstractSqlGenericFunctions cast(String expression);

    public AbstractSqlGenericFunctions ceiling(String number);

    public AbstractSqlGenericFunctions cos(String number);

    public AbstractSqlGenericFunctions cot(String number);

    public AbstractSqlGenericFunctions count();

    public AbstractSqlGenericFunctions degrees(String number);

    public AbstractSqlGenericFunctions exp(String number);

    public AbstractSqlGenericFunctions floor(String number);

}
