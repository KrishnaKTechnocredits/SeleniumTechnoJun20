/*Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
(This form is available on master branch > take a pull. )

1) Find total number of links.
Output : 6 links on the page.

2) Print text of all links.

3) Print text of Missing links.
Output : Missing Link -> Instagram

4) Print the name of link where href attribute is missing.
Output : Synechron */
package barkha;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindMissingLink {
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("D:\\TechnoGitProjectSelenium\\SeleniumTechnoJun20\\resources\\forms\\MissingLink.html");
		driver.manage().window().maximize();

		// Find total number of links.
		List<WebElement> availableLinks = driver.findElements(By.xpath("//a"));
		System.out.println("Total No of links on the page is: " + availableLinks.size());
		System.out.println("===============================================================================");
		// Print text of all links.
		for (WebElement Links : availableLinks) {
			System.out.println("Names of links available on page are: " + Links.getText());
		}	
		System.out.println("===============================================================================");
		for (WebElement Links : availableLinks) {
			// Print text of Missing links. (no value assigned to href)
			try {
				if (Links.getAttribute("href").equals("")) {
					System.out.println("Missing link (No Value assigned to href) is: " + Links.getText());
				}
				//Print the name of link where href attribute is missing.
			} catch (NullPointerException ne) {
				System.out.println("Link where href attribute is missing: "+Links.getText());
			}
		}
		driver.close();
	}
}