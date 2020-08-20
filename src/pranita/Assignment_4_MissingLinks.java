/*
 * Assignment 4 :  19th Jun 2020

Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
(This form is available on master branch > take a pull. )

1) Find total number of links.
Output : 6 links on the page.

2) Print text of all links.

3) Print text of Missing links.
Output : Missing Link -> Instagram

4) Print the name of link where href attribute is missing.
Output : Synechron
 */
package pranita;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment_4_MissingLinks {
	void findMissingLinks(WebDriver driver) {
		driver.get("file:///E:/Technocredits/TechnoGitProject/SeleniumPranitaTechno20/resources/forms/MissingLink.html");
		List<WebElement> listOfLinks = driver.findElements(By.xpath("//a"));
		System.out.println("Total number of links on the page are: " + listOfLinks.size());
		System.out.println("\nAll links are:");
		for (WebElement currentlink : listOfLinks) {
			System.out.println(currentlink.getText());
		}
		for (WebElement currentlink : listOfLinks) {
			try {
				if (currentlink.getAttribute("href").equals("")) {
					System.out.println("\nMissing Link is: " + currentlink.getText());
				}
			} catch (NullPointerException ne) {
				System.out.println("\nLink where href attribute is missing: " + currentlink.getText());
			}
		}	
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		new Assignment_4_MissingLinks().findMissingLinks(driver);
		driver.close();
	}

}
