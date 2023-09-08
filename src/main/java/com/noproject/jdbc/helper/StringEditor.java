package com.noproject.jdbc.helper;

import com.noproject.pattern.BaseMemento;
import java.util.LinkedList;
import java.util.List;

public final class StringEditor {

    private static class Memento{

        private final List<String> updateHistory = new LinkedList<>();
        private int countUndo = 1;

        private void save(String str) {
            updateHistory.add(str);
        }

        private void undo(StringEditor se, int count) {
            countUndo++;
            int index = lastIndex() - count;
            se.removeAll();
            updateHistory.remove(lastIndex());
            se.append(updateHistory.get(index < 0 ? 0 : index));
            updateHistory.remove(lastIndex());
        }

        private void redo(StringEditor se, int count) {
            countUndo = countUndo > updateHistory.size()
                    ? updateHistory.size() : countUndo;
            int index = updateHistory.size() - countUndo + count;
            se.removeAll();
            updateHistory.remove(lastIndex());
            se.append(updateHistory.get(index > lastIndex() ? lastIndex() : index));
            if (index < lastIndex()) {
                updateHistory.remove(lastIndex());
            }
        }

        private int lastIndex() {
            return updateHistory.size() - 1;
        }

        private void clear() {
            updateHistory.clear();
        }
    }

    // Hỗ trợ format giống groovy ($key || ${key})
    private static final String[] KEY_FORMAT = new String[]{"$", "{", "}"};
    private static final String KEY_TO_VALUE_OPERATOR = "->";
    private static final String EMPTY_STRING = "";

    private final StringBuilder value;
    private final Memento memento = new Memento();

    public StringEditor(String str) {
        this.value = new StringBuilder(str);
        memento.save(value.toString());
    }

    public StringEditor(CharSequence str) {
        this.value = new StringBuilder(str);
        memento.save(value.toString());
    }

    private static String getKeyName(String str) {
        String result;
        if (str == null || str.isEmpty() == true) {
            return EMPTY_STRING;
        }
        result = String.join(EMPTY_STRING, str.split(KEY_FORMAT[0]));
        result = String.join(EMPTY_STRING, result.split("\\" + KEY_FORMAT[1]));
        result = String.join(EMPTY_STRING, result.split("\\" + KEY_FORMAT[2]));
        if (result.isEmpty() == true) {
            return EMPTY_STRING;
        }
        return result;
    }

    // Format : @{key}
    private static boolean isFullFormKey(String str) {
        if (str.startsWith(KEY_FORMAT[0] + KEY_FORMAT[1])
                && str.endsWith(KEY_FORMAT[2])) {
            return (getKeyName(str).isEmpty() == false);
        }
        return false;
    }

    // Format : @key
    private static boolean isFirstFormKey(String str) {
        if (str.startsWith(KEY_FORMAT[0])
                && str.contains(KEY_FORMAT[1]) == false
                && str.contains(KEY_FORMAT[2]) == false) {
            return (getKeyName(str).isEmpty() == false);
        }
        return false;
    }

    // Format : {key}
    @Deprecated
    private static boolean isSecondFormKey(String str) {
        if (str.contains(KEY_FORMAT[0]) == false
                && str.startsWith(KEY_FORMAT[1])
                && str.endsWith(KEY_FORMAT[2])) {
            return (getKeyName(str).isEmpty() == false);
        }
        return false;
    }

    private static int getKeyType(String key) {
        if (key == null || key.isEmpty() == true) {
            return 0;
        }
        if (isFullFormKey(key) == true) {
            return 1;
        }
        if (isFirstFormKey(key) == true) {
            return 2;
        }
        if (isSecondFormKey(key) == true) {
            return 3;
        }
        return -1;
    }

    private static boolean everyKeys(String[] arr) {
        if (arr == null || arr.length <= 0) {
            return false;
        }
        int formType = getKeyType(arr[0]);
        if (formType < 0) {
            return false;
        }
        for (String str : arr) {
            if (getKeyType(str) != formType) {
                return false;
            }
        }
        return true;
    }

