package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Path;
import java.util.Map;
import java.io.IOException;

public final class Parser {

    private Parser() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, Object> parseFile(String filePath) throws Exception {
        return parseFile(Path.of(filePath));
    }

    public static Map<String, Object> parseFile(Path filePath) throws Exception {
        String fileName = filePath.toString();
        ObjectMapper mapper;

        if (fileName.endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (fileName.endsWith(".yaml") || fileName.endsWith(".yml")) {
            mapper = new ObjectMapper(new YAMLFactory());
        } else {
            throw new IllegalArgumentException("Неподдерживаемый формат файла" + fileName);
        }

        try {
            return mapper.readValue(filePath.toFile(), Map.class);
        } catch (IOException e) {
            throw new ParsingException("Ошибка чтения файла " + fileName, e);
        }
    }
}
