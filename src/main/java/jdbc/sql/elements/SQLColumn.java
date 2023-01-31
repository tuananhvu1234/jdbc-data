package jdbc.sql.elements;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLColumn {

    private String columnName;
    private String dataType;
    private Class classDataType;

    public SQLColumn(String columnName, String dataType) {
        this.columnName = columnName;
        this.dataType = dataType;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public Class getClassDataType() {
        try {
            this.classDataType = Class.forName(this.dataType);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.classDataType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Column{");
        sb.append("columnName=").append(columnName);
        sb.append(", dataType=").append(dataType);
        sb.append('}');
        return sb.toString();
    }

}