    private static String completeKeyForm(String str) {
//        if (str == null || str.isBlank() == true) {
//            return null;
//        }
        StringBuilder result = new StringBuilder(str);
        if (isFullFormKey(result.toString()) == true) {
            return result.toString();
        } else if (isFirstFormKey(result.toString()) == true) {
            result.insert(1, KEY_FORMAT[1]);
            result.append(KEY_FORMAT[2]);
            return result.toString();
        } else if (isSecondFormKey(result.toString()) == true) {
            result.insert(0, KEY_FORMAT[0]);
            return result.toString();
        } else {
            result.insert(0, KEY_FORMAT[0]);
            result.insert(1, KEY_FORMAT[1]);
            result.append(KEY_FORMAT[2]);
            return result.toString();
        }
    }

    private static int[] getIndexOfAny(String origin, String str) {
//        if ((origin == null || origin.isBlank() == true)
//                || (str == null || str.isBlank() == true)) {
//            return new int[]{-1, -1};
//        }
        if (origin.contains(str) == true) {
            return new int[]{origin.indexOf(str), origin.indexOf(str) + str.length()};
        }
        return new int[]{-1, -1};
    }

    private static String getKeyFromOriginString(String str) {
        int start = str.indexOf(KEY_FORMAT[0] + KEY_FORMAT[1]);
        int end = str.indexOf(KEY_FORMAT[2], start);
        if ((start < end) && (start > -1)) {
            return str.substring(start, end + 1);
        }
        return EMPTY_STRING;
    }

