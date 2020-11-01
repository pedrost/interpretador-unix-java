package commands;

import java.io.*;
import java.util.*;
import java.lang.reflect.Field;
import core.Prompt;

public class Export implements Command {
    Prompt prompt;

	public Export(Prompt prompt) {
        this.prompt = prompt;
	}

    @Override
	public void run(String command) {
        // This will look something like ["MYPS1", "pedro"];
        String[] splitedExport = command.split("=");
        if(splitedExport.length >= 2) {
            String key = splitedExport[0];
            String val = splitedExport[1];
            if(key.equals("MYPS1")) {
                prompt.setPrompt(val);
            }
        } else {
            System.out.printf("%s invalid number of arguments \n");
        }
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