/**
 *
 * Hướng sử dụng của client.
 *
 * client
 * 1. lấy ra loại database
 * 2. từ loại database được chọn sẽ lấy ra loại câu lệnh cần build
 * 3. từ loại câu lệnh lấy ra class chịu trách nhiêm build
 * 4. từ class build tạo kết quả trả về.
 *
 * Mục đích : Hỗ trợ tạo câu lệnh sql.
 *
 * Kỹ thuật : Sử dụng Abstract Factory Pattern
 *
 * Các class tham gia :
 *
 * Factory class : SqlStatementBuilder
 *
 * Công dụng của class : Lấy ra loại database cần build (mysql, mssql).(1)
 *
 * Abstract Factory class : AbstractStatementBuilder
 *
 * Công dụng của class : Làm khuôn mẫu cho các class lấy lệnh.
 *
 * Concrete Factory class : MysqlStatementBuilder & MssqlStatementBuilder implements AbstractStatementBuilder
 *
 * Công dụng của class : Lấy ra loại câu lệnh cần build(query, insert, update, delete).(2)
 *
 * Tạo đường dẫn đến các class builder(QueryBuilder, InsertBuilder).(3)
 *
 * Abstract Product class : AbstractQueryBuilder
 *
 * Công dụng của class : Khuôn mẫu sản phẩm cần tạo.
 *
 * Product class : MysqlQueryBuilder & MssqlQueryBuilder implements AbstractQueryBuilder
 *
 * Công dụng của class : Tạo ra sản phẩm và trả về.(4)
 *
 */
package com.mycompany.jdbc.statement.builder;
