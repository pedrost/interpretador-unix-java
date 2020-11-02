package contexts;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import core.*;
import helpers.Runner;
import commands.History;

public class ForegroundExecution {

    private static final Dir dir = new Dir(System.getProperty("user.dir"));
    private static final Prompt prompt = new Prompt(dir.getDir() + " $", System.getenv("PATH"));

    public ForegroundExecution() { }

    public static void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            final History historyCommand = new History();

            while(true){
                String ps1 = prompt.getPrompt();
                System.out.printf("%s> ", ps1);
                Scanner input = new Scanner(System.in);
                String commandInput = input.nextLine();
                historyCommand.pushItem(commandInput);

                if(commandInput.endsWith("&")) {
                    BackgroundExecution backgroundExecution = new BackgroundExecution(commandInput);
                    backgroundExecution.start();
                    continue;
                }
                if(commandInput.equals("exit")) {
                    System.out.println("Bye !");
                    Thread.currentThread().interrupt();
                    return;
                }
                if(commandInput.contains("&&")) {
                    String[] splittedFullCommand = commandInput.split("&& ");

                    System.out.println(Arrays.toString(splittedFullCommand));

                    for(String command : splittedFullCommand) {
                        try {
                            Runner.splitAndRun(command, "", "", false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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
                        try {
                            Runner.splitAndRunHandleError(currentCommmand, outputRedirectionFileName, outputErrorRedirectFileName, false);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        String[] splittedFullCommand = commandInput.split("> ");
                        String currentCommmand = splittedFullCommand[0];
                        String outputRedirectionFileName = splittedFullCommand[1];
                        try {
                            Runner.splitAndRun(currentCommmand, "output", outputRedirectionFileName, false);
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
                        Runner.splitAndRun(currentCommmand, "input", inputRedirectionFileName, false);
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
                        Runner.splitAndRunWithInputAndOutputRedirect(currentCommmand, inputRedirectionFileName, outputRedirectionFileName, false);
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
                        System.out.println("from primary thread:");
                        Runner.splitAndRun(commandInput, "", "", false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
    }
}
