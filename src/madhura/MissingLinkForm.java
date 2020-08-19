package madhura;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*Assignment 4 :  19th Jun 2020
Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
1) Find total number of links.
Output : 6 links on the page.
2) Print text of all links.
3) Print text of Missing links.
Output : Missing Link -> Instagram
4) Print the name of link where href attribute is missing.
Output : Synechron*/
public class MissingLinkForm {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///E:/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		driver.manage().window().maximize();

		// total number of links
		List<WebElement> list = driver.findElements(By.xpath("//a"));
		System.out.println(list.size() + " links on the page");

		// text on the links
		System.out.println("\nText on all links : ");
		for (WebElement we : list) {
			System.out.println(we.getText());
		}

		// text of missing link and Link where href attribute is missing
		for (WebElement element : list) {
			try {
				if (element.getAttribute("href").equals("")) {
					System.out.println("\nMissing link -> " + element.getText());
				}
			} catch (NullPointerException ne) {
				if (element.getAttribute("href") == null) {
					System.out.println("\nLink where href attribute is missing -> " + element.getText());
				}
			}
		}
		driver.quit();
	}
}