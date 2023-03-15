package com.noproject.jdbc.clause;

import com.noproject.jdbc.operator.EqualMappedColumn;
import com.noproject.jdbc.clause.type.JoinType;
import com.noproject.jdbc.helper.StringConvertor;

/**
 *
 * @author DELL
 */
public class JoinClause extends Clause {

    private static final String FORMAT
            = "@{leftTable} @{joinType} @{rightTable} ON @{leftColumn} @{compareType} @{rightColumn}";

    private final String result;
    private String leftTable;
    private String rightTable;
    private String leftColumn;
    private String rightColumn;
    private JoinType joinType;

    private JoinClause(String join) {
        this.result = join;
    }

    @Override
    public String getResult() {
        return result;
    }

    public String getLeftTable() {
        return leftTable;
    }

    public String getRightTable() {
        return rightTable;
    }

    public String getLeftColumn() {
        return leftColumn;
    }

    public String getRightColumn() {
        return rightColumn;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    private void setAll(String leftTable, String rightTable, String leftColumn, String rightColumn, JoinType type) {
        this.leftTable = leftTable;
        this.rightTable = rightTable;
        this.leftColumn = leftColumn;
        this.rightColumn = rightColumn;
        this.joinType = type;
    }

    public static OnClause join(JoinType type, String table1, String table2) {
        return new JoinClauseHandle().handle(type, table1, table2);
    }

    private static class JoinClauseHandle {

        private OnClause handle(JoinType type, String leftTable, String rightTable) {
            String result = StringConvertor.convertKeyToValue(FORMAT,
                    new String[]{"@{leftTable}", "@{rightTable}"},
                    new String[]{leftTable, rightTable});
            if (type.toString().toUpperCase().contains("JOIN") == false) {
                String joinType = type.toString() + " JOIN";
                result = StringConvertor.convertOneString(result, "@{joinType}", joinType);
            } else {
                result = StringConvertor.convertOneString(result, "@{joinType}", type.toString());
            }
            return new OnClause(result, leftTable, rightTable).setType(type);
        }
    }

    public static class OnClause {

        private final String join;
        private JoinType type;
        private final String leftTable;
        private final String rightTable;

        private OnClause(String join, String leftTable, String rightTable) {
            this.join = join;
            this.leftTable = leftTable;
            this.rightTable = rightTable;
        }

        private OnClause setType(JoinType type) {
            this.type = type;
            return this;
        }

        private boolean checkColumnForm(String table, String col) {
            if (col.toLowerCase().contains(table + ".") == true) {
                return true;
            }
            return false;
        }

        private String[] completeColumnForm(String leftCol, String rightCol) {
            final String form = "@{table}.@{column}";
            if (checkColumnForm(leftTable, leftCol) == false) {
                leftCol = StringConvertor.convertKeyToValue(form,
                        new String[]{"@{table}", "@{column}"},
                        new String[]{leftTable, leftCol});
            }
            if (checkColumnForm(rightTable, rightCol) == false) {
                rightCol = StringConvertor.convertKeyToValue(form,
                        new String[]{"@{table}", "@{column}"},
                        new String[]{rightTable, rightCol});
            }
            return new String[]{leftCol, rightCol};
        }

        public JoinClause on(String compareType, String leftColumn, String rightColumn) {
            String[] cols = completeColumnForm(leftColumn, rightColumn);
            String result = StringConvertor.convertKeyToValue(join,
                    new String[]{"@{leftColumn}", "@{rightColumn}", "@{compareType}"},
                    new String[]{cols[0], cols[1], compareType});
            JoinClause clause = new JoinClause(result);
            clause.setAll(leftTable, rightTable, leftColumn, rightColumn, type);
            return clause;
        }

        public JoinClause on(EqualMappedColumn operator) {
            String[] cols = completeColumnForm(operator.getLeftColumn(), operator.getRightColumn());
            String result = StringConvertor.convertKeyToValue(join,
                    new String[]{"@{leftColumn}", "@{rightColumn}", "@{compareType}"},
                    new String[]{cols[0], cols[1], operator.getOperator()});
            JoinClause clause = new JoinClause(result);
            clause.setAll(leftTable, rightTable, operator.getLeftColumn(), operator.getRightColumn(), type);
            return clause;
        }
    }

}
