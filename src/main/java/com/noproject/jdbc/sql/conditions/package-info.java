/*
 * Công dụng : Thiết lập các điều kiện để thực hiện sql
 * 
 * Điều kiện về cột :
 * Lấy ra các cột nào.
 * 
 * Điều kiện về dòng :
 * Lấy ra bao nhiêu dòng.
 * Bắt đầu từ dòng nào.
 * 
 * Điều kiện về dữ liệu :
 * Dữ liệu duy nhất.
 * Dữ liệu cần lấy ra là gì.
 * Dữ liệu group
 * Dữ liệu order
 * 
 * Áp dụng : Adapter Pattern.
 * 
 * class adapter : ConditionAdapter
 * Nhận vào Connection, TableCondition, ColumnCondition, RowCondition.
 * Cho ra các điều kiện đã được căn chỉnh phù hợp.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
package com.noproject.jdbc.sql.conditions;
