package com.mycompany.jdbc.statement.format.mysql;

/**
 * Áp dụng cho mysql version 8.0
 *
 * @author DELL
 */
public class MySqlStatementFormat {

    // >> (not) requirement >> key name >> {value}
    /*
    
    >>>> select
    
    >> requirement       >> SELECT >> SELECT
    >> not requirement >> select_options >>
        {
            [ALL | DISTINCT | DISTINCTROW ] [HIGH_PRIORITY] [STRAIGHT_JOIN]
            [SQL_SMALL_RESULT] [SQL_BIG_RESULT] [SQL_BUFFER_RESULT]
            [SQL_NO_CACHE] [SQL_CALC_FOUND_ROWS]
        }
    >> requirement       >> columns >> select_expr [, select_expr] ...
    (k dung)[into_option]
    >> requirement       >> from_clause >> FROM table_references
    >> not requirement >> partition_clause >> [PARTITION partition_list]
    >> not requirement >> where_clause >> [WHERE where_condition]
    >> not requirement >> group_by_clause >> [GROUP BY {col_name | expr | position}, ... [WITH ROLLUP]]
    >> not requirement >> having_clause >> [HAVING where_condition]
    >> not requirement >> window_clause >>
        {
            [WINDOW window_name AS (window_spec)
            [, window_name AS (window_spec)] ...]
        }
    >> not requirement >> order_by_clause >> [ORDER BY {col_name | expr | position}
    >> not requirement >> order_by_options >>
        {
            >> requirement       >> [ASC | DESC],
            >> not requirement >> ... [WITH ROLLUP]]
        }
    >> not requirement >> limit_clause >> [LIMIT {[offset,] row_count | row_count OFFSET offset}]
    (k dung) [into_option]
    >> not requirement >> for_clause >>
        {
            [ FOR {UPDATE | SHARE}
                [OF tbl_name [, tbl_name] ...]
                [NOWAIT | SKIP LOCKED]
            | LOCK IN SHARE MODE ]
        }
    >> not requirement >> into_options >> [into_option]

    into_option: {
        INTO OUTFILE 'file_name'
            [CHARACTER SET charset_name]
            export_options
      | INTO DUMPFILE 'file_name'
      | INTO var_name [, var_name] ...
    }
    
    
    >>>> form & join
    
    table_references:
    escaped_table_reference [, escaped_table_reference] ...

    escaped_table_reference: {
        table_reference
      | { OJ table_reference }
    }

    table_reference: {
        table_factor
      | joined_table
    }

    table_factor: {
        tbl_name [PARTITION (partition_names)]
            [[AS] alias] [index_hint_list]
      | [LATERAL] table_subquery [AS] alias [(col_list)]
      | ( table_references )
    }

    joined_table: {
        table_reference {[INNER | CROSS] JOIN | STRAIGHT_JOIN} table_factor [join_specification]
      | table_reference {LEFT|RIGHT} [OUTER] JOIN table_reference join_specification
      | table_reference NATURAL [INNER | {LEFT|RIGHT} [OUTER]] JOIN table_factor
    }

    join_specification: {
        ON search_condition
      | USING (join_column_list)
    }

    join_column_list:
        column_name [, column_name] ...

    index_hint_list:
        index_hint [, index_hint] ...

    index_hint: {
        USE {INDEX|KEY}
          [FOR {JOIN|ORDER BY|GROUP BY}] ([index_list])
      | {IGNORE|FORCE} {INDEX|KEY}
          [FOR {JOIN|ORDER BY|GROUP BY}] (index_list)
    }

    index_list:
        index_name [, index_name] ...

    
    
    
    
     */
    // statement
    // select
    public static final String SELECT_STATEMENT = "";
//            = StringConvertor.getRequirementMySqlKeyword(SQLKeywords.SELECT)
//            + StringConvertor.getNotRequirementVariable("select_options")
//            + StringConvertor.getRequirementVariable(SQLVariables.COLUMNS)
//            + StringConvertor.getRequirementVariable(SQLVariables.FROM_CLAUSE)
//            + StringConvertor.getVariable("where_clause")
//            + StringConvertor.getVariable("group_by_clause")
//            + StringConvertor.getVariable("having_clause")
//            + StringConvertor.getVariable("window_clause")
//            + StringConvertor.getVariable("order_by_clause")
//            + StringConvertor.getVariable("limit_clause")
//            + StringConvertor.getVariable("for_clause")
//            + StringConvertor.getVariable("into_options");

    // clause
    // with
    // $_cte_name_! $_with_cloumns_? = $_origin_name_!
    // $_with_subquery_! = $_alias_name_!
    public static final String WITH_CLAUSE = "";
//            = StringConvertor.getMySqlKeyword("WITH") + " "
//            + StringConvertor.getMySqlKeyword("RECURSIVE") + " "
//            + StringConvertor.getVariable("alias_clause") + " "
//            + StringConvertor.getVariable("more_subquery") + " ";
    // alias
    public static final String ALIAS_CLAUSE = "";
//            = StringConvertor.defineRequirementVariable("origin_name") + " "
//            + "AS" + " "
//            + StringConvertor.defineRequirementVariable("alias_name") + " ";
    // from
    public static final String FROM_CLAUSE = "";
//            = StringConvertor.getRequirementMySqlKeyword(SQLKeywords.FROM)
//            + StringConvertor.getRequirementVariable(SQLVariables.TABLE_REFERENCES)
//            + StringConvertor.getVariable("partition_clause");
    public static final String TABLE_REFERENCES = "";
//            = StringConvertor.getRequirementMySqlKeyword(SQLKeywords.JOIN);
    public static final String JOIN_CLAUSE = "";
//            = StringConvertor.getRequirementMySqlKeyword(SQLKeywords.JOIN);
    // partition
    public static final String PARTITION_CLAUSE = "PARTITION $_partition_list_!";
    // where
    public static final String WHERE_CLAUSE = "WHERE $_where_conditions_!";
    // group by
    public static final String GROUP_BY_CLAUSE = "GROUP BY $_group_conditions_!";
    // having
    public static final String HAVING_CLAUSE = "HAVING $_having_conditions_! $partition_clause?";
    // window
    public static final String WINDOW_CLAUSE = "WINDOW $_window_alias_! $_more_window_?";
    // order by
    public static final String ORDER_BY_CLAUSE = "ORDER BY $_order_conditions_! $_order_by_option_!";
    // options
    // select
    public static final String SELECT_OPTIONS
            = "@_ALL_DISTINCT_DISTINCTROW_? @_HIGH_PRIORITY_? @_STRAIGHT_JOIN_? "
            + "@_SQL_SMALL_RESULT_? @_SQL_BIG_RESULT_? @_SQL_BUFFER_RESULT_? "
            + "@_SQL_NO_CACHE_? @_SQL_CALC_FOUND_ROWS_?";
    // into
    public static final String INTO_OPTIONS
            = "INTO @_OUTFILE_DUMPFILE_? $_into_location_!";
    // order by
    public static final String ORDER_BY_OPTIONS = "@_ASC_DESC_! @_WITH_ROLLUP_?";

}
