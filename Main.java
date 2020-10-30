package commands;

import java.util.*;
import commands.Command;
import commands.Echo;
import commands.Cd;
import commands.NotFound;

import core.Dir;

public class Main {

    public static Dir dir = new Dir(System.getProperty("user.dir"));
    public static Echo echoCommand = new Echo();
    public static Cd cdCommand = new Cd(dir);
    public static NotFound notFoundCommand = new NotFound();
    
    public enum AvailableCommands {
        echo(echoCommand),
        cd(cdCommand),
        cat(echoCommand),
        not_found(notFoundCommand);

        private final Command commandClass;
        AvailableCommands(Command commandClass) {
            this.commandClass = commandClass;
        }

        public void run(String args) {
            this.commandClass.run(args);
        }
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

            String[] splittedCommand = commandInput.split(" ");
            String currentCommand = splittedCommand[0];

            AvailableCommands searchedCommand = search(currentCommand);
            String commandArgs = "";
            for(int i = 1; i <= splittedCommand.length - 1; i++) {
                commandArgs += splittedCommand[i];
            }
            searchedCommand.run(commandArgs);

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