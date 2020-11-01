package commands;

import helpers.FileCreator;
import helpers.FileWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History implements Command {
    List<String> history = new ArrayList<>();

    public History() {}

    public void pushItem(String command) {
        this.history.add(command);
    }

    @Override
    public void run(String args) {
        this.history.forEach(command -> System.out.println(this.history.indexOf(command)+1 + " " + command));
    }

    @Override
    public void runWithRedirectedOutput(String input, String output) throws IOException {
        FileCreator file = new FileCreator();
        FileWrite writer = new FileWrite();
        file.createFile(output);

        writer.write(output, this.history.toString(),"error.txt");
    }

    @Override
    public void runWithRedirectedOutputHandleError(String input, String output, String outputError) throws IOException {
        FileCreator file = new FileCreator();
        FileWrite writer = new FileWrite();
        file.createFile(output);

        writer.write(output, this.history.toString(),outputError);
    }

    @Override
    public void runWithRedirectedInput(String inputRedirect) { }

    @Override
    public void runWithInputAndOutputRedirect(String command, String input, String output) { }
}
