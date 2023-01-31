package jdbc.statement.builder;

import jdbc.sql.elements.SQLSchema;
import jdbc.type.DatabaseType;
import jdbc.type.StatementType;

public class SQLStatementBuilder {

    private SQLStatementBuilder() {
    }

    private static class SQLStatementBuilderHolder {

        private static final SQLStatementBuilder INSTANCE = new SQLStatementBuilder();
    }

    public static SQLStatementBuilder using(SQLSchema schema) {
        return SQLStatementBuilderHolder.INSTANCE;
    }

    public SQLStatementBuilder build(DatabaseType sqlType, StatementType statementType) {
        switch (sqlType) {
            case MYSQL:
                switch (statementType) {
                    case QUERY:
//                        return MySQLQueryBuilder.getInstance();
                    default:
                }
                break;

            default:
        }
        return SQLStatementBuilderHolder.INSTANCE;
    }
}