    private static String[] splitAllWith(String[] arr, String str, int getIndex) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        String[] copyArr = new String[arr.length];
        for (int i = 0; i < copyArr.length; i++) {
            if (arr[i].contains(str) == true) {
                copyArr[i] = arr[i].split(str)[getIndex];
            } else {
                copyArr[i] = null;
            }
        }
        return copyArr;
    }

    @Deprecated
    public static String convertOneString(String origin, String key, String value) {
        StringBuilder stringBuilder = new StringBuilder(origin);
        int[] index = getIndexOfAny(origin, key);
        if (index[0] > -1 && index[1] > -1) {
            stringBuilder.replace(index[0], index[1], value);
        }
        return stringBuilder.toString();
    }

    @Deprecated
    public static String convertKeyToValue(String origin, String[] keys, String[] values) {
        if (everyKeys(keys) == false) {
            throw new UnsupportedOperationException("All keys are not in the same format.");
        }
        if (keys.length == values.length) {
            for (int i = 0; i < keys.length; i++) {
                origin = convertOneString(origin, completeKeyForm(keys[i]), values[i]);
            }
        }
        return origin;
    }

    @Deprecated
    public static String convertKeyToValue(String origin, String[] keysArrowValues) {
        if (everyKeys(splitAllWith(keysArrowValues, KEY_TO_VALUE_OPERATOR, 0)) == false) {
            throw new UnsupportedOperationException("All keys are not in the same format.");
        }
        if (keysArrowValues[0].contains(KEY_TO_VALUE_OPERATOR) == false) {
            for (String value : keysArrowValues) {
                origin = convertOneString(origin, getKeyFromOriginString(origin), value);
            }
            return origin;
        } else {
            return convertKeyToValue(origin,
                    splitAllWith(keysArrowValues, KEY_TO_VALUE_OPERATOR, 0),
                    splitAllWith(keysArrowValues, KEY_TO_VALUE_OPERATOR, 1));
        }
    }

    @Deprecated
    public static String convertEveryString(String origin, String from, String to) {
        while (origin.contains(from) == true) {
            origin = convertOneString(origin, from, to);
        }
        return origin;
    }

    // create : thêm string mới vào đoạn string ban đầu.
    public StringEditor append(String str) {
        value.append(str);
        memento.save(value.toString());
        return this;
    }

    public StringEditor append(StringBuffer sb) {
        value.append(sb);
        return this;
    }

    public StringEditor append(StringBuilder sb) {
        return append(sb.toString());
    }

    public StringEditor append(CharSequence csq) {
        value.append(csq);
        return this;
    }

    private String createKey(String keyName) {
        return null;
    }

    // update : sửa đổi string đã tồn tại.
    private void baseConvertor(String key, String value) {
        int[] index = getIndexOfString(key);
        if (index[0] > -1 && index[1] > -1) {
            this.value.replace(index[0], index[1], value);
        }
    }

    // thay đổi hoàn toàn 1 đoạn string với chiều dài bất kỳ.
    public StringEditor convert(String oldString, String newString) {
        baseConvertor(oldString, newString);
        return this;
    }

    public StringEditor convertKeyValue(String key, String value) {
        return convert(completeKeyForm(key), value);
    }

    // thay đổi 1 đoạn string với chiều dài ban đầu
    // string mới sẽ có length = string cũ.
    public StringEditor change(String oldString, String newString) {
        baseConvertor(oldString, newString);
        return this;
    }

    public StringEditor changeKeyName(String oldName, String newName) {
        baseConvertor(completeKeyForm(oldName), completeKeyForm(newName));
        return this;
    }

    public StringEditor move(String str, int toIndex) {
        int[] index = getIndexOfString(str);
        if (toIndex < index[0]) {
            remove(str);
            value.insert(toIndex, str);
            return this;
        } else if (toIndex > index[1]) {
            value.insert(toIndex, str);
            remove(str);
            return this;
        } else {
            return this;
        }
    }

    private int undoCount = 1;
    private int redoCount = 1;

    public StringEditor undo() {
        if (redoCount > 1) {
            redoCount--;
        }
        memento.undo(this, undoCount);
        undoCount++;
        return this;
    }

    public StringEditor redo() {
        if (undoCount > 1) {
            undoCount--;
        }
        memento.redo(this, redoCount);
        redoCount++;
        return this;
    }

    public StringEditor convertToKey(String str) {
        baseConvertor(str, completeKeyForm(str));
        return this;
    }

    public StringEditor convertToKey(String str, String keyName) {
        baseConvertor(str, completeKeyForm(keyName));
        return this;
    }

    public StringEditor reverse() {
        value.reverse();
        return this;
    }

    // delete : xóa string đã tồn tại.
    // xóa luôn không thể undo
    public StringEditor clear() {
        delete(0, length());
        memento.save(value.toString());
        return this;
    }

    public StringEditor delete(int start, int end) {
        value.delete(start, end);
        memento.clear();
        memento.save(value.toString());
        System.out.println(memento.updateHistory);
        return this;
    }

    public StringEditor delete(String str) {
        int[] index = getIndexOfString(str);
        return delete(index[0], index[1]);
    }

    // xóa và có thể undo
    public StringEditor remove(String str) {
        int[] index = getIndexOfString(str);
        value.delete(index[0], index[1]);
        memento.save(value.toString());
        return this;
    }

    public StringEditor removeAll() {
        value.delete(0, length());
        memento.save(value.toString());
        return this;
    }

    public StringEditor deleteCharAt(int index) {
        value.deleteCharAt(index);
        return this;
    }

    public StringEditor deletePrev(String str) {
        return delete(0, getIndexOfString(str)[0]);
    }

    public StringEditor deleteNext(String str) {
        delete(getIndexOfString(str)[1], length());
        return this;
    }

    public StringEditor deleteKey(String key) {
        return delete(completeKeyForm(key));
    }

    // read
    private int[] getIndexOfString(String str) {
        String origin = value.toString();
        if ((origin == null || isBlank(origin) == true)
                || (str == null || isBlank(str) == true)) {
            return new int[]{-1, -1};
        }
        if (origin.contains(str) == true) {
            return new int[]{origin.indexOf(str), origin.indexOf(str) + str.length()};
        }
        return new int[]{-1, -1};
    }

    private boolean isBlank(String str) {
        if (str.isEmpty() == true
                || String.join(EMPTY_STRING, str.split(" ")).isEmpty() == true) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isBlank() {
        return isBlank(value.toString());
    }

    public boolean isEmpty() {
        return value.toString().isEmpty();
    }

    public boolean equals(String str) {
        return value.toString().equals(str);
    }

    public boolean contains(String str) {
        return value.toString().contains(str);
    }

    public boolean containsKey(String key) {
        return contains(completeKeyForm(key));
    }

    public int length() {
        return value.length();
    }

    public char charAt(int index) {
        return value.charAt(index);
    }

    public int indexOf(String str) {
        return value.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return value.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return value.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return value.lastIndexOf(str, fromIndex);
    }

    public CharSequence subSequence(int start, int end) {
        return value.subSequence(start, end);
    }

}
