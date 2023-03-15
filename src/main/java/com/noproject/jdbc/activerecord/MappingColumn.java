package com.noproject.jdbc.activerecord;

/**
 *
 * @author DELL
 */
public class MappingColumn {

    /**
     *
     * @param name
     * @return
     */
    public static MappedColumn mapping(String name) {
        return new MappedColumn().setMappedColumnName(name);
    }

//    public MappingColumn as(String aliasName) {
//        return new MappingColumn(AliasClause.as(name, aliasName).getResult());
//    }
//
//    // count
//    public SqlGenericFunctions count() {
//        return (SqlGenericFunctions) func.count();
//    }
    // eq , so sanh
}
