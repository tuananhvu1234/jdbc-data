package com.noproject.jdbc.sql.functions;

import com.noproject.jdbc.sql.type.MysqlUnitArguments;
import java.sql.Date;

/**
 *
 * @author
 */
public interface AbstractMysqlFunctions {

    public AbstractMysqlFunctions adddate(String date, String expr, MysqlUnitArguments unit);

    public AbstractMysqlFunctions adddate(String date, int number, MysqlUnitArguments unit);

    public AbstractMysqlFunctions adddate(Date date, String expr, MysqlUnitArguments unit);

    public AbstractMysqlFunctions adddate(Date date, int number, MysqlUnitArguments unit);
}
