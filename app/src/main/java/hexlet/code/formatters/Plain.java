package hexlet.code.formatters;

import java.util.*;

public class Plain {
    public static String generate(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();
        for (Map<String, Object> keyMap : diff) {
            String type = (String) keyMap.get("type");
            String key = (String) keyMap.get("key");

            switch (type) {
                case "added":
                    result.append("Property '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(formatValue(keyMap.get("value")))
                            .append("\n");
                    break;
                case "deleted":
                    result.append("Property '")
                            .append(key)
                            .append("' was removed\n");
                    break;
                case "changed":
                    result.append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(formatValue(keyMap.get("value1")))
                            .append(" to ")
                            .append(formatValue(keyMap.get("value2")))
                            .append("\n");
                    break;
                case "unchanged":
                    break;
                default:
                    throw new RuntimeException("Unknown type: " + type);
            }
        }
        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Collection || value instanceof Map) {
            return "[complex value]";
        }
        return value.toString();
    }
}