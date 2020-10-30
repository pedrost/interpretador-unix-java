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
	public void runWithRedirectedOutput(String input, String output) throws IOException {

	}
}