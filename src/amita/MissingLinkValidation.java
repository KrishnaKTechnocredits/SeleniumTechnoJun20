/*krishna, 02:33
Assignment 4 :  19th Jun 2020
Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
(This form is available on master branch > take a pull. )

1) Find total number of links.
Output : 6 links on the page.

2) Print text of all links.

3) Print text of Missing links.
Output : Missing Link -> Instagram

4) Print the name of link where href attribute is missing.
Output : Synechron*/
package amita;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MissingLinkValidation {

	void findMissingLink(WebDriver driver) {

		// Verify below scenarios from SeleniumTechnoJun20 > resources > forms >
		// MissingLink.html
		driver.get("file:///D:/JAVA-JUNE20/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		driver.manage().window().maximize();

		// 1) Find total number of links.
		if (driver.findElement(By.xpath("//p")).getText().equals("Missing Link")) {
			List<WebElement> linkList = driver.findElements(By.xpath("//a"));
			System.out.println("Total number of Links present : " + linkList.size());

			// 2) Print text of all links.
			ArrayList<String> missingLinksList = new ArrayList<String>();
			ArrayList<String> missingHrefList = new ArrayList<String>();
			System.out.println("\nThe name of the links are : ");
			for (WebElement linkname : linkList) {
				System.out.println(linkname.getText());
				try {
					if (linkname.getAttribute("href").equals(""))
						missingLinksList.add(linkname.getText());
				} catch (NullPointerException ne) {
					missingHrefList.add(linkname.getText());
				}
			}
			System.out.println("\nThe missing links are :" + missingLinksList);
			System.out.println("The links with href missing are : " + missingHrefList);
		} else {
			System.out.println("Invalid URL");
		}
	}

	public static void main(String[] args) {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		MissingLinkValidation linkvalidation = new MissingLinkValidation();
		linkvalidation.findMissingLink(driver);
		driver.close();
	}
}
