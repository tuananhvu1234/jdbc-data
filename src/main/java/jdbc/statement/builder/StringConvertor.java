package jdbc.statement.builder;

/**
 * Áp dụng cho mysql 8.0 và java 8
 * <p>
 * Thiết lập định dạng cho các từ khóa trong câu lệnh mysql hoặc các biến trong
 * câu lệnh.
 * <p>
 * class này chỉ sử kiểu dữ liệu string cho tất cả method và variable
 *
 * @author DELL
 */
public final class StringConvertor {

    private static final String[] KEY_FORMAT = new String[]{"{", "}"};
    private static final String KEY_TO_VALUE_OPERATOR = "->";

    private static boolean checkKeyFormat(String str) {
        if (str.contains(KEY_FORMAT[0]) && str.contains(KEY_FORMAT[1])
                && !str.contains(" ")
                && str.length() > 2) {
            return true;
        }
        return false;
    }

    private static boolean checkKeyFromValue(String str) {
        if (str.contains(KEY_TO_VALUE_OPERATOR)) {
            return checkKeyFormat(str.split(KEY_TO_VALUE_OPERATOR)[0]);
        }
        return false;
    }

    private static boolean checkValueFormat(String str) {
        String regex
                = "[" + KEY_FORMAT[0] + "]"
                + "[a-zA-Z0-9]+"
                + "[" + KEY_FORMAT[1] + "]"
                + KEY_TO_VALUE_OPERATOR
                + "[\\w\\W]+";
        if (str.matches(regex)) {
            return checkKeyFromValue(str);
        }
        return false;
    }

    private static int[] findIndex(String originStr, String key) {
        if (originStr.contains(key)) {
            return new int[]{originStr.indexOf(key), originStr.indexOf(key) + key.length()};
        }
        return new int[]{-1, -1};
    }

    private static int[] findKeyIndex(String originStr, String key) {
        if (checkKeyFormat(key)) {
            return findIndex(originStr, key);
        }
        return new int[]{-1, -1};
    }

    private static String convertKeyToValue(String stringValue, String key, String value) {
        StringBuilder stringBuilder = new StringBuilder(stringValue);
        int[] index = findKeyIndex(stringValue, key);
        if (index[0] > -1 && index[1] > -1) {
            stringBuilder.replace(index[0], index[1], value);
        }
        return stringBuilder.toString();
    }

    public static String converter(String origin, String[] values) {
        for (String value : values) {
            if (checkValueFormat(value) == false) {
                throw new UnsupportedOperationException(value
                        + " is not in a supported format. \n"
                        + "Format is key" + KEY_TO_VALUE_OPERATOR + "value. "
                        + "key = " + KEY_FORMAT[0]
                        + "string_without_spaces"
                        + KEY_FORMAT[1]
                        + ". value = string.");
            }
        }
        String[] arrKeyValue;
        for (String value : values) {
            arrKeyValue = value.split(KEY_TO_VALUE_OPERATOR);
            origin = convertKeyToValue(origin, arrKeyValue[0], arrKeyValue[1]);
        }
        return origin;
    }

    public static String removeDoubleSpaces(String str) {
        String doubleSpaces = "  ";
        StringBuilder stringBuilder = new StringBuilder(str);
        int[] index;
        while (stringBuilder.toString().contains(doubleSpaces)) {
            index = findIndex(stringBuilder.toString(), doubleSpaces);
            stringBuilder.replace(index[0], index[1], " ");
        }
        if (stringBuilder.toString().endsWith(" ")) {
            stringBuilder.replace(stringBuilder.toString().length() - 1, stringBuilder.toString().length(), "");
        }
        return stringBuilder.toString();
    }

    public static String removeKeys(String str, String[] keys) {
        StringBuilder strBuilder = new StringBuilder(str);
        int[] index;
        for (String key : keys) {
            index = findIndex(strBuilder.toString(), key);
            strBuilder.replace(index[0], index[1], "");
        }
        return strBuilder.toString();
    }
}
