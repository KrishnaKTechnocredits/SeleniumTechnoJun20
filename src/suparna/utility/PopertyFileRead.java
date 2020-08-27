package suparna.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PopertyFileRead {

	private Properties prop;
	
	public  PopertyFileRead (String path) throws IOException
	{
		File file = new  File (path);
		FileInputStream inputStream = new FileInputStream(file);
		prop = new Properties ();
		prop.load(inputStream);
	}
	
	public String getPropertyValue(String key)
	{
		return prop.getProperty(key);
	}
}
