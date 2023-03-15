package com.mycompany.jdbc.activerecord;

/**
 *
 * @author DELL
 */
public class Mapper {

    public static <T extends MappedTable> T mappingTable(Class<? extends MappedTable> obj, String tableName) {
        return MappingTable.mapping(obj, tableName);
    }

    public static MappedColumn mappingColumn(String columnName) {
        return MappingColumn.mapping(columnName);
    }

}
