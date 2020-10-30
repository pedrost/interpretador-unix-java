package commands;

import java.util.*;
import commands.Command;
import commands.Echo;
import commands.Cd;
import commands.NotFound;
import commands.Ls;
import commands.Clear;

import core.Dir;

public class Main {

    //    public static Dir dir = new Dir(System.getPropertu("user.dir"));
    public static Dir dir = new Dir(System.getenv("PWD"));

    public static Cd cdCommand = new Cd(dir);
    private static Clear clearCommand = new Clear();
    public static Echo echoCommand = new Echo();
    public static Ls lsCommand = new Ls(dir);
    public static NotFound notFoundCommand = new NotFound();
    
    public enum AvailableCommands {
        cat(echoCommand),
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
    }

    private static void splitAndRun(String command) {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands searchedCommand = search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        searchedCommand.run(commandArgs.toString());
    }

    public static void main(String[] args) {

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
                    splitAndRun(command);
                }
                continue;
            }
            if(commandInput.contains(">")) {
                // pegar 0
                // pegar saida dele
                // criar arquivo
                // colocar saida no arquivo
            }
            splitAndRun(commandInput);
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