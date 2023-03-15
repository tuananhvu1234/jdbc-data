package com.noproject.jdbc.helper;

/**
 *
 * @author DELL
 */
public final class StringConvertor {

    private static final String[] KEY_FORMAT = new String[]{"@", "{", "}"};
    private static final String KEY_TO_VALUE_OPERATOR = "->";
    private static final String EMPTY_STRING = "";

    /**
     *
     * @param str
     * @return
     */
    private static String getKeyName(String str) {
        String result;
        if (str == null || str.isEmpty() == true) {
            return EMPTY_STRING;
        }
        result = String.join(EMPTY_STRING, str.split(KEY_FORMAT[0]));
        result = String.join(EMPTY_STRING, result.split("\\" + KEY_FORMAT[1]));
        result = String.join(EMPTY_STRING, result.split("\\" + KEY_FORMAT[2]));
        if (result.contains(" ") || result.isEmpty() == true) {
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
        if (str == null || str.isEmpty() == true) {
            return null;
        }
        StringBuilder result = new StringBuilder(str);
        if (isFullFormKey(result.toString()) == true) {
            return result.toString();
        }
        if (isFirstFormKey(result.toString()) == true) {
            result.insert(1, KEY_FORMAT[1]);
            result.append(KEY_FORMAT[2]);
            return result.toString();
        }
        if (isSecondFormKey(result.toString()) == true) {
            result.insert(0, KEY_FORMAT[0]);
            return result.toString();
        }
        return null;
    }

    private static int[] getIndexOfAny(String origin, String str) {
        if ((origin == null || origin.isEmpty() == true)
                || (str == null || str.isEmpty() == true)) {
            return new int[]{-1, -1};
        }
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

    public static String convertOneString(String origin, String key, String value) {
        StringBuilder stringBuilder = new StringBuilder(origin);
        int[] index = getIndexOfAny(origin, key);
        if (index[0] > -1 && index[1] > -1) {
            stringBuilder.replace(index[0], index[1], value);
        }
        return stringBuilder.toString();
    }

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

    /**
     *
     *
     * @param origin Chuỗi cần thay đổi.
     * @param keysArrowValues Một mảng gồm các chuỗi chứa cả key và value ngăn
     * cách nhau bằng chuỗi mũi tên(->).
     * @return
     */
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

    public static String convertEveryString(String origin, String from, String to) {
        while (origin.contains(from) == true) {
            origin = convertOneString(origin, from, to);
        }
        return origin;
    }

}
