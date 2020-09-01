package aavruti.utility;

import java.io.*;
import java.util.*;

public class PropertyFileOperation {
	
	Properties prop;
	
	public PropertyFileOperation(String path) throws IOException {
		File file = new File(path);		
		FileInputStream inputStream = new FileInputStream(file);
		
		prop = new Properties();		
		prop.load(inputStream);
	}

	public String propRead(String key) {
		return prop.getProperty(key);
	}
}
