package jdbc.type;

import jdbc.sql.elements.SQLSchema;
import jdbc.statement.builder.SQLStatementBuilder;

public class MySql {

    private MySql() {
    }

    public SQLStatementBuilder build(StatementType type) {
        switch (type) {
            case INSERT:

                break;
            case QUERY:
                return null;
            default:
                return null;
        }
        return null;
    }

}
