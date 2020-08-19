package harshad.selenium_assignment4;

/*Assignment 4 :  19th Jun 2020

Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
(This form is available on master branch > take a pull. )

1) Find total number of links.
Output : 6 links on the page.

2) Print text of all links.

3) Print text of Missing links.
Output : Missing Link -> Instagram

4) Print the name of link where href attribute is missing.
Output : Synechron*/


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinksValidationsInsideDom {
	
	private static WebDriver openBrowser(String url) {
		
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		System.out.println("\nTotal "+allLinks.size()+" links in the page");
		
		System.out.println("\nText of all links as below:");
		for(WebElement linkElement: allLinks) {
			System.out.println(linkElement.getText());
		}
		
		System.out.println("\nText of Missing links as below:");
		for(WebElement linkElement: allLinks) {
			try {				
				if(linkElement.getAttribute("href").isEmpty()) {
					System.out.println("Missing Link -> "+linkElement.getText());
				}
			}catch(NullPointerException ne) {
				System.out.println("\nThe name of links where href attribute is missing:");
				System.out.println(linkElement.getText());
			}
			
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return driver;
	}
	
	public static void main(String[] args) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		
		WebDriver driver = openBrowser("file:///S:/TechnoGitProject/SeleniumProject/Assignment4/MissingLink.html");
		//WebDriver driver = openBrowser(".//resources/forms/MissingLink.html");
		driver.close();
	}
}
