package jdbc.statement.builder;

public class SQLVariables {

    private SQLVariables() {
    }

    public static SQLVariables getInstance() {
        return MySqlVarInstance.INSTANCE;
    }

    private static final class MySqlVarInstance {

        private static final SQLVariables INSTANCE = new SQLVariables();
    }

    public static final String COLUMNS = "select_expr";
    public static final String FROM_CLAUSE = "from_clause";
    public static final String TABLE_REFERENCES = "table_references";
}
