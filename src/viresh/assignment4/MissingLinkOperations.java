/*
Assignment 4 :  19th Jun 2020

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

package viresh.assignment4;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MissingLinkOperations {
	
	void linkOperations(WebDriver driver, ArrayList<WebElement> wList) {
		String missingLink = null, missinghRef= null;
		System.out.println("Number of links on Webpage: " + wList.size());	
		System.out.println("");
		System.out.println("Text of each link: ");
		for (int index=0; index< wList.size(); index++) {
			System.out.println("  - " + wList.get(index).getText());
			String name= wList.get(index).getAttribute("hRef");
			try {
				if (name.equals("")) {
					 missingLink= wList.get(index).getText();
				}
			}catch (NullPointerException ne) {
					missinghRef= wList.get(index).getText();
			}
		}
		System.out.println("");
		System.out.println("Link name with blank hRef: " + missingLink);	
		System.out.println("");
		System.out.println("Link name with missing hRef: " + missinghRef);
	}
	
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".//resources/windows/chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("file:///C:/Users/Viresh.jangam/OneDrive%20-%20Wolters%20Kluwer/Desktop/Viresh/TechnoCredits/TechnoGitProject/javatechnojun20/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		ArrayList <WebElement> wList= new  ArrayList<WebElement>(driver.findElements(By.xpath("//a")));
		 new MissingLinkOperations().linkOperations(driver, wList);
		 System.out.println("");		 
		 driver.close();
	}
}
