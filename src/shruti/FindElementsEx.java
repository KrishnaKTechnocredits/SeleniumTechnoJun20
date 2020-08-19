/*
 1) Find total number of links.
 Output : 6 links on the page.
 2) Print text of all links.
 3) Print text of Missing links.
 Output : Missing Link -> Instagram
 4) Print the name of link where href attribute is missing.
 Output : Synechron
 */
package shruti;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementsEx {

	WebDriver driver;
	void findLinkandPrint(String url) {

		System.setProperty("webdriver.chrome.driver",
				".\\resources\\windows\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get(url);

		List<WebElement> listOfLinks = driver.findElements(By.xpath("//a"));
		System.out.println("Total number of links: " + listOfLinks.size());

		for (WebElement currentlink : listOfLinks) {
			System.out.println(currentlink.getText());
		}

		for (WebElement currentlink : listOfLinks) {
			try {
				if (currentlink.getAttribute("href").equals("")) {
					System.out.println("Missing Link is: " + currentlink.getText());
				}
			} catch (NullPointerException ne) {
				System.out.println("Link with no href Value: " + currentlink.getText());
			}
		}
		driver.close();

	}

	public static void main(String[] args) {
		FindElementsEx FindElementsEx = new FindElementsEx();
		FindElementsEx
				.findLinkandPrint("file:///D:/Technocredit/Selenium%20recordings/@Selenium/Day4/MissingLink.html");
	}

}
