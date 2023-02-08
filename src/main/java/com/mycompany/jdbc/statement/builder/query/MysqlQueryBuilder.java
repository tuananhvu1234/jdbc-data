package com.mycompany.jdbc.statement.builder.query;

import com.mycompany.jdbc.clause.builder.AliasClause;
import com.mycompany.jdbc.helper.StringConvertor;
import com.mycompany.jdbc.sql.functions.AbstractMysqlFunctions;
import com.mycompany.jdbc.sql.functions.AbstractSQLGenericFunctions;

/**
 *
 * @author DELL
 */
public class MysqlQueryBuilder
        implements AbstractMysqlQueryBuilder {

    private final StringBuilder statement;

    private String[] selectExpressions;
    private String table;

    public MysqlQueryBuilder() {
        statement = new StringBuilder(QueryStatementFormat.FORMAT);
    }

    public String preparedFormat(String str) {
        if (str == null || str.isEmpty() == true) {
            return str;
        }
        StringBuilder builder = new StringBuilder(str);
        if (str.startsWith("`") == false) {
            builder.insert(0, "`");
        }
        if (str.endsWith("`") == false) {
            builder.append("`");
        }
        return builder.toString();
    }

    public String completeColumnName(String tblName, String[] columns) {
        StringBuilder builder = new StringBuilder();
        if (columns.length == 1) {
            if (StringConvertor.convertEveryString(columns[0], " ", "")
                    .equals("*") == true) {
                builder.append(AliasClause.getAliasName(tblName));
                builder.append(".").append("*");
                return builder.toString();
            }
        }
        if (tblName != null && tblName.isEmpty() == false) {
            for (int i = 0; i < columns.length; i++) {
                if (StringConvertor.convertEveryString(columns[i], " ", "")
                        .equals("*") == true) {
                    return completeColumnName(tblName, new String[]{"*"});
                }
                builder.append(AliasClause.getAliasName(tblName));
                builder.append(".").append(preparedFormat(columns[i]));
                if (i < columns.length - 1) {
                    builder.append(", ");
                }
            }
            return builder.toString();
        }
        return null;
    }

    public String completeStatement() {
        return StringConvertor.convertKeyToValue(statement.toString(),
                new String[]{
                    new StringBuilder().append("@{selectExpr}->")
                            .append(completeColumnName(table, selectExpressions))
                            .toString(),
                    new StringBuilder().append("@{tableReferences}->")
                            .append(table)
                            .toString()
                });
    }

    @Override
    public String get() {
        return completeStatement();
    }

    @Override
    public AbstractQueryBuilder select(String[] selectExpressions) {
        this.selectExpressions = selectExpressions;
        return this;
    }

    @Override
    public AbstractQueryBuilder select(AbstractSQLGenericFunctions expression) {
        System.out.println(expression);
        return this;
    }

    @Override
    public AbstractQueryBuilder select(AbstractMysqlFunctions expression) {
        System.out.println(expression);
        return this;
    }

    @Override
    public AbstractQueryBuilder from(String tableReferences) {
        if (AliasClause.isAliasFormat(tableReferences) == true) {
            this.table = AliasClause.as(
                    preparedFormat(AliasClause.getOriginName(tableReferences)),
                    preparedFormat(AliasClause.getAliasName(tableReferences))
            ).toString();
        } else {
            this.table = preparedFormat(tableReferences);
        }
        return this;
    }

    @Override
    public AbstractQueryBuilder join(String joinedTable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractQueryBuilder on(String searchCondition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
