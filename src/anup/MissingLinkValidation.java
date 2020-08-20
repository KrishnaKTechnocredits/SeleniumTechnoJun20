package anup;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

public class MissingLinkValidation {
	void validateMissingLink(WebDriver driver) {
//1) Find total number of links.
		List<WebElement> listoflinks = driver.findElements(By.xpath("//a"));
		System.out.println("Total Number of links in the webpage are :" + listoflinks.size());
//2) Print text of all links.
		System.out.println("Name of all links in webpage are : ");
		for (WebElement element : listoflinks) {
			System.out.println(element.getText());
		}
		
//3) Print text of Missing links.
//4) Print the name of link where href attribute is missing.
		for (WebElement element : listoflinks) {
			try {
				if (element.getAttribute("href").equals("")) 
					System.out.println("Missing link in the webpage : "+element.getText());
			}catch(NullPointerException ne) {
				System.out.println("Link name of which href attribute is missing: "+element.getText());
			}
		}
	}

	public static void main(String[] args) {

		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe" : os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("file:///Users/amitarout/Desktop/TechnoGitProject/SeleniumJun20/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		driver.manage().window().maximize();
		new MissingLinkValidation().validateMissingLink(driver);
		driver.close();
	}
}
