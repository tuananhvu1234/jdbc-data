package com.mycompany.jdbc.sql.elements;

import java.sql.Connection;

/**
 *
 * @author
 */
public abstract class AbstractSQLElement {

    protected boolean checkExistSubElement(
            AbstractSQLElement element,
            String subElementName
    ) {
        for (String subElmName : element.getAllSubElementNames()) {
            if (subElmName.toLowerCase()
                    .equals(subElementName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

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
