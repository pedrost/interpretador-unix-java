package helpers;

import java.io.IOException;
import java.io.FileWriter;

public class FileWrite {

    private String fileName;

    public FileWrite() {}

    public FileWrite write(String fileName, String input) throws IOException {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
