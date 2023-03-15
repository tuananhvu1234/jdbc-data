package com.mycompany.jdbc.sql.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class ColumnCondition {

    /* Thiết lập index của các cột cần lấy dữ liệu.
     * Lấy ra index của các cột bắt đầu từ index của cột đầu tiên đến index của cột cuối cùng.
     * Bao gồm cả index của cột đầu tiên và index của cột cuối cùng.
     * Các cột có index nhỏ hơn index của cột đầu tiên và lớn hơn index của cột cuối cùng sẽ không được lấy ra.
     * Có thể thiết lập chi tiết index của các cột.
     * Nếu không được thiết lập thì
     * giá trị index của cột đầu tiên sẽ bằng 1,
     * giá trị index của cột cuối cùng sẽ bằng với số lượng cột trong bảng,
     * danh sách index sẽ bao gồm index của tất cả các cột có trong bảng.
     * Nếu giá trị index của cột đầu tiên, cột cuối cùng và danh sách index cùng được thiết lập thì
     * sẽ kết hợp lại thành một danh sách chứa tất cả các index đã được thiết lập.
     * Mỗi một index chỉ xuất hiện một lần.
     */
    private int /*            */ firstColumnIndex;
    private int /*            */ lastColumnIndex;
    private List<Integer> listColumnIndex = new ArrayList<>();
    /* Thiết lập tên các cột cần lấy dữ liệu.
     * Nếu không được thiết lập thì
     * danh sách tên cột sẽ bao gồm tên của tất cả các cột trong bảng.
     * Nếu danh sách các index và danh sách tên của các cột cùng được thiết lập thì
     * sẽ kết hợp lại thành danh sách chứa tất cả các tên cột đã được thiết lập
     * và tên cột tương ứng với các index trong danh sách index của cột.
     * Mỗi một tên chỉ xuất hiện một lần.
     */
    private List<String> listColumnNames = new ArrayList<>();
    /*
     * Cần thiết lập ResultSet để lấy ra tất cả các cột trong bảng.
     * Danh sách các cột trong bảng (index, tên cột).
     */
    private ResultSet /*                    */ resultSet = null;
    private final Map<Integer, String> listAllColumns = new LinkedHashMap<>();

    public ColumnCondition endsAt(int end) {
        if (end < 1) {
            throw new IndexOutOfBoundsException("ket thuc nho hon 1");
        }
        if (firstColumnIndex > 0 && firstColumnIndex > end) {
            throw new IndexOutOfBoundsException("ket thuc nho hơn bắt đầu");
        }
        this.lastColumnIndex = end;
        return this;
    }

    public ColumnCondition startFrom(int start) {
        if (start < 1) {
            throw new IndexOutOfBoundsException("bat dau nho hon 1");
        }
        if (lastColumnIndex > 0 && start > lastColumnIndex) {
            throw new IndexOutOfBoundsException("bat dau lon hon ket thuc");
        }
        this.firstColumnIndex = start;
        return this;
    }

    public ColumnCondition columns(int start, int end) {
        return startFrom(start).endsAt(end);
    }

    public ColumnCondition columns(Integer... index) {
        for (int i = 0; i < index.length; i++) {
            if (index[i] < 1) {
                throw new IndexOutOfBoundsException("Invalid column index at position " + (i + 1) + "!");
            }
        }
        this.listColumnIndex = Arrays.asList(index);
        return this;
    }

    public ColumnCondition columns(String... names) {
        for (int i = 0; i < names.length; i++) {
            if (names[i] == null || names[i].isBlank() == true) {
                throw new NullPointerException("Invalid column name at position " + (i + 1) + "!");
            }
        }
        this.listColumnNames = Arrays.asList(names);
        return this;
    }

    protected void valueForResultSet(ResultSet rs) {
        if (rs == null) {
            throw new NullPointerException("ResultSet khong duoc phep null");
        }
        this.resultSet = rs;
    }

    protected Map<Integer, String> getAllColumnsInTable() {
        try {
            final ResultSetMetaData rsmd = this.resultSet.getMetaData();
            for (int index = 1; index <= rsmd.getColumnCount(); index++) {
                listAllColumns.put(index, rsmd.getColumnName(index));
            }
            return listAllColumns;
        } catch (SQLException ex) {
            Logger.getLogger(ColumnCondition.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    protected final int getLastColumnIndex() {
        final int maxIndex = getAllColumnsInTable().size();
        if (listColumnNames.isEmpty() == true
                && listColumnIndex.isEmpty() == true
                && lastColumnIndex < 1
                || lastColumnIndex > maxIndex) {
            return maxIndex;
        }
        return lastColumnIndex;
    }

    protected final int getFirstColumnIndex() {
        final int lastIndex = getLastColumnIndex();
        if (firstColumnIndex < 1) {
            return 1;
        }
        if (firstColumnIndex > lastIndex) {
            return lastIndex;
        }
        return firstColumnIndex;
    }

    protected final List<String> getListColumnNamesByBlock() {
        final List<String> result = new ArrayList<>();
        final int start = getFirstColumnIndex(), end = getLastColumnIndex();
        if (end >= start) {
            getAllColumnsInTable().forEach((index, columnName) -> {
                if (index >= start && index <= end) {
                    result.add(columnName);
                }
            });
            return result;
        }
        return result;
    }

    protected final List<String> getListColumnNamesByIndex() {
        final Map<Integer, String> tableColumns = getAllColumnsInTable();
        final List<String> result = new ArrayList<>();
        listColumnIndex.stream().forEach(index -> {
            result.add(tableColumns.get(index));
        });
        return result;
    }

    protected final List<String> getListColumnNames() {
        final Map<Integer, String> tableColumns = getAllColumnsInTable();
        final List<String> result = new ArrayList<>();
        listColumnNames.stream().forEach(colName -> {
            if (tableColumns.containsValue(colName) == true) {
                result.add(colName);
            }
        });
        return result;
    }

    /* Danh sách các tên các cột cần lấy dữ liệu.
     * Kết hợp các điều kiện lại để trả ra danh sách tên các cột phù hợp.
     * Mỗi một tên chỉ xuất hiện một lần.
     */
    protected final List<String> getListMixedColumnNames() {
        // Lấy tất cả cột trong bảng.
        final Map<Integer, String> tableColumns = getAllColumnsInTable();
        // Lấy các cột theo khối.
        final List<String> listNameByBlock = getListColumnNamesByBlock();
        // Nếu tất cả cột trong khối tồn tại trong bảng thì return luôn bảng.
        if (listNameByBlock.size() == tableColumns.size()
                && tableColumns.values().containsAll(listNameByBlock)) {
            return tableColumns.values().stream().toList();
        }
        // Nếu không thì qua đây.
        final List<String> listNameByNames = getListColumnNames(),
                listNameByIndex = getListColumnNamesByIndex();
        // Nếu list block chứa cả 2 list name và index thid return luôn list block.
        if (listNameByBlock.containsAll(listNameByNames)
                && listNameByBlock.containsAll(listNameByIndex)) {
            return listNameByBlock;
        }
        // Nếu list block không chứa 1 trong 2 list trên thì xuống đây.
        final Set<String> listMixed = new HashSet<>(listNameByBlock);
        // Nếu list mix không chứa list name thì thêm vào.
        if (listMixed.containsAll(listNameByNames) == false) {
            listMixed.addAll(listNameByNames);
        }
        // Nếu list mix không chưa list index thì thêm vào.
        if (listMixed.containsAll(listNameByIndex) == false) {
            listMixed.addAll(listNameByIndex);
        }
        // Nếu tất cả các cột trong list mix có trong table thì return luôn table.
        if (listMixed.size() == tableColumns.size()
                && tableColumns.values().containsAll(listMixed)) {
            return tableColumns.values().stream().toList();
        }
        // Nếu không thì xuống đây.
        // Với mỗi name trong list mix tồn tại trong table thì sẽ được thêm vào result.
        final List<String> result = new ArrayList<>();
        tableColumns.values().stream().forEach(columnName -> {
            if (listMixed.contains(columnName) == true) {
                result.add(columnName);
            }
        });
        return result;
    }

}
