package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static final String TEST_RESOURCES_DIR = "src/test/resources/";

    private static Map<String, Object> file1Json;
    private static Map<String, Object> file2Json;
    private static Map<String, Object> file1Yaml;
    private static Map<String, Object> file2Yaml;

    private static Path filePath1Json;
    private static Path filePath2Json;
    private static Path filePath1Yaml;
    private static Path filePath2Yaml;

    @BeforeAll
    static void beforeAll() throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

        filePath1Json = getTestPath("file1.json");
        filePath2Json = getTestPath("file2.json");
        filePath1Yaml = getTestPath("file1.yaml");
        filePath2Yaml = getTestPath("file2.yml");

        file1Json = jsonMapper.readValue(filePath1Json.toFile(), new TypeReference<>() {

        });
        file2Json = jsonMapper.readValue(filePath2Json.toFile(), new TypeReference<>() {

        });
        file1Yaml = yamlMapper.readValue(filePath1Yaml.toFile(), new TypeReference<>() {

        });
        file2Yaml = yamlMapper.readValue(filePath2Yaml.toFile(), new TypeReference<>() {

        });
    }

    private static Path getTestPath(String filename) {
        return Paths.get(TEST_RESOURCES_DIR, filename).toAbsolutePath().normalize();
    }

    @Test
    void testDifferJson() throws Exception {
        String expected = normalizeLineEndings(readFile("expected_stylish.txt"));
        String actual = normalizeLineEndings(Differ.generate(file1Json, file2Json, "stylish"));
        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlYml() throws Exception {
        String expected = normalizeLineEndings(readFile("expected_stylish.txt"));
        String actual = normalizeLineEndings(Differ.generate(file1Yaml, file2Yaml, "stylish"));
        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlJsonPlain() throws Exception {
        String expected = normalizeLineEndings(readFile("expected_plain.txt"));
        String actual = normalizeLineEndings(Differ.generate(file1Yaml, file2Json, "plain"));
        assertEquals(expected, actual);
    }

    @Test
    void testDifferYamlJsonJson() throws Exception {
        String expected = normalizeLineEndings(readFile("expected_json.json").replaceAll("\\s+", ""));
        String actual = normalizeLineEndings(Differ.generate(file1Yaml, file2Json, "json").replaceAll("\\s+", ""));
        assertEquals(expected, actual);
    }

    private String readFile(String filename) throws Exception {
        Path path = getTestPath(filename);
        return Files.readString(path);
    }

    private String normalizeLineEndings(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }
}
