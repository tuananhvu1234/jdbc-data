package com.mycompany.jdbc.sql.functions;

import com.mycompany.jdbc.activerecord.MappingColumn;

/**
 *
 * @author DELL
 */
public class SqlGenericFunctions
        implements AbstractSqlGenericFunctions {

    private StringBuilder result;

    public SqlGenericFunctions() {
        result = new StringBuilder();
    }

    public static SqlGenericFunctions call() {
        return new SqlGenericFunctions();
    }

    @Override
    public String getResult() {
        return result.toString();
    }

    @Override
    public AbstractSqlGenericFunctions abs(String number) {
        result.append("ABS(").append(number).append(")");
        return this;
    }

    @Override
    public AbstractSqlGenericFunctions abs(int number) {
        return abs(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions acos(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions acos(float number) {
        return acos(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions asin(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions asin(float number) {
        return asin(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions atan(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions atan(float number) {
        return atan(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions atan2(String number1, String number2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions atan2(float number1, float number2) {
        return atan2(String.valueOf(number1), String.valueOf(number2));
    }

    @Override
    public AbstractSqlGenericFunctions avg(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions avg(double number) {
        return avg(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions ceiling(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions ceiling(double number) {
        return ceiling(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions cos(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions cos(float number) {
        return cos(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions cot(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions cot(float number) {
        return cot(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions degrees(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions degrees(double number) {
        return degrees(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions exp(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions exp(float number) {
        return exp(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions floor(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions floor(double number) {
        return floor(String.valueOf(number));
    }

    @Override
    public AbstractSqlGenericFunctions ascii(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions cast(String expression) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSqlGenericFunctions count() {
        result = new StringBuilder().append("count(\'*\')");
        return this;
    }

}
