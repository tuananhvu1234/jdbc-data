/*
 *
 */
package com.mycompany.jdbc.clause;

import com.mycompany.jdbc.activerecord.MappedColumn;
import com.mycompany.jdbc.activerecord.MappingColumn;

/**
 *
 * @author DELL
 */
public class AliasMappedColumn extends Clause {

    private final AliasClause aliasClause;

    public AliasMappedColumn(AliasClause alias) {
        this.aliasClause = alias;
    }

    @Override
    public String getResult() {
        return aliasClause.getResult();
    }

    public String getColumnName() {
        return aliasClause.getOriginName();
    }

    public String getAliasName() {
        return aliasClause.getAliasName();
    }

    public MappedColumn cast() {
        return MappingColumn.mapping(getResult());
    }
}
