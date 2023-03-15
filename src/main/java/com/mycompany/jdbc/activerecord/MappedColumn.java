/*
 *
 */
package com.mycompany.jdbc.activerecord;

import com.mycompany.jdbc.clause.AliasMappedColumn;
import com.mycompany.jdbc.clause.AliasClause;
import com.mycompany.jdbc.operator.EqualMappedColumn;

/**
 *
 * @author DELL
 */
public class MappedColumn {

    private String columnName;

    public String getMappedColumnName() {
        return this.columnName;
    }

    MappedColumn setMappedColumnName(String name) {
        this.columnName = name;
        return this;
    }

    public AliasMappedColumn as(String aliasName) {
        return new AliasMappedColumn(AliasClause.as(columnName, aliasName));
    }

    public EqualMappedColumn eq(String colName) {
        return new EqualMappedColumn(this.columnName, colName);
    }

    public EqualMappedColumn eq(MappedColumn colName) {
        return new EqualMappedColumn(this.columnName, colName.getMappedColumnName());
    }
}
