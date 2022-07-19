import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ShuffleFasta {
    public static final String file = "/users/claricepark/data/blindptm/shuffled.fasta";

    public static void main(String[] args) {
        List<String> list = null;
        try {
            list = readFromFile(file);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);

    }

    public static List<String> readFromFile(String fileName) throws IOException {
        Stream<String> stream = Files.lines(Paths.get(fileName));
        return stream.collect(Collectors.toList());
    }
}