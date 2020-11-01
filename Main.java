import java.io.IOException;
import java.util.*;
import commands.*;

import core.Dir;

public class Main {
    //    public static Dir dir = new Dir(System.getProperty("user.dir"));
    public static Dir dir = new Dir(System.getProperty("user.dir"));

    public static Cd cdCommand = new Cd(dir);
    private static final Clear clearCommand = new Clear();
    public static Echo echoCommand = new Echo();
    public static Ls lsCommand = new Ls(dir);
    public static NotFound notFoundCommand = new NotFound();
    
    public enum AvailableCommands {
        // cat(echoCommand),
        cd(cdCommand),
        clear(clearCommand),
        echo(echoCommand),
        ls(lsCommand),
        not_found(notFoundCommand);

        private final Command commandClass;
        AvailableCommands(Command commandClass) {
            this.commandClass = commandClass;
        }

        public void run(String args) {
            this.commandClass.run(args);
        }

        public void runWithRedirectedOutput(String input, String output) throws IOException {
            this.commandClass.runWithRedirectedOutput(input, output);
        }

        public void runWithRedirectedOutputHandleError(String input, String output, String outputError) throws IOException {
            this.commandClass.runWithRedirectedOutputHandleError(input, output, outputError);
        }

        public void runWithRedirectedInput(String inputRedirect) throws IOException {
            this.commandClass.runWithRedirectedInput(inputRedirect);
        }

        public void runWithInputAndOutputRedirect(String command, String input, String output) throws IOException {
            this.commandClass.runWithInputAndOutputRedirect(command, input, output);
        }

    }

    private static void splitAndRun(String command, String redirectType, String redirectFile) throws IOException {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands searchedCommand = search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        if (redirectType.equals("output")) {
            searchedCommand.runWithRedirectedOutput(commandArgs.toString(), redirectFile);
        } if (redirectType.equals("input")) {
            searchedCommand.runWithRedirectedInput(redirectFile);
        }
        else {
            searchedCommand.run(commandArgs.toString());
        }
    }

    private static void splitAndRunHandleError(String command, String output, String outputError) throws IOException {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands searchedCommand = search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        searchedCommand.runWithRedirectedOutputHandleError(commandArgs.toString(), output, outputError);
    }

    private static void splitAndRunWithInputAndOutputRedirect(String command, String input, String output) throws IOException {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands searchedCommand = search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        searchedCommand.runWithInputAndOutputRedirect(commandArgs.toString(), input, output);
    }


    public static void main(String[] args) throws IOException {
        String ps1 = dir.getDir() + " $";

        while(true){
            System.out.printf("%s> ", ps1);
            Scanner input = new Scanner(System.in);
            String commandInput = input.nextLine();

            if(commandInput.equals("exit")) {
                System.out.println("Bye !");
                System.exit(1);
            }
            if(commandInput.contains("&&") || commandInput.contains("|")) {
                String[] splittedFullCommand = new String[0];

                if (commandInput.contains("&&")) splittedFullCommand = commandInput.split("&& ");
                if (commandInput.contains("|")) splittedFullCommand = commandInput.split("| ");

                for(String command : splittedFullCommand) {
                    splitAndRun(command, "", "");
                }
                continue;
            }
            if(commandInput.contains(">")) {
                if(commandInput.contains(">>")) {
                    String[] splittedFullCommand = commandInput.split("2> ");
                    String[] splittedFirstCommand = splittedFullCommand[0].split("> ");
                    String currentCommmand = splittedFirstCommand[0];
                    String outputRedirectionFileName = splittedFirstCommand[1];
                    String outputErrorRedirectFileName = splittedFullCommand[1];
                    splitAndRunHandleError(currentCommmand, outputRedirectionFileName, outputErrorRedirectFileName);

                } else {
                    String[] splittedFullCommand = commandInput.split("> ");
                    String currentCommmand = splittedFullCommand[0];
                    String outputRedirectionFileName = splittedFullCommand[1];
                    splitAndRun(currentCommmand, "output", outputRedirectionFileName);
                }

            }
            if(commandInput.contains("<")) {
                String[] splittedFullCommand = commandInput.split("< ");
                String currentCommmand = splittedFullCommand[0];
                String inputRedirectionFileName = splittedFullCommand[1];
                try {
                    splitAndRun(currentCommmand, "input", inputRedirectionFileName);
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

                System.out.println("full command -> " + Arrays.toString(splittedFullCommand));

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
                    splitAndRunWithInputAndOutputRedirect(currentCommmand, inputRedirectionFileName, outputRedirectionFileName);
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
                splitAndRun(commandInput, "", "");
            }
        }
    }

    public static AvailableCommands search(String input) {
        for (AvailableCommands command : AvailableCommands.values()) {
            if (command.name().equals(input)) {
                return command;
            }
        }
        return AvailableCommands.not_found;
    }
}