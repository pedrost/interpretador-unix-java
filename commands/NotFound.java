package commands;

import java.io.*;
import core.Prompt;

public class NotFound implements Command {
	Prompt prompt;

	public NotFound(Prompt prompt) {
		this.prompt = prompt;
	}

    @Override
	public void run(String command) {
		if(System.getProperty("os.name").startsWith("Windows")) {
			String[] foldersToLookUp = System.getenv("PATH").split(";");
			for(int i = 0; i<= foldersToLookUp.length - 1; i++) {
				// This will look something like c:/usr/bin/ls
				String searchedFilePath = foldersToLookUp[i] + "/" + prompt.getCurrentNotFoundCommand();
				File searchedFile = new File(searchedFilePath);
				if(searchedFile.exists()) {
					System.out.println(searchedFilePath);
					//Runtime rt = Runtime.getRuntime();
					//Process ps = rt.exec("path to my executable.exe");
				}
			}
		} else {
			String[] foldersToLookUp = System.getenv("PATH").split(":");
			for(int i = 0; i<= foldersToLookUp.length - 1; i++) {
				// This will look something like c:/usr/bin/ls
				String searchedFilePath = foldersToLookUp[i] + "/" + prompt.getCurrentNotFoundCommand();
				File searchedFile = new File(searchedFilePath);
				if(searchedFile.exists()) {
					System.out.println(searchedFilePath);
					//Runtime rt = Runtime.getRuntime();
					//Process ps = rt.exec("path to my executable.exe");
				}
			}
		}
		System.out.printf("%s command not found\n", prompt.getCurrentNotFoundCommand());
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