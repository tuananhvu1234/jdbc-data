/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc.clause;

import com.mycompany.jdbc.clause.type.JoinType;

/**
 *
 * @author DELL
 */
public class JoinMappedTable extends Clause {

    private final JoinClause joinClause;

    public JoinMappedTable(JoinClause join) {
        this.joinClause = join;
    }

    @Override
    public String getResult() {
        return joinClause.getResult();
    }

    public String getLeftTable() {
        return joinClause.getLeftTable();
    }

    public String getRightTable() {
        return joinClause.getRightTable();
    }

    public String getLeftColumn() {
        return joinClause.getLeftColumn();
    }

    public String getRightColumn() {
        return joinClause.getRightColumn();
    }

    public JoinType getJoinType() {
        return joinClause.getJoinType();
    }

}
