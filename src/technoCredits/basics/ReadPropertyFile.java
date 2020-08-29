package technoCredits.basics;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	void propRead() throws IOException {
		// open file
		File file = new File(".//src//technoCredits//config//BasicElementData.properties");
		// enable readable mode
		FileInputStream inputStream = new FileInputStream(file);
		
		Properties prop = new Properties();
		prop.load(inputStream); // load file in memory
		
		String firstName = prop.getProperty("firstName");
		String lastName = prop.getProperty("LastName");
		System.out.println(firstName);
		System.out.println(lastName);
	}
	
	public static void main(String[] args) throws IOException {
		new ReadPropertyFile().propRead();
	}
}
