package helpers;

import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;

public class FileCreator {

    private String fileName;

    public FileCreator() {}

    public FileCreator createFile(String filename) throws IOException {
        this.fileName = filename;

        File file = new File(filename);
        file.deleteOnExit();

        return this;
    }

    public void write(String input) throws IOException {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
