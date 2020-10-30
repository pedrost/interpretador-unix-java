package helpers;

import java.io.File;
import java.io.IOException;

public class FileCreator {

    public FileCreator() {}

    public void createFile(String filename) throws IOException {
        File file = new File(filename);
        file.deleteOnExit();
    }
}
