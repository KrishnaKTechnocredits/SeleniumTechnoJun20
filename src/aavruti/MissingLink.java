package aavruti;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MissingLink {

	static WebDriver driverSetup() {

		String osName = System.getProperty("os.name").toLowerCase();
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe" 
				: osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///G:/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		return driver;
	}

	void findMissingLink() {
		WebDriver driver = driverSetup();		

		try {
			List<WebElement> linkList = driver.findElements(By.xpath("//a"));
			System.out.println("\n" + linkList.size() + " links on the page.");

			System.out.println("\nThe list of links are : ");
			for(WebElement linkName : linkList) {
				try {
					System.out.println(linkName.getAttribute("href").equals("") 
					? "Missing Links --> " + linkName.getText() : linkName.getText());
				}
				catch(NullPointerException ne) {
					System.out.println("href Attribute Missing links --> " + linkName.getText());
				}
			}
		}
		catch(NoSuchElementException ne) {
			System.out.println("Given Element Not Found");
		}
		finally {
			driver.quit();
		}
	}

	public static void main(String args[]) {
		new MissingLink().findMissingLink();
	}
}