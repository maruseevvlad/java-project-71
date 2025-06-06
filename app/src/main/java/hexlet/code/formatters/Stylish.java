package hexlet.code.formatters;

import java.util.*;

public class Stylish {

    public static String generate(List<Map<String, Object>> diff) {

        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> keyMap : diff) {

            if (keyMap.get("type").equals("added")) {
                appendLine(result, "+", keyMap.get("key").toString(), keyMap.get("value"));
            } else if (keyMap.get("type").equals("deleted")) {
                appendLine(result, "-", keyMap.get("key").toString(), keyMap.get("value"));
            } else if (keyMap.get("type").equals("changed")) {
                appendLine(result, "-", keyMap.get("key").toString(), keyMap.get("value1"));
                appendLine(result, "+", keyMap.get("key").toString(), keyMap.get("value2"));
            } else {
                appendLine(result, " ", keyMap.get("key").toString(), keyMap.get("value"));
            }
        }
        return result.append("}").toString();
    }

    private static void appendLine(StringBuilder sb, String prefix, String key, Object value) {
        String formattedValue;
        if (value == null) {
            formattedValue = "null";
        } else {
            formattedValue = value.toString();
        }
        sb.append("  ")
                .append(prefix)
                .append(" ")
                .append(key)
                .append(": ")
                .append(formattedValue)
                .append("\n");
    }

}

