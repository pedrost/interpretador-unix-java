package commands;

import java.io.*;

public class NotFound implements Command {
	
	public NotFound() {
	}

    @Override
	public void run(String command) {
        System.out.printf("%s command not found\n", command);
	}


	@Override
	public void runWithRedirectedOutput(String input, String output) { }

	@Override
	public void runWithRedirectedOutputHandleError(String input, String output, String outputError) { }

	@Override
	public void runWithRedirectedInput(String inputFile) { }

	@Override
	public void runWithInputAndOutputRedirect(String commands, String input, String output) { }
}