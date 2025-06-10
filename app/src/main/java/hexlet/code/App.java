package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

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

        try {
            Map<String, Object> mapFile1 = Parser.parseFile(path1);
            Map<String, Object> mapFile2 = Parser.parseFile(path2);
            System.out.println(Differ.generate(mapFile1, mapFile2, format));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
