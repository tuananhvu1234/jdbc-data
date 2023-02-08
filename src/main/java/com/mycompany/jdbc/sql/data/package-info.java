/**
 * Mục đích : Hỗ trợ query và update.
 * 
 * Kỹ thuật : Áp dụng Adapter Pattern.
 * 
 * Các class tham gia :
 * - Target : interface SQLActions.
 *      Công dụng : Cung cấp các method cho người dùng.
 * - Adapter : class Actions implements SQLActions.
 *      Công dụng : Triển khai các method implement.
 * - Adaptee : class SetSQL.
 *      Công dụng : Thiết lập các điều kiện.
 */
package com.mycompany.jdbc.sql.data;
