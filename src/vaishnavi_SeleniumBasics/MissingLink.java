package vaishnavi_SeleniumBasics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

/* Assignment 4 :  19th Jun 2020

Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
(This form is available on master branch > take a pull. )
1) Find total number of links.
Output : 6 links on the page.
2) Print text of all links.
3) Print text of Missing links.
Output : Missing Link -> Instagram
4) Print the name of link where href attribute is missing.
Output : Synechron */

public class MissingLink {

	void findLinksCount(List<WebElement> li) {

		// Counting Number of links on the page through xpath
		System.out.println("Number of links count on this page: " + li.size());

		// Printing visible text of all the links
		System.out.println("=======Visible Texts of Links=============");
		for (WebElement text : li) {
			System.out.println(text.getText());
		}
	}

	void findMissingLink(List<WebElement> li) {
		System.out.println("==================================");
		for (WebElement text : li) {
			try {
				String missingLink = text.getAttribute("href");
				// System.out.println(missingLink);
				if (missingLink.isEmpty())					
					System.out.println("Missing link is: " + text.getText());  // Print text of Missing links(means href tag is blank)
			} catch (NullPointerException n) {
				System.out.println("==================================");
				System.out.println("href attribute is missing at: : " + text.getText()); // This will print the name of link where href attribute is missing.
			}
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/vaish/Desktop/Selenium_Recordings/MissingLink.html");
		List<WebElement> li = driver.findElements(By.xpath("//a"));
		MissingLink missingLink = new MissingLink();
		missingLink.findLinksCount(li);
		missingLink.findMissingLink(li);
	}

}
