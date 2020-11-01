package commands;
import core.Dir;
import helpers.FileCreator;
import helpers.FileWrite;

import java.io.*;
import java.util.Arrays;

public class Ls implements Command {
    private int counter;
    private Dir dir;
    File location;
    String[] filesAndDirs;

    public Ls(Dir dir) {
        counter = 0;
        this.dir = dir;
        location = new File(dir.getDir());
        filesAndDirs = location.list();
    }

    public void run(String args) {
        counter = 0;
        location = new File(dir.getDir());
        filesAndDirs = location.list();
        System.out.println(".");
        System.out.println("..");
        
        while(counter < filesAndDirs.length) {
            System.out.println(filesAndDirs[counter]);
            counter++;
        }
    }

    @Override
    public void runWithRedirectedOutput(String input, String output) throws IOException {
        FileCreator file = new FileCreator();
        FileWrite writer = new FileWrite();
        file.createFile(output);

        writer.write(output, Arrays.toString(filesAndDirs),"error.txt");
    }

    @Override
    public void runWithRedirectedOutputHandleError(String input, String output, String outputError) throws IOException {
        FileCreator file = new FileCreator();
        FileWrite writer = new FileWrite();
        file.createFile(output);

        writer.write(output, Arrays.toString(filesAndDirs),outputError);
    }

    @Override
    public void runWithRedirectedInput(String inputFile) { }

    @Override
    public void runWithInputAndOutputRedirect(String commands, String input, String output) { }

}
