package jdbc.statement.builder;

import java.util.ArrayList;
import java.util.List;
import jdbc.sql.elements.SQLColumn;
import jdbc.statement.format.mysql.MySqlStatementFormat;
import jdbc.sql.elements.SQLTable;

public class MySqlStatementBuilder {

    private StringBuilder statement;

    private String tblName;

    private String[] tblColumns;

    private MySqlStatementBuilder() {
    }

    private static class BuilderInstance {

        private static final MySqlStatementBuilder INSTANCE = new MySqlStatementBuilder();
    }

    public static MySqlStatementBuilder getInstance() {
        return BuilderInstance.INSTANCE;
    }

    public MySqlStatementBuilder select(String[] columns) {
        this.tblColumns = columns;
        return this;
    }

    public MySqlStatementBuilder from(String tableName) {
        this.tblName = tableName;
        return this;
    }

    public MySqlStatementBuilder select(String tableName) {
        if (tableName.isEmpty()) {
            throw new NullPointerException("Your table name could not be found. Your table name is EMPTY.");
        }
        return this;
    }

    public String getStatement() {
        return StringConvertor.removeDoubleSpaces(statement.toString());
    }

}
