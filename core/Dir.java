package core;

import java.io.*;

public class Dir {
	private String currentDir;
	
	public Dir(String currentDir) {
		this.currentDir = currentDir;
	}

	public void setDir(String destination, Boolean append) {
		if (append) {
			currentDir = currentDir + destination;
		}
		else {
			currentDir = destination;
		}
	}

	public String getDir() {
		return this.currentDir;
	}
}