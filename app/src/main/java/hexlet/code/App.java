package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import com.fasterxml.jackson.databind.ObjectMapper;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

class App implements Callable<Integer> {

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String pathToFile1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String pathToFile2;

    @Option(names = { "-f", "--format" },
            paramLabel = "format",
            description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;

    @Override
    public Integer call() throws Exception {
        Path path1 = Paths.get(pathToFile1).toAbsolutePath().normalize();
        Path path2 = Paths.get(pathToFile2).toAbsolutePath().normalize();

        if (!Files.exists(path1) || !Files.exists(path2)) {
            throw new Exception("File does not exist");
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mapFile1 = new HashMap<>();
        Map<String, Object> mapFile2 = new HashMap<>();
        try {
            mapFile1 = mapper.readValue(new File(String.valueOf(path1)), Map.class);
            mapFile2 = mapper.readValue(new File(String.valueOf(path2)), Map.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(Differ.generate(mapFile1, mapFile2));

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
