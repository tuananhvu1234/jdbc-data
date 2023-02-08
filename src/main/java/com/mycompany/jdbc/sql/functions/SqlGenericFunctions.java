package com.mycompany.jdbc.sql.functions;

/**
 *
 * @author DELL
 */
public class SqlGenericFunctions
        implements AbstractSQLGenericFunctions {

    private final StringBuilder result;

    private SqlGenericFunctions() {
        result = new StringBuilder();
    }

    public static SqlGenericFunctions call() {
        return new SqlGenericFunctions();
    }

    @Override
    public String toString() {
        return result.toString();
    }

    @Override
    public AbstractSQLGenericFunctions abs(String number) {
        result.append("ABS(").append(number).append(")");
        return this;
    }

    @Override
    public AbstractSQLGenericFunctions abs(int number) {
        return abs(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions acos(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions acos(float number) {
        return acos(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions asin(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions asin(float number) {
        return asin(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions atan(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions atan(float number) {
        return atan(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions atan2(String number1, String number2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions atan2(float number1, float number2) {
        return atan2(String.valueOf(number1), String.valueOf(number2));
    }

    @Override
    public AbstractSQLGenericFunctions avg(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions avg(double number) {
        return avg(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions ceiling(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions ceiling(double number) {
        return ceiling(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions cos(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions cos(float number) {
        return cos(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions cot(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions cot(float number) {
        return cot(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions degrees(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions degrees(double number) {
        return degrees(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions exp(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions exp(float number) {
        return exp(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions floor(String number) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions floor(double number) {
        return floor(String.valueOf(number));
    }

    @Override
    public AbstractSQLGenericFunctions ascii(String str) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions cast(String expression) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractSQLGenericFunctions count(String expression) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
