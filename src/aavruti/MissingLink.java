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
		return driver;
	}

	void findMissingLink() {
		WebDriver driver = driverSetup();
		driver.get("file:///G:/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");

		if(driver.findElement(By.xpath("//p")).getText().equals("Missing Link")) {
			List<WebElement> linkList = driver.findElements(By.xpath("//a"));
			System.out.println("\n" + linkList.size() + " links on the page.");

			String missingLink = "";
			String hrefAttributeMissing = "";
			System.out.println("\nThe list of links are : ");
			for(WebElement linkName : linkList) {
				System.out.println(linkName.getText());
				try {
					if(linkName.getAttribute("href").equals(""))
						missingLink += linkName.getText();
				}
				catch(NullPointerException ne) {
					hrefAttributeMissing += linkName.getText();
				}
			}
			System.out.println("\nMissing Links --> " + missingLink);
			System.out.println("href Attribute Missing links --> " + hrefAttributeMissing);
		}
		else {
			System.out.println("URL is not correct/Not properly loaded");
		}
	}

	public static void main(String args[]) {
		new MissingLink().findMissingLink();
	}
}