package helpers;
import java.io.File;

public class FileCreator {

    String filename;
    File file;

    public FileCreator() {}

    public void createFile(String filename) {
        this.filename = filename;
        this.file = new File(filename);
    }

    public Boolean exists() {
        return this.file.exists();
    }
}
