package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    void testDiffer() {
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
                  - follow: false
                    host: "hexlet.io"
                  - proxy: "123.234.53.22"
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String actual = Differ.generate(mapFile1, mapFile2);
        assertEquals(expected, actual);
    }
}
