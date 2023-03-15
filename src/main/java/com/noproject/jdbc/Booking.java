/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.noproject.jdbc;

import com.noproject.jdbc.activerecord.MappedColumn;
import com.noproject.jdbc.activerecord.MappedTable;
import com.noproject.jdbc.activerecord.MappingColumn;
import com.noproject.jdbc.activerecord.MappingTable;

/**
 *
 * @author DELL
 */
public class Booking extends MappedTable {

    public static final Booking BOOKING = MappingTable.mapping(Booking.class, "booking");

    public final MappedColumn BOOKING_ID = MappingColumn.mapping("booking_id");

//    @Override
//    public <T extends MappedTable> MappingColumn[] ALL() {
//        return new MappingColumn[]{BOOKING_ID};
//    }
}
