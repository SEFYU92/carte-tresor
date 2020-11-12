package com.carbon.apps.util;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class ResourceLoader {
	
	public String getResourceDefaultFile() {
		return this.getResourceDefaultFile("testfile.txt");
	}
	
	public String getResourceDefaultFile(String file) {
		String path = "";
		try {
			
			path = Paths.get(getClass().getClassLoader().getResource(file).toURI()).toString();
			
		} catch (URISyntaxException e) {
			System.err.println("error during resource test file loading");
			e.printStackTrace();
		} 
		return path;
	}
	
}
