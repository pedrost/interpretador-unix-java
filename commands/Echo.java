package commands;
import java.io.*;
import helpers.FileCreator;
import helpers.FileWrite;

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
            new FileWrite().write(output, "");
        } else {
            if(String.valueOf(input.charAt(0)).equals("$")) {
                String environmentVar = System.getenv(input.substring(1));
                if(environmentVar != null) {
                    file.createFile(output);
                    new FileWrite().write(output, System.getenv(input.substring(1)));
                }
            } else {
                file.createFile(output);
                new FileWrite().write(output, input);
            }
        }
    }
}