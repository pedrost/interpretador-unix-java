package commands;
import java.io.*;

import core.Dir;

public class Cd implements Command {
    private Dir dir;

	public Cd(Dir dir) {
        this.dir = dir;
	}

	public void run(String destination) {
	
		if (destination.equals("..")) {
			File file = new File(dir.getDir());
			String fileString;
			fileString = file.getParent();
			dir.setDir(fileString, false);
		}

		else if (destination.equals(".")) {
			
		}
		
		else {
			dir.setDir(System.getProperty("file.separator") + destination, true);
		}
	
	}

	@Override
	public void runWithRedirectedOutput(String input, String output) throws IOException { }

	@Override
	public void runWithRedirectedOutputHandleError(String input, String output, String outputError) throws IOException { }
}