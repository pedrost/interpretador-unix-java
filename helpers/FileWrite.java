package helpers;
import java.io.IOException;
import java.io.FileWriter;

public class FileWrite {

    public FileWrite() {}

    public void write(String fileName, String input, String errorOutput) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        FileWriter writerErrorOutput = new FileWriter(errorOutput);

        try {
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            writerErrorOutput.write(e.toString());
        }
    }
}
