/*Assignment 4 : 19th Jun 2020

Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
(This form is available on master branch > take a pull. )

1) Find total number of links.
Output : 6 links on the page.

2) Print text of all links.

3) Print text of Missing links.
Output : Missing Link -> Instagram

4) Print the name of link where href attribute is missing.
Output : Synechron*/
package palak;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindAndPrintLinks {

	static WebDriver start() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("H:/TechnoCredits/Projects/Techno Git Project/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		driver.manage().window().maximize();
		return driver;
	}

	void findLinks() {
		WebDriver driver = start();
		List<WebElement> list = driver.findElements(By.xpath("//a"));
		System.out.println("Output : " + list.size() + " links on the page");

		System.out.println("List of all Links are ");
		for (WebElement webElement : list) {
			System.out.println(webElement.getText());
		}
		
		for (WebElement webElement : list) {
			try {
				if (webElement.getAttribute("href").equals("")) {
					System.out.println("Missing Link -> " +webElement.getText());
				}
			} catch (NullPointerException e) {
				System.out.println("href Attribute is missing -> "+ webElement.getText());
			}
		}
	}

	public static void main(String[] args) {
		FindAndPrintLinks findAndPrintLinks = new FindAndPrintLinks();
		findAndPrintLinks.findLinks();
	}

}
