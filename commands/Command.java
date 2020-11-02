package commands;
import java.io.IOException;

public interface Command {
    void run(String args, Boolean isBackground);
    void runWithRedirectedOutput(String input, String output, Boolean isBackground) throws IOException;
    void runWithRedirectedOutputHandleError(String input, String output, String outputError, Boolean isBackground) throws IOException;
    void runWithRedirectedInput(String inputRedirect, Boolean isBackground) throws IOException;
    void runWithInputAndOutputRedirect(String command, String input,String output, Boolean isBackground) throws IOException;
}