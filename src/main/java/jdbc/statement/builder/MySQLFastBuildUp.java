package jdbc.statement.builder;

import java.util.ArrayList;
import java.util.List;
import jdbc.sql.elements.SQLTable;
import jdbc.statement.format.mysql.MySqlStatementFormat;

public class MySQLFastBuildUp {

    protected final List<SQLTable> listSelectTable = new ArrayList<>();

    protected String addQuery() {
        String localStatement = MySqlStatementFormat.SELECT_STATEMENT;
//        localStatement = FormatHelper.convertKeyToValue(localStatement,
//                FormatHelper.getRequirementVariable(SQLVariables.FROM_CLAUSE),
//                MySqlStatementFormat.FROM_CLAUSE);
//        localStatement = FormatHelper.convertKeyToValue(localStatement,
//                FormatHelper.getRequirementMySqlKeyword(SQLKeywords.SELECT),
//                SQLKeywords.SELECT + " ");
//        localStatement = FormatHelper.convertKeyToValue(localStatement,
//                FormatHelper.getRequirementMySqlKeyword(SQLKeywords.FROM),
//                " " + SQLKeywords.FROM + " ");
        return localStatement;
    }

    protected String select(SQLTable table) {
        String localStatement = addQuery();
//        localStatement = FormatHelper.convertKeyToValue(localStatement,
//                FormatHelper.getRequirementVariable(SQLVariables.COLUMNS),
//                table.getTableColumns());
//        localStatement = FormatHelper.convertKeyToValue(localStatement,
//                FormatHelper.getRequirementVariable(SQLVariables.TABLE_REFERENCES),
//                "`" + table.getTableName() + "`");
        return localStatement;
    }

    private String setJoinOn(String[] table, String[] joinElements) {
        String[] result;
        if (joinElements.length % 3 == 1) {
            result = new String[]{joinElements[0], "=", joinElements[0]};
        } else {
            result = joinElements;
        }
        return "ON `" + table[0] + "`.`" + result[0] + "`"
                + " " + result[1] + " "
                + "`" + table[1] + "`.`" + result[2] + "`";
    }

    protected String join(String joinType, String[] joinOn) {
        String localStatement = addQuery();
        String listColumns = "";
        String joinTable = "";
        for (int i = 0; i < listSelectTable.size(); i++) {
//            listColumns += listSelectTable.get(i).getTableColumns();
            joinTable += "`" + listSelectTable.get(i).getTableName() + "`";
            if (i < listSelectTable.size() - 1) {
                listColumns += ", ";
                joinTable += " " + joinType + " " + SQLKeywords.JOIN + " ";
            }
//            joinTable += setJoinOn(new String[]{"role", "user"}, joinOn);
        }
//        joinTable += setJoinOn(joinOn);
//        for (int y = 0; y < joinOn.length; y++) {
//            
//        }
        System.out.println(setJoinOn(new String[]{"role", "user"}, joinOn));

//        localStatement = FormatHelper.convertKeyToValue(localStatement,
//                FormatHelper.getRequirementVariable(SQLVariables.COLUMNS),
//                listColumns);
//        localStatement = FormatHelper.convertKeyToValue(localStatement,
//                FormatHelper.getRequirementVariable(SQLVariables.TABLE_REFERENCES),
//                joinTable);
        return localStatement;
    }
}
