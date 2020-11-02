package contexts;

import commands.History;
import core.Dir;
import core.Prompt;
import helpers.Runner;

import java.io.IOException;

public class BackgroundExecution extends Thread {
    private final String commandInput;
    private static final Dir dir = new Dir(System.getProperty("user.dir"));
    private static final Prompt prompt = new Prompt(dir.getDir() + " $", System.getenv("PATH"));

    public BackgroundExecution(String command) {
        this.commandInput = command;
    }

    public void run(){
        String ps1 = prompt.getPrompt();
        if(commandInput.contains("&&")) {
            String[] splittedFullCommand = commandInput.split("&& ");

            for(String command : splittedFullCommand) {
                try {
                    Runner.splitAndRun(command, "", "", true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(commandInput.contains(">")) {
            if(commandInput.contains(">>")) {
                String[] splittedFullCommand = commandInput.split("2> ");
                String[] splittedFirstCommand = splittedFullCommand[0].split("> ");
                String currentCommmand = splittedFirstCommand[0];
                String outputRedirectionFileName = splittedFirstCommand[1];
                String outputErrorRedirectFileName = splittedFullCommand[1];
                try {
                    Runner.splitAndRunHandleError(currentCommmand, outputRedirectionFileName, outputErrorRedirectFileName, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                String[] splittedFullCommand = commandInput.split("> ");
                String currentCommmand = splittedFullCommand[0];
                String outputRedirectionFileName = splittedFullCommand[1];
                try {
                    Runner.splitAndRun(currentCommmand, "output", outputRedirectionFileName, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        if(commandInput.contains("<")) {
            String[] splittedFullCommand = commandInput.split("< ");
            String currentCommmand = splittedFullCommand[0];
            String inputRedirectionFileName = splittedFullCommand[1];
            try {
                Runner.splitAndRun(currentCommmand, "input", inputRedirectionFileName, true);
            } catch (IOException e) {
                System.out.println("File not found.");
            }
        }

        if(commandInput.contains("<") && commandInput.contains(">")) {
            String[] splittedFullCommand = commandInput.split("< ");
            String currentCommmand;
            String outputRedirectionFileName = "";
            String inputRedirectionFileName = "";
            String[] splittedOut;

            if(splittedFullCommand[0].contains(">")) {
                splittedOut = splittedFullCommand[0].split("> ");
                currentCommmand = splittedOut[0];
                outputRedirectionFileName = splittedOut[1];
                inputRedirectionFileName = splittedFullCommand[1];
            } else {
                splittedOut = splittedFullCommand[1].split("> ");
                currentCommmand = splittedFullCommand[0];
                inputRedirectionFileName = splittedOut[0];
                outputRedirectionFileName = splittedOut[1];
            }

            try {
                Runner.splitAndRunWithInputAndOutputRedirect(currentCommmand, inputRedirectionFileName, outputRedirectionFileName, true);
            } catch (IOException e) {
                System.out.println("File not found.");
            }

        }
        if(commandInput.contains("export $MYPS1") || commandInput.contains("export $PS1")) {
            String[] splittedFullCommand = commandInput.split(" ");
            if (splittedFullCommand.length > 2) {
                StringBuilder newPS1 = new StringBuilder();
                for (int i = 2; i < splittedFullCommand.length; i++) {
                    newPS1.append(splittedFullCommand[i]);
                    newPS1.append(" ");
                }
                ps1 = newPS1.toString();
            } else ps1 = dir.getDir() + " $";
        }
        else {
            try {
                Runner.splitAndRun(commandInput, "", "", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Thread.currentThread().interrupt();
    }
}
