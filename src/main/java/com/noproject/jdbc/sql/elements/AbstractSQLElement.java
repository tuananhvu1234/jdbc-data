package com.noproject.jdbc.sql.elements;

import java.sql.Connection;

/**
 *
 * @author
 */
public abstract class AbstractSqlElement {

    public abstract Connection getConnection();

    public abstract String getElementName();

    protected abstract void setAllSubElementNames();

    public abstract String[] getAllSubElementNames();

    public abstract int getSubElementCount();

    protected boolean validateIndex(int index) {
        if (index < 1 || index > getSubElementCount()) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param index Ví trí cột cần lấy tên. Bắt đầu từ 1.
     * @return
     */
    public abstract String getSubElementName(int index);

}
