package rachana;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HyperLinksTesting {

	static void testLinksOnPage(WebDriver driver) {

		// 1. Find total number of links.
		List<WebElement> listofHyperlinks = driver.findElements(By.xpath("//a"));
		System.out.println("Total Number of Hyperlinks available on page :" + listofHyperlinks.size());

		// 2. Print text of all links.
		System.out.println("Text for all links:");
		for (WebElement ele : listofHyperlinks) {
			System.out.println(ele.getText());
		}

		// 3. Print text of Missing links.//4. Print the name of link where href attribute is missing.
		System.out.print("Text for missing links: ");
		for (WebElement ele : listofHyperlinks) {
			try {
				if (ele.getAttribute("href").length() == 0) {
					System.out.println(ele.getText());
				}
			}catch(NullPointerException e) {
				System.out.println("Link name for which href attribute is missing: "+ele.getText());
			}
		}
	}

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", ".//resources//windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(
				"file:///G:/Technocredits/TechnoGitSeleniumProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		driver.manage().window().maximize();
		testLinksOnPage(driver);
		driver.close();

	}
}