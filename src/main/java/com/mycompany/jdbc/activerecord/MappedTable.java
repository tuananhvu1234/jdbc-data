/*
 *
 */
package com.mycompany.jdbc.activerecord;

import com.mycompany.jdbc.clause.AliasMappedColumn;
import com.mycompany.jdbc.clause.AliasClause;
import com.mycompany.jdbc.clause.AliasMappedTable;
import com.mycompany.jdbc.clause.JoinClause;
import com.mycompany.jdbc.clause.JoinMappedTable;
import com.mycompany.jdbc.clause.type.MysqlJoinType;
import com.mycompany.jdbc.operator.EqualMappedColumn;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class MappedTable {

    private String tableName;

    MappedTable setMappedTableName(String name) {
        this.tableName = name;
        return this;
    }

    public String getMappedTableName() {
        return this.tableName;
    }

    /**
     *
     * @param <T>
     * @return
     */
    public <T extends MappedTable> MappedColumn[] ALL() {
        T obj = null;
        try {
            obj = (T) getClass().getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MappingTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<MappedColumn> list = new ArrayList<>();
        for (Field field : getClass().getFields()) {
            if (field.getGenericType().getTypeName()
                    .equals(MappedColumn.class.getName()) == true) {
                try {
                    list.add((MappedColumn) field.get(obj));
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(MappingTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list.toArray(MappedColumn[]::new);
    }

    // as
    public AliasMappedTable as(String aliasName) {
        return new AliasMappedTable(AliasClause.as(getMappedTableName(), aliasName));
    }

    public OnMappedTable join(MappedTable joinTable) {
        return new OnMappedTable(
                JoinClause.join(MysqlJoinType.INNER, tableName, joinTable.getMappedTableName()));
    }

    public static class OnMappedTable {

        private final JoinClause.OnClause onClause;

        private OnMappedTable(JoinClause.OnClause on) {
            this.onClause = on;
        }

        public JoinMappedTable on(EqualMappedColumn operator) {
            return new JoinMappedTable(onClause.on(operator));
        }

        public JoinMappedTable onSame(MappedColumn column) {
            return new JoinMappedTable(
                    onClause.on(
                            new EqualMappedColumn(
                                    column.getMappedColumnName(),
                                    column.getMappedColumnName())
                    )
            );
        }
    }

}
