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
			String[] foldersToLookUp = this.prompt.getCurrentPathEnvironmentVariable().split(";");
			for(int i = 0; i<= foldersToLookUp.length - 1; i++) {
				// This will look something like c:/usr/bin/ls
				String searchedFilePath = foldersToLookUp[i] + "/" + prompt.getCurrentNotFoundCommand();
				File searchedFile = new File(searchedFilePath);
				if(searchedFile.exists()) {
					Runtime rt = Runtime.getRuntime();
					try {
						Process ps = rt.exec(searchedFilePath);
						BufferedReader stdInput = new BufferedReader(new InputStreamReader(ps.getInputStream()));
						BufferedReader stdError = new BufferedReader(new InputStreamReader(ps.getErrorStream()));

						String s = null;
						while ((s = stdInput.readLine()) != null) {
							System.out.println(s);
						}
						while ((s = stdError.readLine()) != null) {
							System.out.println(s);
						}

					} catch(Exception ex) {
						System.out.printf("failed to execute command %s\n", prompt.getCurrentNotFoundCommand());
					}
					return;
				}
			}
		} else {
			String[] foldersToLookUp = this.prompt.getCurrentPathEnvironmentVariable().split(":");
			for(int i = 0; i<= foldersToLookUp.length - 1; i++) {
				// This will look something like c:/usr/bin/ls
				String searchedFilePath = foldersToLookUp[i] + "/" + prompt.getCurrentNotFoundCommand();
				File searchedFile = new File(searchedFilePath);
				if(searchedFile.exists()) {
					Runtime rt = Runtime.getRuntime();
					try {
						Process ps = rt.exec(searchedFilePath);
						BufferedReader stdInput = new BufferedReader(new InputStreamReader(ps.getInputStream()));
						BufferedReader stdError = new BufferedReader(new InputStreamReader(ps.getErrorStream()));

						String s = null;
						while ((s = stdInput.readLine()) != null) {
							System.out.println(s);
						}
						while ((s = stdError.readLine()) != null) {
							System.out.println(s);
						}
					} catch(Exception ex) {
						System.out.printf("failed to execute command %s\n", prompt.getCurrentNotFoundCommand());
					}
					return;
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