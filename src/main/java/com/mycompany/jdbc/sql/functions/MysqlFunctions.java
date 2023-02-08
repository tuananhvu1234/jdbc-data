package com.mycompany.jdbc.sql.functions;

import com.mycompany.jdbc.sql.type.MysqlUnitArguments;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class MysqlFunctions
        implements AbstractSQLGenericFunctions, AbstractMysqlFunctions {

    private final SqlGenericFunctions generic;
    private final StringBuilder result;

    private MysqlFunctions() {
        result = new StringBuilder();
        generic = SqlGenericFunctions.call();
    }

    public static MysqlFunctions call() {
        return new MysqlFunctions();
    }

    @Override
    public String toString() {
        return result.toString();
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

    @Override
    public AbstractSQLGenericFunctions abs(String number) {
        result.append(generic.abs(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions abs(int number) {
        return abs(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions asin(String number) {
        result.append(generic.asin(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions asin(float number) {
        return asin(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions atan(String number) {
        result.append(generic.atan(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions atan(float number) {
        return atan(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions atan2(String number1, String number2) {
        result.append(generic.atan2(number1, number2));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions atan2(float number1, float number2) {
        return atan2(String.valueOf(number1), String.valueOf(number2));
    }

    @Override
    public AbstractSQLGenericFunctions avg(String number) {
        result.append(generic.avg(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions avg(double number) {
        return avg(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions ceiling(String number) {
        result.append(generic.ceiling(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions ceiling(double number) {
        return ceiling(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions cos(String number) {
        result.append(generic.cos(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions cos(float number) {
        return cos(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions cot(String number) {
        result.append(generic.cot(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions cot(float number) {
        return cot(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions degrees(String number) {
        result.append(generic.degrees(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions degrees(double number) {
        return degrees(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions exp(String number) {
        result.append(generic.exp(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions exp(float number) {
        return exp(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions floor(String number) {
        result.append(generic.floor(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions floor(double number) {
        return floor(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions acos(String number) {
        result.append(generic.acos(number));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions acos(float number) {
        return acos(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions ascii(String str) {
        result.append(generic.ascii(str));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions cast(String expression) {
        result.append(generic.cast(expression));
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions count(String expression) {
        result.append(generic.count(expression));
        return this;
    }

}
