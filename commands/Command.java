package commands;
import java.io.IOException;

public interface Command {
    void run(String args);
    void runWithRedirectedOutput(String input, String output) throws IOException;
    void runWithRedirectedOutputHandleError(String input, String output, String outputError) throws IOException;
}