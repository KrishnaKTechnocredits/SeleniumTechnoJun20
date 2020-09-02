package barkha_utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperation {
	Properties prop;

	public PropertyFileOperation(String path) throws IOException {
		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file);

		Properties prop = new Properties();
		prop.load(inputStream);
	}

	public String propRead(String path, String key) {
		return prop.getProperty(key);
	}
}