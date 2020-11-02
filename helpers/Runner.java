package helpers;

import commands.AvailableCommands;

import java.io.IOException;

public class Runner {

    public Runner(){}

    public static void splitAndRun(String command, String redirectType, String redirectFile, Boolean isBackground) throws IOException {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands.CommandsEnum searchedCommand = AvailableCommands.search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        if (redirectType.equals("output")) {
            searchedCommand.runWithRedirectedOutput(commandArgs.toString(), redirectFile, isBackground);
        } if (redirectType.equals("input")) {
            searchedCommand.runWithRedirectedInput(redirectFile, isBackground);
        }
        else {
            searchedCommand.run(commandArgs.toString(), isBackground);
        }
    }

    public static void splitAndRunHandleError(String command, String output, String outputError, Boolean isBackground) throws IOException {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands.CommandsEnum searchedCommand = AvailableCommands.search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        searchedCommand.runWithRedirectedOutputHandleError(commandArgs.toString(), output, outputError, isBackground);
    }

    public static void splitAndRunWithInputAndOutputRedirect(String command, String input, String output, Boolean isBackground) throws IOException {
        String[] splittedCommand = command.split(" ");
        String currentCommand = splittedCommand[0];
        AvailableCommands.CommandsEnum searchedCommand = AvailableCommands.search(currentCommand);
        StringBuilder commandArgs = new StringBuilder();

        for(int i = 1; i <= splittedCommand.length - 1; i++) {
            commandArgs.append(splittedCommand[i]);
        }

        searchedCommand.runWithInputAndOutputRedirect(commandArgs.toString(), input, output, isBackground);
    }
}
