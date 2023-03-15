package com.mycompany.jdbc.sql.functions;

import com.mycompany.jdbc.sql.type.MysqlUnitArguments;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class MysqlFunctions extends SqlGenericFunctions
        implements AbstractMysqlFunctions {

    private final StringBuilder result;

    private MysqlFunctions() {
        result = new StringBuilder();
    }

    public static MysqlFunctions call() {
        return new MysqlFunctions();
    }

    @Override
    public String getResult() {
        if (super.getResult() == null || super.getResult().isEmpty() == true) {
            return this.result.toString();
        } else {
            return super.getResult();
        }
    }

    //ADDDATE('2008-01-02', INTERVAL 31 DAY)
    @Override
    public AbstractMysqlFunctions adddate(String date, String expr, MysqlUnitArguments unit) {
        result.append("ADDDATE(\'");
        result.append(date);
        result.append("\', INTERVAL ");
        result.append(expr);
        result.append(" ");
        result.append(unit);
        result.append(")");
        return this;
    }

    @Override
    public AbstractMysqlFunctions adddate(String date, int number, MysqlUnitArguments unit) {
        return adddate(date, String.valueOf(number), unit);
    }

    @Override
    public AbstractMysqlFunctions adddate(Date date, String expr, MysqlUnitArguments unit) {
        return adddate(date.toString(), expr, unit);
    }

    @Override
    public AbstractMysqlFunctions adddate(Date date, int number, MysqlUnitArguments unit) {
        return adddate(date.toString(), String.valueOf(number), unit);
    }

}
