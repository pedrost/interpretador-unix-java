import java.io.*;

public class Cd {
	private CurrentDir dir;
	private String destination;
	
	public Cd(String destination, CurrentDir dir) {
		super(null, null);
		this.dir = dir;
		this.destination = destination;
	}
	public Object transform(Object o) {
	
		if (destination.equals("..")) {		
			File file = new File(dir.getDir());
			String fileString;
			fileString = file.getParent();
			dir.setDir(fileString, false);
		}

		else if (destination.equals(".")) {
			
		}
		
		else {
			dir.setDir(System.getProperty("file.separator") + destination, true);
		}
	
		this.done = true;
		return null;
	}
}