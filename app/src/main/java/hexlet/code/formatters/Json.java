package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Json {
    public static String generate(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder("[");
        boolean firstEntry = true;

        for (Map<String, Object> keyMap : diff) {
            if (firstEntry) {
                firstEntry = false;
            } else {
                result.append(",");
            }

            String key = (String) keyMap.get("key");
            String type = (String) keyMap.get("type");

            result.append("{\"name\":").append(toJsonString(key));
            result.append(",\"operation\":").append(toJsonString(type));

            switch (type) {
                case "unchanged":
                    result.append(",\"value\":").append(toJsonString(keyMap.get("value")));
                    break;
                case "changed":
                    result.append(",\"value1\":").append(toJsonString(keyMap.get("value1")));
                    result.append(",\"value2\":").append(toJsonString(keyMap.get("value2")));
                    break;
                case "added":
                    result.append(",\"value\":").append(toJsonString(keyMap.get("value")));
                    break;
                case "deleted":
                    result.append(",\"value\":").append(toJsonString(keyMap.get("value")));
                    break;
                default:
                    throw new RuntimeException("Unknown type: " + type);
            }

            result.append("}");
        }

        result.append("]");
        return result.toString();
    }

    private static String toJsonString(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "\"" + escapeString((String) value) + "\"";
        }
        if (value instanceof Boolean || value instanceof Number) {
            return value.toString();
        }
        if (value instanceof Collection) {
            Collection<?> collection = (Collection<?>) value;
            StringBuilder sb = new StringBuilder("[");
            boolean first = true;
            for (Object element : collection) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append(toJsonString(element));
            }
            sb.append("]");
            return sb.toString();
        }
        if (value instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) value;
            StringBuilder sb = new StringBuilder("{");
            boolean first = true;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                String entryKey = entry.getKey().toString();
                sb.append("\"").append(escapeString(entryKey)).append("\"");
                sb.append(":");
                sb.append(toJsonString(entry.getValue()));
            }
            sb.append("}");
            return sb.toString();
        }
        return "\"" + escapeString(value.toString()) + "\"";
    }

    private static String escapeString(String input) {
        StringBuilder escaped = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '"' -> escaped.append("\\\"");
                case '\\' -> escaped.append("\\\\");
                case '\b' -> escaped.append("\\b");
                case '\f' -> escaped.append("\\f");
                case '\n' -> escaped.append("\\n");
                case '\r' -> escaped.append("\\r");
                case '\t' -> escaped.append("\\t");
                default -> escaped.append(c);
            }
        }
        return escaped.toString();
    }
}