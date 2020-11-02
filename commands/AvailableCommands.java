package commands;

import commands.*;
import core.Dir;
import core.Prompt;
import core.Dir;
import core.Prompt;

import java.io.IOException;

public class AvailableCommands {
    public static Dir dir = new Dir(System.getProperty("user.dir"));
    public static Prompt prompt = new Prompt(dir.getDir() + " $", System.getenv("PATH"));

    public static Cd cdCommand = new Cd(dir);
    private static final Clear clearCommand = new Clear();
    public static Echo echoCommand = new Echo(prompt);
    public static History historyCommand = new History();
    public static Ls lsCommand = new Ls(dir);
    public static Export exportCommand = new Export(prompt);
    public static NotFound notFoundCommand = new NotFound(prompt);

    public AvailableCommands() {
    }

    public enum CommandsEnum {
        cd(cdCommand),
        clear(clearCommand),
        echo(echoCommand),
        history(historyCommand),
        ls(lsCommand),
        export(exportCommand),
        not_found(notFoundCommand);

        private final Command commandClass;

        CommandsEnum(Command commandClass) {
            this.commandClass = commandClass;
        }

        public void run(String args, Boolean isBackground) {
            this.commandClass.run(args, isBackground);
        }

        public void runWithRedirectedOutput(String input, String output, Boolean isBackground) throws IOException {
            this.commandClass.runWithRedirectedOutput(input, output, isBackground);
        }

        public void runWithRedirectedOutputHandleError(String input, String output, String outputError, Boolean isBackground) throws IOException {
            this.commandClass.runWithRedirectedOutputHandleError(input, output, outputError, isBackground);
        }

        public void runWithRedirectedInput(String inputRedirect, Boolean isBackground) throws IOException {
            this.commandClass.runWithRedirectedInput(inputRedirect, isBackground);
        }

        public void runWithInputAndOutputRedirect(String command, String input, String output, Boolean isBackground) throws IOException {
            this.commandClass.runWithInputAndOutputRedirect(command, input, output, isBackground);
        }

    }

    public static CommandsEnum search(String input) {
        for (CommandsEnum command : CommandsEnum.values()) {
            if (command.name().equals(input)) {
                return command;
            }
        }
        prompt.setCurrentNotFoundCommand(input);
        return CommandsEnum.not_found;
    }

}
