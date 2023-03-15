/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc.operator;

/**
 *
 * @author DELL
 */
public class EqualMappedColumn extends Operator {

    private final SqlOperators equalOperator;
    private final String leftColumn;
    private final String rightColumn;

    public EqualMappedColumn(String leftCol, String rightCol) {
        this.leftColumn = leftCol;
        this.rightColumn = rightCol;
        this.equalOperator = SqlOperators.eq(leftColumn, rightColumn);
    }

    @Override
    public String getResult() {
        return equalOperator.getResult();
    }

    @Override
    public String getOperator() {
        return equalOperator.getOperator();
    }

    public String getLeftColumn() {
        return leftColumn;
    }

    public String getRightColumn() {
        return rightColumn;
    }

}
