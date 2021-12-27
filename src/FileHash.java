import java.io.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileHash {

    public static String md5sum(String source) throws IOException, NoSuchAlgorithmException {
        // See file copy example using file channel:
        // https://www.amitph.com/java-read-write-large-files-efficiently/#Using_FileChannel
        try (FileChannel inputChannel = new FileInputStream(source).getChannel()) {
            // See MD5 example Using MessageDigest Class:
            // https://www.baeldung.com/java-md5
            MessageDigest md = MessageDigest.getInstance("MD5");
            ByteBuffer buffer = ByteBuffer.allocateDirect(4 * 1024);
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                md.update(buffer);
                buffer.clear();
            }

            // Examples of how convert from byte array to hex string
            // https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
            byte[] digest = md.digest();
            // There are better solutions to this, but this solution does not require any dependencies
            return new BigInteger(1, digest).toString(16);
        }
    }

    public static void main(String[] args) {
        String source = "<path to a file>";
        try {
            String hash = md5sum(source);
            System.out.printf("%s %s\n", hash, source);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
