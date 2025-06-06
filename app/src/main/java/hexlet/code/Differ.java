package hexlet.code;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Sets;

import java.util.*;

public class Differ {
    static String stringGenerator(Map<String, Object> data1,
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

        // 1
        // key login
        // type - deleted
        // value - 123
       return Formatter.formatter(list, format);
    }
}
