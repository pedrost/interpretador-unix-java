package commands;
import java.io.IOException;
import java.lang.ProcessBuilder;

public class Clear implements Command {
    public Clear() { }

    public void run(String args){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
                System.out.flush();
        } catch (InterruptedException | IOException ignored) {}
    }


    @Override
    public void runWithRedirectedOutput(String input, String output) throws IOException { }

    @Override
    public void runWithRedirectedOutputHandleError(String input, String output, String outputError) throws IOException { }
}
