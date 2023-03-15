package com.mycompany.jdbc.sql.data;

/**
 *
 * @author DELL
 */
public class RowCondition {

    // Tổng số dòng cần lây ra.
    // Nếu nhỏ hơn 0 thì sẽ lấy tất cả.
    private int limitRow = -1;
    // Vị trí dòng đầu lấy ra.
    // Nếu nhỏ hơn hoặc bằng 0 thì sẽ lấy từ 0.
    private int offsetRow = 0;
    //
    private int countFrom = 0;

    // Điều kiện.
    // order by -> boolean -> sort
    // group by -> boolean -> comparing
    // unique data -> boolean -> comparing
    // where search (column, data)
    public RowCondition maxResultCount(int count) {
        if (count < 0) {
            throw new IndexOutOfBoundsException("");
        }
        this.limitRow = count;
        return this;
    }

    public RowCondition skipRows(int rows) {
        if (rows < 0) {
            throw new IndexOutOfBoundsException("");
        }
        this.offsetRow = rows;
        return this;
    }

    public RowCondition countFrom(int value) {
        this.countFrom = value;
        return this;
    }

    protected int getMaxRow() {
        return limitRow;
    }

    protected int getOffsetRow() {
        return offsetRow;
    }

    protected int getCountFrom() {
        return countFrom;
    }

}
