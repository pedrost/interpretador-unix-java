package commands;
import core.Dir;
import java.io.*;
import java.util.Arrays;

public class Ls implements Command {
    private int counter;
    File location;
    String[] filesAndDirs;

    public Ls(Dir dir) {
        counter = 0;
        location = new File(dir.getDir());
        filesAndDirs = location.list();
    }

    public void run(String args) {
        while(counter < filesAndDirs.length) {
            System.out.println(filesAndDirs[counter]);
            counter++;
        }
    }

    @Override
    public void runWithRedirectedOutput(String input, String output) throws IOException {

    }
}
