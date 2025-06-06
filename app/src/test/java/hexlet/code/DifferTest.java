package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    void testDifferJson() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mapFile1 = new HashMap<>();
        Map<String, Object> mapFile2 = new HashMap<>();
        try {
            mapFile1 = mapper.readValue(new File(String.valueOf("src/test/resources/file1.json")), Map.class);
            mapFile2 = mapper.readValue(new File(String.valueOf("src/test/resources/file2.json")), Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        String actual = Differ.stringGenerator(mapFile1, mapFile2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlYml() {
        Path path1 = Paths.get("src/test/resources/file1.yaml");
        Path path2 = Paths.get("src/test/resources/file2.yml");

        Map<String, Object> mapFile1 = null;
        Map<String, Object> mapFile2 = null;
        try {
            mapFile1 = Parser.parseFile(path1);
            mapFile2 = Parser.parseFile(path2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        String actual = Differ.stringGenerator(mapFile1, mapFile2, "stylish");
        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlJsonPlain() {
        Path path1 = Paths.get("src/test/resources/file1.yaml");
        Path path2 = Paths.get("src/test/resources/file2.json");

        Map<String, Object> mapFile1 = null;
        Map<String, Object> mapFile2 = null;
        try {
            mapFile1 = Parser.parseFile(path1);
            mapFile2 = Parser.parseFile(path2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String expected =
                   """
                   Property 'chars2' was updated. From [complex value] to false
                   Property 'checked' was updated. From false to true
                   Property 'default' was updated. From null to [complex value]
                   Property 'id' was updated. From 45 to null
                   Property 'key1' was removed
                   Property 'key2' was added with value: 'value2'
                   Property 'numbers2' was updated. From [complex value] to [complex value]
                   Property 'numbers3' was removed
                   Property 'numbers4' was added with value: [complex value]
                   Property 'obj1' was added with value: [complex value]
                   Property 'setting1' was updated. From 'Some value' to 'Another value'
                   Property 'setting2' was updated. From 200 to 300
                   Property 'setting3' was updated. From true to 'none' """;

        String actual = Differ.stringGenerator(mapFile1, mapFile2, "plain");
        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlJsonJson() {
        Path path1 = Paths.get("src/test/resources/file1.yaml");
        Path path2 = Paths.get("src/test/resources/file2.json");

        Map<String, Object> mapFile1 = null;
        Map<String, Object> mapFile2 = null;
        try {
            mapFile1 = Parser.parseFile(path1);
            mapFile2 = Parser.parseFile(path2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String expected =
                """
                [{"name":"chars1","operation":"unchanged","value":["a","b","c"]},{"name":"chars2","operation":"changed","value1":["d","e","f"],"value2":false},{"name":"checked","operation":"changed","value1":false,"value2":true},{"name":"default","operation":"changed","value1":null,"value2":["value1","value2"]},{"name":"id","operation":"changed","value1":45,"value2":null},{"name":"key1","operation":"deleted","value":"value1"},{"name":"key2","operation":"added","value":"value2"},{"name":"numbers1","operation":"unchanged","value":[1,2,3,4]},{"name":"numbers2","operation":"changed","value1":[2,3,4,5],"value2":[22,33,44,55]},{"name":"numbers3","operation":"deleted","value":[3,4,5]},{"name":"numbers4","operation":"added","value":[4,5,6]},{"name":"obj1","operation":"added","value":{"nestedKey":"value","isNested":true}},{"name":"setting1","operation":"changed","value1":"Some value","value2":"Another value"},{"name":"setting2","operation":"changed","value1":200,"value2":300},{"name":"setting3","operation":"changed","value1":true,"value2":"none"}]""";

        String actual = Differ.stringGenerator(mapFile1, mapFile2, "json");
        assertEquals(expected, actual);
    }
}
