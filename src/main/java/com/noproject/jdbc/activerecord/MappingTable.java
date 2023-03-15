package com.noproject.jdbc.activerecord;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class MappingTable {

    public static final <T extends MappedTable> T mapping(Class<? extends MappedTable> ouput, String name) {
        T obj = null;
        try {
            obj = (T) ouput.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MappingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (T) new MappedTable().getClass().cast(obj).setMappedTableName(name);
    }
//
//    // count
//    public SqlGenericFunctions count() {
//        return (SqlGenericFunctions) func.count();
//    }
//
//    // as
//    public AliasClause as(String aliasName) {
//        return AliasClause.as(name, aliasName);
//    }
//
//    // join
}
