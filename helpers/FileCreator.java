package helpers;
import java.io.File;

public class FileCreator {

    public FileCreator() {}

    public void createFile(String filename) {
        File file = new File(filename);
        file.deleteOnExit();
    }
}
