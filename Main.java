package commands;
import java.util.*;
import commands.Echo;
import commands.Command;


public class Main {

    public static Echo echoCommand = new Echo();
    
    public enum AvailableCommands {
        echo(echoCommand),
        cd(echoCommand),
        cat(echoCommand),
        not_found(echoCommand);

        private final Command commandClass;
        AvailableCommands(Command commandClass) {
            this.commandClass = commandClass;
        }

        public void run(String args) {
            this.commandClass.run(args);
        }
    }

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        

        while(true){
            System.out.printf("%s> $ ", currentDir);
            Scanner input = new Scanner(System.in);
            String commandInput = input.nextLine();

            String[] splittedCommand = commandInput.split(" ");
            String currentCommand = splittedCommand[0];

            try {
                String commandArgs = splittedCommand[1];
                AvailableCommands searchedCommand = search(commandInput);
                searchedCommand.run(commandArgs);
            } catch(Exception e) {
                String commandArgs = "";
                AvailableCommands searchedCommand = search(commandInput);
                searchedCommand.run(commandArgs);
            }


            if(commandInput.equals("exit")) {
                System.out.println("Bye !");
                System.exit(1);
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