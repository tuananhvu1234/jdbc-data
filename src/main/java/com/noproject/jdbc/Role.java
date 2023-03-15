package com.noproject.jdbc;

import com.noproject.jdbc.activerecord.MappedColumn;
import com.noproject.jdbc.activerecord.MappedTable;
import com.noproject.jdbc.activerecord.Mapper;

/**
 *
 * @author DELL
 */
public class Role extends MappedTable {

    public static final Role ROLE = (Role) Mapper.mappingTable(Role.class, "role");

    public final MappedColumn ROLE_ID = Mapper.mappingColumn("role_id");

    public final MappedColumn ROLE_NAME = Mapper.mappingColumn("role_name");

}
