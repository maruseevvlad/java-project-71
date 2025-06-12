package hexlet.code;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class Differ {

    public static String generate(Map<String, Object> data1,
                                  Map<String, Object> data2) throws Exception {
        return generate(data1, data2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> data1 = Parser.parseFile(filePath1);
        Map<String, Object> data2 = Parser.parseFile(filePath2);
        return generate(data1, data2, format);
    }

    // 🔧 Новый метод — по строкам с путями, формат по умолчанию
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    static String generate(Map<String, Object> data1,
                           Map<String, Object> data2,
                           String format) {
        List<Map<String, Object>> list = new ArrayList<>();
        Set<String> dataKeys = ImmutableSortedSet.copyOf(Sets.union(data1.keySet(), data2.keySet()));


        dataKeys.forEach((key) -> {

            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            Map<String, Object> changes = new HashMap<>();
            changes.put("key", key);
            if (!data1.containsKey(key)) {
                changes.put("type", "added");
                changes.put("value", value2);
            } else if (!data2.containsKey(key)) {
                changes.put("type", "deleted");
                changes.put("value", value1);
            } else if (Objects.equals(value1, value2)) {
                changes.put("type", "unchanged");
                changes.put("value", value1);
            } else {
                changes.put("type", "changed");
                changes.put("value1", value1);
                changes.put("value2", value2);
            }

            list.add(changes);
        });

        return Formatter.formatter(list, format);
    }
}
