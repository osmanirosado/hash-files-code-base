import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

public class DirectoryWalker {

    public static void example(String path) {
        // List Files and Subdirectories from a Directory in Java
        // https://www.amitph.com/java-list-files/#Walk_through_Directory_Tree
        try (Stream<Path> stream = Files.walk(Path.of(path))) {
            stream.filter(Files::isRegularFile)
                    .filter(Files::isReadable)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stream<Path> streamOfRegularFiles(String directory) throws IOException {
        Path start = Path.of(directory);
        return Files.walk(start)
                .filter(Files::isRegularFile)
                .filter(Files::isReadable);
    }

    public static void usingForEachWithMethodReference(String directory) {
        try (Stream<Path> stream = streamOfRegularFiles(directory)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void usingForEachWithLambdaExpression(String directory) {
        try (Stream<Path> stream = streamOfRegularFiles(directory)) {
            stream.forEach(file -> System.out.printf("> %s : %s\n", file.getFileName(), file.getParent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void usingIterator(String directory) {
        try (Stream<Path> stream = streamOfRegularFiles(directory)) {
            Iterator<Path> iterator = stream.iterator();
            while (iterator.hasNext()) {
                Path file = iterator.next();
                System.out.println(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String directory = "<path to a directory>";

//        example(directory);
//        usingForEachWithMethodReference(directory);
        usingForEachWithLambdaExpression(directory);
//        usingIterator(directory);
    }
}
