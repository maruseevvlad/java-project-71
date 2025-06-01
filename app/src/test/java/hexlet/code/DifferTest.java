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

        String actual = Differ.generate(mapFile1, mapFile2);
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

        String actual = Differ.generate(mapFile1, mapFile2);
        assertEquals(expected, actual);
    }
}
