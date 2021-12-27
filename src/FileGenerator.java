import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileGenerator {
    public static void generateRandomFile(File file, long length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        // Generate Random Alphanumeric String With Java 8
        // https://www.baeldung.com/java-random-string#java8-alphanumeric
        IntStream intStream = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length);

        try (FileWriter fileWriter = new FileWriter(file);
             PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter))) {

            intStream.forEach(value -> printWriter.print((char) value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String outDir = "files";
        String inFile = "files.txt";

        Path baseDir = Path.of(outDir);
        try (Stream<String> lines = Files.lines(Path.of(inFile))) {
            lines.forEach(line -> {
                int firstSpace = line.indexOf(' ');
                long length = Long.parseLong(line.substring(0, firstSpace));
                String file = line.substring(firstSpace + 1).trim();
                Path path = baseDir.resolve(file);
                try {
                    Files.createDirectories(path.getParent());
                    generateRandomFile(path.toFile(), length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
