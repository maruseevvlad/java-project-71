package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> file1, Map<String, Object> file2) {

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(file1.keySet());
        allKeys.addAll(file2.keySet());

        StringBuilder result = new StringBuilder("{\n");
        for (String key : allKeys) {
            Object value1 = file1.get(key);
            Object value2 = file2.get(key);

            if (!file1.containsKey(key)) {
                appendLine(result, "+", key, value2);
            } else if (!file2.containsKey(key)) {
                appendLine(result, "-", key, value1);
            } else if (!Objects.equals(value1, value2)) {
                appendLine(result, "-", key, value1);
                appendLine(result, "+", key, value2);
            } else {
                appendLine(result, " ", key, value1);
            }
        }
        return result.append("}").toString();
    }

    private static void appendLine(StringBuilder sb, String prefix, String key, Object value) {
        String formattedValue = (value instanceof String) ? "\"" + value + "\"" : value.toString();
        sb.append("  ").append(prefix).append(" ").append(key).append(": ").append(formattedValue).append("\n");
    }

}
