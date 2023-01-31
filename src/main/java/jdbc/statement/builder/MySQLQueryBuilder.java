package jdbc.statement.builder;

/**
 *
 * @author DELL
 */
public class MySQLQueryBuilder {

    private MySQLQueryBuilder() {
    }

    public static MySQLQueryBuilder getInstance() {
        return MySQLQueryBuilderHolder.INSTANCE;
    }

    private static class MySQLQueryBuilderHolder {

        private static final MySQLQueryBuilder INSTANCE = new MySQLQueryBuilder();
    }

    private StringBuilder statement;

}
