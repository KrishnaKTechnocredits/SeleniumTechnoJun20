/*
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
package aditi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MissingLinks {
	private static WebDriver start(String url) {
		System.setProperty("webdriver.chrome.driver", "./resources/mac/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	void linkCountAndVisibleText(List<WebElement> list) {
		System.out.println("\nThere are total " + list.size() + " links available on current page.");
		System.out.println("\n---Text for all links---");
		for (WebElement link : list) {
			System.out.println(" -> " + link.getText());
		}
	}

	void findMissingLink(List<WebElement> list) {
		for (WebElement link : list) {
			try {
				if (link.getAttribute("href").isEmpty() || link.getAttribute("href").length() == 0) {
					System.out.println("\nMissing Link -> " + link.getText());
				}
			} catch (NullPointerException nullPointerException) {
				System.out.println("\nhref attribute is missing for -> " + link.getText());
			}
		}
	}

	public static void main(String[] args) {
		WebDriver driver = start(
				"file:///Users/apple/Documents/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		MissingLinks missingLinks = new MissingLinks();
		List<WebElement> list = driver.findElements(By.xpath("//a"));
		missingLinks.linkCountAndVisibleText(list);
		missingLinks.findMissingLink(list);
		driver.quit();
	}
}
