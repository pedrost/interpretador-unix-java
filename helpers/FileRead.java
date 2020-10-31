package helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class FileRead {

    private final String fileName;

    public FileRead(String file) {
        this.fileName = file;
    }

    public String read() {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.fileName));
            String line = br.readLine();

            while (line != null) {
                content.append(line);
                content.append(System.lineSeparator());
                line = br.readLine();
            }
            return content.toString();
        } catch (IOException fileEx) {
            return "FAIL: " + fileEx.getMessage();
        }

    }

    public Boolean exists() {
        FileCreator file = new FileCreator();
        file.createFile(this.fileName);
        return file.exists();
    }

}
