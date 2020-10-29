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
            System.out.println(string);
        }
	}
}