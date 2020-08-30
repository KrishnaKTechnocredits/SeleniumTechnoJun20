package vaishnavi.PropertyFileOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperation {
	Properties properties;

	public PropertyFileOperation(String path) throws IOException {
		// Open File
		File file = new File(path);
		// Enable readable mode
		FileInputStream inputstream = new FileInputStream(file);
		properties = new Properties();
		properties.load(inputstream); // loads file into memory
	}

	public String propRead(String Key) {
		return properties.getProperty(Key);
	}
}
