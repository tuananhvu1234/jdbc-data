package com.noproject.jdbc.statement.builder.query;

import com.noproject.jdbc.activerecord.MappedColumn;
import com.noproject.jdbc.activerecord.MappedTable;
import com.noproject.jdbc.clause.AliasClause;
import com.noproject.jdbc.clause.AliasMappedTable;
import com.noproject.jdbc.clause.JoinClause;
import com.noproject.jdbc.clause.JoinMappedTable;
import com.noproject.jdbc.helper.StringConvertor;
import com.noproject.jdbc.operator.EqualMappedColumn;
import com.noproject.jdbc.sql.functions.SqlGenericFunctions;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.noproject.jdbc.sql.functions.AbstractSqlGenericFunctions;

/**
 *
 * @author DELL
 * @param <T>
 */
public class MysqlQueryBuilder<T extends MappedTable> //        implements AbstractMysqlQueryBuilder
{

    private static final class MysqlQueryHelper {

        private static String prepared(String value) {
            if (value == null || value.isEmpty() == true) {
                return value;
            }
            StringBuilder builder = new StringBuilder(value);
            if (value.startsWith("`") == false) {
                builder.insert(0, "`");
            }
            if (value.endsWith("`") == false) {
                builder.append("`");
            }
            return builder.toString();
        }

        private static String preparedStatement(String value) {
            if (value == null || value.isEmpty() == true) {
                return value;
            }
            StringBuilder builder = new StringBuilder(value);
            if (value.startsWith("\'") == false) {
                builder.insert(0, "\'");
            }
            if (value.endsWith("\'") == false) {
                builder.append("\'");
            }
            return builder.toString();
        }
    }

    private final StringBuilder statement;

    private String[] listColumns;
    private String selectFunc;

    private String tableName;
    private String tableAliasName;

    private String joinedTable;
    private String joinedTableName;
    private String joinedTableAliasName;

    public MysqlQueryBuilder() {
        statement = new StringBuilder(QueryStatementFormat.NORMAL_FORMAT);
    }

    private String getColumnName(String tblName, String colName) {
        String colNameForm = "@{table}.@{colName}";

        if (AliasClause.isAliasFormat(colName) == true) {
            colNameForm += " AS @{aliasName}";

            String oriColName = AliasClause.getOriginName(colName);

            String result = StringConvertor.convertKeyToValue(colNameForm,
                    new String[]{"@{table}", "@{colName}"},
                    new String[]{tblName, MysqlQueryHelper.prepared(oriColName)});

            String aliColName = AliasClause.getAliasName(colName);

            return StringConvertor.convertOneString(result,
                    "@{aliasName}", MysqlQueryHelper.prepared(aliColName));
        } else {
            return StringConvertor.convertKeyToValue(colNameForm,
                    new String[]{"@{table}", "@{colName}"},
                    new String[]{tblName, MysqlQueryHelper.prepared(colName)});
        }
    }

    private String getSelectExpression(String colName) {
        if (tableName == null || tableName.isEmpty() == true) {
            throw new NullPointerException("Invalid table name");
        }
        if (tableAliasName != null && tableAliasName.isEmpty() == false) {
            return getColumnName(tableAliasName, colName);
        } else {
            return getColumnName(tableName, colName);
        }
    }

    private String[] convertArrayColumn(MappedColumn[] arrColumns) {
        LinkedList<String> list = new LinkedList<>();
        for (MappedColumn col : arrColumns) {
            list.add(col.getMappedColumnName());
        }
        return list.toArray(String[]::new);
    }

    public String getSelectExpressions() {
        if (selectFunc != null && selectFunc.isEmpty() == false) {
            return this.selectFunc;
        }
        if (listColumns != null && listColumns.length > 0) {
            LinkedList<String> list = new LinkedList<>();
            for (String col : listColumns) {
                list.add(getSelectExpression(col));
            }
            return String.join(", ", list);
        }
        return null;
    }

    private String getTableReferences() {
        if (joinedTable != null && joinedTable.isEmpty() == false) {
            return joinedTable;
        }
        if (tableName == null || tableName.isEmpty() == true) {
            throw new NullPointerException("Invalid table name");
        }
        if (tableAliasName != null && tableAliasName.isEmpty() == false) {
            return AliasClause.as(tableName, tableAliasName).getResult();
        }
        return tableName;
    }

    public String get() {
        return StringConvertor.convertKeyToValue(statement.toString(),
                new String[]{"@{selectExpr}", "@{tableReferences}"},
                new String[]{getSelectExpressions(), getTableReferences()}
        );
    }

    private MysqlQueryBuilder setSelectAllColumn(
            String nameOfTable, Class<T> classOfTable
    ) {
        this.tableName = MysqlQueryHelper.prepared(nameOfTable);
        try {
            T obj = (T) classOfTable.getDeclaredConstructor().newInstance();
            this.listColumns = convertArrayColumn(obj.ALL());
        } catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(MysqlQueryBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }

    public static final class SecondMysqlQueryBuilder {

        public MysqlQueryBuilder from(MappedTable tableReferences) {
            return new MysqlQueryBuilder().setSelectAllColumn(
                    tableReferences.getMappedTableName(),
                    tableReferences.getClass()
            );
        }
    }

    /**
     * Chỉ hỗ trợ cho tên bảng không có tên thay thế.
     *
     * @return class cung cấp method 'from' đặc biệt.
     */
    public SecondMysqlQueryBuilder select() {
        return new SecondMysqlQueryBuilder();
    }

    public MysqlQueryBuilder selectFrom(MappedTable tableReferences) {
        return new SecondMysqlQueryBuilder().from(tableReferences);
    }

    public MysqlQueryBuilder select(MappedColumn[] listColumns) {
        this.listColumns = convertArrayColumn(listColumns);
        return this;
    }

    public MysqlQueryBuilder select(AbstractSqlGenericFunctions expression) {
        this.selectFunc = expression.getResult();
        return this;
    }

    public MysqlQueryBuilder selectCount() {
        return select(SqlGenericFunctions.call().count());
    }

    public MysqlQueryBuilder selectCountAs(String aliasName) {
        String expr = AliasClause.as(
                SqlGenericFunctions.call().count().getResult(),
                MysqlQueryHelper.prepared(aliasName)
        ).getResult();
        this.selectFunc = expr;
        return this;
    }

    public MysqlQueryBuilder from(MappedTable tableReferences) {
        this.tableName = MysqlQueryHelper.prepared(tableReferences.getMappedTableName());
        return this;
    }

    public MysqlQueryBuilder from(AliasMappedTable aliasTableReferences) {
        this.tableName = MysqlQueryHelper.prepared(aliasTableReferences.getTableName());
        this.tableAliasName = MysqlQueryHelper.prepared(aliasTableReferences.getAliasName());
        return this;
    }

    public MysqlQueryBuilder from(JoinMappedTable joinedTable) {
        this.tableName = MysqlQueryHelper.prepared(joinedTable.getLeftTable());
        this.joinedTableName = MysqlQueryHelper.prepared(joinedTable.getRightTable());
        String leftCol = MysqlQueryHelper.prepared(joinedTable.getLeftColumn());
        String rightCol = MysqlQueryHelper.prepared(joinedTable.getRightColumn());
        this.joinedTable = JoinClause.join(joinedTable.getJoinType(), tableName, joinedTableName)
                .on(new EqualMappedColumn(leftCol, rightCol)).getResult();
        return this;
    }
//    public AbstractQueryBuilder join(String joinedTable) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public AbstractQueryBuilder on(String searchCondition) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
