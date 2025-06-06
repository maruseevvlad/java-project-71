package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatter(List<Map<String, Object>> diff, String format) {
         switch (format) {
            case "stylish":
                return Stylish.generate(diff);
            case "plain":
                return Plain.generate(diff);
             case "json":
                 return Json.generate(diff);
            default:
                throw new IllegalStateException("Unexpected value: " + format);
        }
    }
}
