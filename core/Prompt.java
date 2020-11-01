package core;
import java.io.*;

public class Prompt {
	private String currentPrompt;
    private String currentNotFoundCommand;
	
	public Prompt(String currentPrompt) {
		this.currentPrompt = currentPrompt;
        this.currentNotFoundCommand = "";
	}

	public void setPrompt(String prompt) {
		currentPrompt = prompt;
	}

    public void setCurrentNotFoundCommand(String command) {
		currentNotFoundCommand = command;
	}

    public String getCurrentNotFoundCommand() {
		return this.currentNotFoundCommand;
	}

	public String getPrompt() {
		return this.currentPrompt;
	}
}