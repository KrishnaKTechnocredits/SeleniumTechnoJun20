package technoCredits.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperation {
	private Properties properties;
	
	public PropertyFileOperation(String path) throws IOException {
		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file);
		
		properties = new Properties();
		properties.load(inputStream);
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
