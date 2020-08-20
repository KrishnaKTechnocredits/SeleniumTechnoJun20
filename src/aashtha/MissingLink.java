package aashtha;
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

public class MissingLink {
	WebDriver driver = new ChromeDriver();

	List<WebElement> findTotallLinks() {
		driver.get("file:///D:/JavaBasics_JUN20/TechnoGitProject/seleniumtechnojun20/resources/forms/MissingLink.html");
		List<WebElement> listOfLinks = driver.findElements(By.xpath("//a"));
		System.out.println("1) Total - " + listOfLinks.size() + " links are available on the page.");
		System.out.println("\n2) Below is the text of all the links:");
		for (WebElement currentLink : listOfLinks)
			System.out.println("  - " + currentLink.getText());
		return listOfLinks;
	}

	void printMissingLinkAndHref(List<WebElement> listOfLinks) {
		for (WebElement currentLink : listOfLinks) {
			try {
				if (currentLink.getAttribute("href").equals(""))
					System.out.println("\n3) Missing Link -> " + currentLink.getText());
			} catch (NullPointerException ne) {
				System.out.println("\n4) Link with href attribute missing -> " + currentLink.getText());
			}
		}
		driver.close();
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		MissingLink missingLink = new MissingLink();
		List<WebElement> listOfLinks = missingLink.findTotallLinks();
		missingLink.printMissingLinkAndHref(listOfLinks);
	}
}
