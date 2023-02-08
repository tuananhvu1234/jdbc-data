package com.mycompany.jdbc.sql.functions;

import com.mycompany.jdbc.sql.type.MysqlUnitArguments;
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
