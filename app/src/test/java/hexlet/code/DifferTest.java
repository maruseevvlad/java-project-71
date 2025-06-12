package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.nio.file.Files.readString;

public class DifferTest {

    private static Path filePath1Json;
    private static Path filePath2Json;
    private static Path filePath1Yaml;
    private static Path filePath2Yaml;

    private static Path expectedStylish;
    private static Path expectedPlain;
    private static Path expectedJson;

    @BeforeAll
    static void beforeAll() {
        filePath1Json = getTestPath("file1.json");
        filePath2Json = getTestPath("file2.json");
        filePath1Yaml = getTestPath("file1.yaml");
        filePath2Yaml = getTestPath("file2.yml");

        expectedStylish = getTestPath("expected_stylish.txt");
        expectedPlain = getTestPath("expected_plain.txt");
        expectedJson = getTestPath("expected_json.json");
    }

    private static Path getTestPath(String filename) {
        return Paths.get("src", "test", "resources", filename).toAbsolutePath().normalize();
    }

    private String normalizeLineEndings(String input) {
        return input.replaceAll("\r\n", "\n").replaceAll("\r", "\n");
    }

    @Test
    void testJsonDefaultFormat() throws Exception {
        String expected = normalizeLineEndings(readString(expectedStylish));
        String actual = normalizeLineEndings(Differ.generate(filePath1Json.toString(), filePath2Json.toString()));
        assertEquals(expected, actual);
    }

    @Test
    void testYamlStylishFormat() throws Exception {
        String expected = normalizeLineEndings(readString(expectedStylish));
        String actual = normalizeLineEndings(Differ
                .generate(filePath1Yaml.toString(), filePath2Yaml.toString(), "stylish"));
        assertEquals(expected, actual);
    }

    @Test
    void testMixedPlainFormat() throws Exception {
        String expected = normalizeLineEndings(readString(expectedPlain));
        String actual = normalizeLineEndings(Differ
                .generate(filePath1Yaml.toString(), filePath2Json.toString(), "plain"));
        assertEquals(expected, actual);
    }

    @Test
    void testMixedJsonFormat() throws Exception {
        String expected = normalizeLineEndings(readString(expectedJson)).replaceAll("\\s+", "");
        String actual = normalizeLineEndings(Differ
                .generate(filePath1Json.toString(), filePath2Yaml.toString(), "json")).replaceAll("\\s+", "");
        assertEquals(expected, actual);
    }
}
