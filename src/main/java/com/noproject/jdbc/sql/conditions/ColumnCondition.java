/*
 * 
 */
package com.noproject.jdbc.sql.conditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author No
 */
public class ColumnCondition {

    private int startIndex;
    private int endIndex;
    private List<Integer> listColumnIndex;
    private List<String> listColumnNames;

    // Các cột bắt dầu từ start đến end. (bao gồm cả start và end).
    public ColumnCondition columns(int start, int end) {
        if (start < 1 || end < 1) {
            throw new IndexOutOfBoundsException();
        }
        if (start > end) {
            throw new IndexOutOfBoundsException();
        }
        if (listColumnIndex == null) {
            listColumnIndex = new ArrayList<>();
            while (start <= end) {
                listColumnIndex.add(start++);
            }
            return this;
        } else {
            Set<Integer> set = new HashSet<>(listColumnIndex);
            while (start <= end) {
                set.add(start++);
            }
//            this.listColumnIndex = set.stream().toList();
            return this;
        }
    }

    public ColumnCondition columns(Integer... listIndex) {
        for (Integer item : listIndex) {
            if (item < 1) {
                throw new IndexOutOfBoundsException();
            }
        }
        if (this.listColumnIndex == null) {
            this.listColumnIndex = Arrays.asList(listIndex);
            return this;
        } else {
            Set<Integer> set = new HashSet<>(listColumnIndex);
            set.addAll(Arrays.asList(listIndex));
//            this.listColumnIndex = set.stream().toList();
            return this;
        }
    }

    public ColumnCondition columns(String... listNames) {
        for (String item : listNames) {
//            if (item.isBlank() == true) {
//                throw new NullPointerException();
//            }
        }
        this.listColumnNames = Arrays.asList(listNames);
        return this;
    }

    public List<Integer> getListColumnIndex() {
        return listColumnIndex;
    }

    public List<String> getListColumnNames() {
        return listColumnNames;
    }

}
