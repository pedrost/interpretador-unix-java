package commands;

import java.io.IOException;

public interface Command {
	
    public void run(String args);

    public void runWithRedirectedOutput(String input, String output) throws IOException;

}