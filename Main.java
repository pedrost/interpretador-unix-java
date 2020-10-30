package commands;

import java.io.IOException;
import java.util.*;
import commands.Command;
import commands.Echo;
import commands.Cd;
import commands.NotFound;
import commands.Ls;
import commands.Clear;

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

    }

    private static void splitAndRun(String command, Boolean withRunOutputRedirect, String output) throws IOException {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands searchedCommand = search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        if (withRunOutputRedirect) {
            searchedCommand.runWithRedirectedOutput(commandArgs.toString(), output);
        } else {
            searchedCommand.run(commandArgs.toString());
        }
    }

    public static void main(String[] args) throws IOException {

        while(true){
            System.out.printf("%s> $ ", dir.getDir());
            Scanner input = new Scanner(System.in);
            String commandInput = input.nextLine();

            if(commandInput.equals("exit")) {
                System.out.println("Bye !");
                System.exit(1);
            }
            if(commandInput.contains("&&")) {
                String[] splittedFullCommand = commandInput.split("&& ");

                for(String command : splittedFullCommand) {
                    splitAndRun(command, false, "");
                }
                continue;
            }
            if(commandInput.contains(">")) {
                String[] splittedFullCommand = commandInput.split("> ");
                String currentCommmand = splittedFullCommand[0];
                String outputRedirectionFileName = splittedFullCommand[1];
                splitAndRun(currentCommmand, true, outputRedirectionFileName);
            }
            else {
                splitAndRun(commandInput, false, "");
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