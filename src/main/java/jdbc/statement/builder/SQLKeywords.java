package jdbc.statement.builder;

public class SQLKeywords {

    private SQLKeywords() {
    }

    public static SQLKeywords getInstance() {
        return MySqlKeywInstance.INSTANCE;
    }

    private static final class MySqlKeywInstance {

        private static final SQLKeywords INSTANCE = new SQLKeywords();
    }

    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String JOIN = "JOIN";
    public static final String WHERE = "WHERE";

}
