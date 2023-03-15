/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jdbc.clause;

/**
 *
 * @author DELL
 */
public class AliasMappedTable extends Clause {

    private final AliasClause aliasClause;

    public AliasMappedTable(AliasClause alias) {
        this.aliasClause = alias;
    }

    @Override
    public String getResult() {
        return aliasClause.getResult();
    }

    public String getTableName() {
        return aliasClause.getOriginName();
    }

    public String getAliasName() {
        return aliasClause.getAliasName();
    }

}
