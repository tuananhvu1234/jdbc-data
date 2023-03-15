package com.noproject.jdbc.clause;

import com.noproject.jdbc.helper.StringConvertor;

/**
 *
 * @author DELL
 */
public class AliasClause extends Clause {

    private final String result;
    private String originName;
    private String aliasName;
    private static String copyOriginName;
    private static String copyAliasName;

    public AliasClause(String after) {
        this.result = after;
    }

    @Override
    public String getResult() {
        return result;
    }

    private void setOriginName(String origin) {
        originName = origin;
        copyOriginName = originName;
    }

    private void setAliasName(String alias) {
        aliasName = alias;
        copyAliasName = aliasName;
    }

    private static class AliasClauseHandle {

        private static final String FORMAT = "@{originName} AS @{aliasName}";

        private static AliasClause handle(String origin, String alias) {
            AliasClause clause
                    = new AliasClause(StringConvertor.convertKeyToValue(FORMAT,
                            new String[]{"@{originName}", "@{aliasName}"},
                            new String[]{origin, alias}));
            clause.setOriginName(origin);
            clause.setAliasName(alias);
            return clause;
        }
    }

    public static AliasClause as(String origin, String alias) {
        if (origin != null && alias != null) {
            return AliasClauseHandle.handle(origin, alias);
        }
        return null;
    }

    public static boolean isAliasFormat(String str) {
        final String key = " as ";
        if (str.toLowerCase().contains(key) == true) {
            AliasClause clause = new AliasClause(str);
            int end = str.toLowerCase().indexOf(key);
            clause.setOriginName(str.substring(0, end));
            int start = end + key.length();
            clause.setAliasName(str.substring(start, str.length()));
            return true;
        }
        return false;
    }

    public static String getOriginName(String str) {
        if (str != null && str.isEmpty() == false) {
            if (isAliasFormat(str) == true) {
                return copyOriginName;
            }
        }
        return str;
    }

    public static String getAliasName(String str) {
        if (str != null && str.isEmpty() == false) {
            if (isAliasFormat(str) == true) {
                return copyAliasName;
            }
        }
        return str;
    }

    public String getOriginName() {
        return getOriginName(result);
    }

    public String getAliasName() {
        return getAliasName(result);
    }

}
