package helpers;

import java.io.IOException;
import java.io.FileWriter;

public class FileWrite {

    private String fileName;

    public FileWrite() {}

    public FileWriter write(String fileName, String input) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        try {
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return writer;
    }
}
