import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Poetry{
    private static Poetry instance;
    private Path path;

    private Map<Author, String> description = new HashMap<>();
    private Poetry(Path path, Map<Author, String> description){
        this.path = path;
        this.description = description;
    }

    public static Poetry getInstance(Path path){
        if(instance == null){
            try {
                Stream<String> fileLines = Files.lines(path);
                Map<Author, String> description = fileLines.map(s -> s.split(",")).collect(Collectors.toMap(
                        s -> Author.parseLine(s[0], s[1]),
                        s -> s[2]
                ));
                instance = new Poetry(path, description);
                fileLines.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
    public static List<Author> filterUS(Poetry poetry){
        return poetry.description.entrySet().stream()
                .filter(entry -> entry.getValue().contains("US")) //entry represents each key-value pair (an author and their description)
                .sorted(Comparator.comparing(entry -> entry.getKey().getBirthYear()))
                .map(entry -> entry.getKey()).collect(Collectors.toList());
    }
    public Map<Author, String> getDescription() {
        return description;
    }

}
