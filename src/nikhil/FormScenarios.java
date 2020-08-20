/*
Assignment 4 : 19th Jun 2020

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

package nikhil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormScenarios {
	private static WebDriver start(String url) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}
	
	List<WebElement> getLinks(WebDriver driver) {
		List<WebElement> listOfLinks = driver.findElements(By.xpath("//a"));
		return listOfLinks;
	}
	
	void displayLinkNumber(List<WebElement> listOfLinks) {
		System.out.println("The number of links of MissingLink webpage : "+listOfLinks.size());
	}
	
	void printLinkText(List<WebElement> listOfLinks) {
		int count=0;
		System.out.println("\nThe Text for all the present links:-");
		for(WebElement element : listOfLinks) {
			count++;
			System.out.println("Link "+count+" : "+element.getText());
		}
	}
	
	void findMissingLink(List<WebElement> listOfLinks) {
		System.out.println();
		for(WebElement element : listOfLinks) {
			if(element.getAttribute("href") != null && element.getAttribute("href").length() == 0) {
				System.out.println("The Missing Link Text:-\n--->"+element.getText());
			}else if(element.getAttribute("href") == null) {
				System.out.println("\nThe Link with missing href attribute:-\n--->"+element.getText());
			}
		}
	}
	
	public static void main(String[] args) {
		WebDriver driver = start("C:\\Users\\Nikhil.Nikhil-Universe.000\\Documents\\TechnoCredits\\TechnoGitProject\\SeleniumTechnoJun20\\resources\\forms\\MissingLink.html");
		FormScenarios formScenarios = new FormScenarios();
		List<WebElement> listOfLinks = formScenarios.getLinks(driver);
		formScenarios.displayLinkNumber(listOfLinks);
		formScenarios.printLinkText(listOfLinks);
		formScenarios.findMissingLink(listOfLinks);
		driver.close();
	}
}
