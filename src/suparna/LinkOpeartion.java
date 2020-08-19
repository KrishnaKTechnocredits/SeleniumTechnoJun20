package suparna;

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
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LinkOpeartion {

	private String path;

	LinkOpeartion() {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("Webdriver.chrome.driver", path);

	}

	public void linkTagOperation() {
		WebDriver driver = new ChromeDriver();

		driver.get("D:\\Suparna_Automation\\TechnoGitProject\\SeleniumTechnoJun20\\resources\\forms\\MissingLink.html");
		/*
		 * try { Thread.sleep(10000); } catch (InterruptedException IE) {
		 * IE.printStackTrace(); }
		 */
		/*
		 * String pageTitle = driver.getTitle(); pageTitle.compareTo("Missing Link")
		 */
		if (driver.getCurrentUrl().equals(
				"file:///D:/Suparna_Automation/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html"))
			System.out.println("Test case 1: open web page of MissingLink.html  --> pass");
		else
			System.out.println("Test case 1: open web page of MissingLink.html  --> Fail");
		List<WebElement> WebElementList = driver.findElements(By.xpath("//a"));

		int linkCnt = WebElementList.size();
		if (linkCnt > 0) {
			System.out.println("Total no of Link count  on MisingLink.html is :" + linkCnt);
			System.out.println("Test case 2 : Find total Link  tag count  Pass");
		} else {
			System.out.println("No Link available on webpage ");
			System.out.println("Test case 2 : Find total Link  tag count  Faill");

		}
		int i = 1;
		System.out.println("\nlink available  in this form are ");
		for (WebElement linkElement : WebElementList) {
			String linkTextValue = linkElement.getText();
			System.out.println((i++) + ":" + linkTextValue);
			// System.out.println( WebElementList.indexOf(linkTextValue +1) +
			// linkTextValue);

		}
		System.out.println("\n link tage for which  link adredd is missing   are ");
		for (WebElement linkElement : WebElementList) {
			String missingLink = linkElement.getAttribute("href");
			try {
				if (missingLink.isEmpty())
				// if( missingLink.isEmpty() ||missingLink == null)
				{
					System.out.println("missingLink-> " + linkElement.getText());
					System.out.println("value of href tage is :" + linkElement.getAttribute("href"));
				}
			} catch (NullPointerException e) {
			}
		}

		System.out.println("\nlink tage for which  href attribute is missing are ");
		for (WebElement linkElement : WebElementList) {
			String missingLink = linkElement.getAttribute("href");

			try {

				if (missingLink.equals(null))
					System.out.println("missingLink-> " + linkElement.getText());
			}

			catch (NullPointerException e) {
				System.out.println("missingLink-> " + linkElement.getText());
				System.out.println("value of href tage is : " + linkElement.getAttribute("href"));
			}

		}
		driver.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkOpeartion linkOperation = new LinkOpeartion();
		linkOperation.linkTagOperation();

	}

}
