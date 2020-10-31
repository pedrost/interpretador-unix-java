package commands;
import java.io.*;
import helpers.FileCreator;
import helpers.FileWrite;
import helpers.FileRead;

public class Echo implements Command {

	public Echo() {
	}

    @Override
	public void run(String string) {
        if(string.length() <= 0) {
            System.out.println("Echo command needs a parameter");
        } else {
            if(String.valueOf(string.charAt(0)).equals("$")) {
                // Variavel de ambiente, substring para remover o $
                String environmentVar = System.getenv(string.substring(1));
                if(environmentVar != null) {
                    System.out.println(System.getenv(string.substring(1)));
                }
            } else {
                System.out.println(string);
            }
        }
	}

	public void runWithRedirectedOutput(String input, String output) throws IOException {
        FileCreator file = new FileCreator();

        if(input.length() <= 0) {
            file.createFile(output);
            new FileWrite().write(output, "", "error.txt");
        } else {
            if(String.valueOf(input.charAt(0)).equals("$")) {
                String environmentVar = System.getenv(input.substring(1));
                if(environmentVar != null) {
                    file.createFile(output);
                    new FileWrite().write(output, System.getenv(input.substring(1)), "error.txt");
                }
            } else {
                file.createFile(output);
                new FileWrite().write(output, input, "error.txt");
            }
        }
    }

    public void runWithRedirectedOutputHandleError(String input, String output, String outputError) throws IOException {
        FileCreator file = new FileCreator();

        if(input.length() <= 0) {
            file.createFile(output);
            new FileWrite().write(output, "", outputError);
            new FileWrite().write(outputError, "", outputError);
        } else {
            if(String.valueOf(input.charAt(0)).equals("$")) {
                String environmentVar = System.getenv(input.substring(1));
                if(environmentVar != null) {
                    file.createFile(output);
                    new FileWrite().write(output, System.getenv(input.substring(1)), outputError);
                }
            } else {
                file.createFile(output);
                new FileWrite().write(output, input, outputError);
            }
        }
    }

    public void runWithRedirectedInput(String inputRedirect) throws IOException {
        FileRead file = new FileRead(inputRedirect);
        String read = file.read();

        if(file.exists()) {
            if(read.length() <= 0) {
                System.out.println("Echo command needs a parameter");
            } else {
                if(String.valueOf(read.charAt(0)).equals("$")) {
                    // Variavel de ambiente, substring para remover o $
                    String environmentVar = System.getenv(read.substring(1));
                    if(environmentVar != null) {
                        System.out.println(System.getenv(read.substring(1)));
                    }
                } else {
                    System.out.println(read);
                }
            }
        } else {
            System.out.println("Entry point does not exists.");
        }
    }
}