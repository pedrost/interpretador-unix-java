package core;

import java.io.*;

public class Prompt {
    private String currentPrompt;
    private String currentNotFoundCommand;
    private String currentPathEnvironmentVariable;

    public Prompt(String currentPrompt, String pathEnvironmentVariable) {
        this.currentPrompt = currentPrompt;
        this.currentNotFoundCommand = "";
        this.currentPathEnvironmentVariable = pathEnvironmentVariable;
    }

    public void setPrompt(String prompt) {
        currentPrompt = prompt;
    }

    public void setcurrentPathEnvironmentVariable(String var) {
        currentPathEnvironmentVariable = var;
    }

    public void setCurrentNotFoundCommand(String command) {
        currentNotFoundCommand = command;
    }

    public String getCurrentPathEnvironmentVariable() {
        return this.currentPathEnvironmentVariable;
    }

    public String getCurrentNotFoundCommand() {
        return this.currentNotFoundCommand;
    }

    public String getPrompt() {
        return this.currentPrompt;
    }
}