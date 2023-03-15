/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.noproject.jdbc;

import com.noproject.jdbc.activerecord.MappedColumn;
import com.noproject.jdbc.activerecord.MappedTable;
import com.noproject.jdbc.activerecord.Mapper;

/**
 *
 * @author DELL
 */
public class User extends MappedTable {

    public static final User USER = (User) Mapper.mappingTable(User.class, "user");

    public final MappedColumn USER_ID = Mapper.mappingColumn("user_id");
    public final MappedColumn FULL_NAME = Mapper.mappingColumn("full_name");

//    @Override
//    public MappingColumn[] ALL() {
//        return Mapper.ALL();
//    }
}
