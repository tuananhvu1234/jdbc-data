package com.mycompany.jdbc.clause.builder;

/**
 *
 * @author DELL
 */
public class AliasClause {

    private AliasClause() {
    }

    private static String result;

    public static AliasClause as(String originName, String aliasName) {
        result = new StringBuilder().append(originName).append(" AS ").append(aliasName)
                .toString();
        return new AliasClause();
    }

    public static boolean isAliasFormat(String str) {
        return str.toLowerCase().contains(" as ");
    }

    public static String getOriginName(String str) {
        if (str != null && str.isEmpty() == false) {
            if (isAliasFormat(str) == true) {
                int end = str.toLowerCase().indexOf(" as ");
                return str.substring(0, end);
            }
        }
        return str;
    }

    public static String getAliasName(String str) {
        if (str != null && str.isEmpty() == false) {
            if (isAliasFormat(str) == true) {
                int start = str.toLowerCase().indexOf(" as ");
                return str.substring(start + 4, str.length());
            }
        }
        return str;
    }

    @Override
    public String toString() {
        return result;
    }
}
