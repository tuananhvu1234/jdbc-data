/*
 *
 */
package com.noproject.jdbc.sql.data;

import com.noproject.jdbc.helper.StringEditor;

/**
 *
 * @author No
 */
public class ResultJson {

    private static class JsonFormat {

        private static final String TAB = "  ";
        private static final String JSON_OBJECT
                = "{AddObjectField}";

        private String newLine(int time) {
            String result = "";
            for (int i = 1; i <= time; i++) {
                result += TAB;
            }
            return "\n" + result;
        }
    }

    private StringEditor editor;
    private int numberOfElements = 0;
    private boolean isArrayType = false;

    public ResultJson() {
        editor = new StringEditor("EmptyJson");
    }

    public ResultJson(String str) {
        editor = new StringEditor(str);
    }

    private boolean isEmptyJson() {
        return (editor.equals("EmptyJson") == true);
    }

    private void addElement(String element) {
        if (isEmptyJson()) {
            editor.convertToKey("EmptyJson", "AddElement");
            editor.convertKeyValue("AddElement",
                    element + "AddElement");
        }
        if (editor.containsKey("AddElement") == false
                && editor.contains("AddElement") == true) {
            editor.convertToKey("AddElement", "AddElement");
        }
        if (numberOfElements > 1) {
            editor.convertKeyValue("AddElement",
                    ", " + element + "AddElement");
        }
    }

    public void add(String key) {
        numberOfElements++;
        if (key == null && numberOfElements > 1) {
            isArrayType = true;
        }
        addElement(JsonFormat.JSON_OBJECT);
    }

    private void addField(String object, String key, Object value) {
        if (object == null) {
            if (editor.contains("AddObjectField") == true
                    && editor.containsKey("AddObjectField") == false) {
                editor.convertToKey("AddElement");
            }
        }
    }

    public void addFieldNumber(String forObject, String key, int value) {
    }

    public void addFieldNumber(String forObject, String key, double value) {
    }

    public void addFieldNumber(String forObject, String key, float value) {
    }

    public void addFieldNumber(String forObject, String key, Integer value) {
    }

    public void addFieldNumber(String forObject, String key, Double value) {
    }

    public void addFieldNumber(String forObject, String key, Float value) {
    }

    public void addFieldString(String forObject, String key, String value) {
    }

    private String clean() {
        if (editor.containsKey("AddElement") == true) {
//            editor.removeKey("AddElement");
        } else {
//            editor.remove("AddElement");
        }
        return editor.toString();
    }

    public String getJsonString() {
        if (isEmptyJson()) {
            return "{}";
        }
        if (isArrayType == true) {
            return "[" + clean() + "]";
        } else {
            return "{" + clean() + "}";
        }
    }

    @Override
    public String toString() {
        System.out.println("json with format view");
        return clean();
    }

}
