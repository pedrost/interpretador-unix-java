package commands;
import java.io.*;

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
}