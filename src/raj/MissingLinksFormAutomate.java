/* Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
(This form is available on master branch > take a pull. )

1) Find total number of links.
Output : 6 links on the page.

2) Print text of all links.

3) Print text of Missing links.
Output : Missing Link -> Instagram

4) Print the name of link where href attribute is missing.
Output : Synechron */

package raj;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MissingLinksFormAutomate {
	static WebDriver browserSetup() {
		System.setProperty("webdriver.chrome.driver", ".//resources//windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///G:/Technocredits/Projects/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		return driver;
	}

	void findMissingLinkAndCount() {
		WebDriver driver = browserSetup();
		//Count of Links on page
		List<WebElement> listOfLinks = driver.findElements(By.xpath("//a"));
		System.out.println("\n" + listOfLinks.size() + " links are available on the page.");

		//Link Title's
		System.out.println("\nList of link Title's are : ");
		for(WebElement linkName : listOfLinks) {
			System.out.println(linkName.getText());
		} 
		//Missing Links Info
		for(WebElement linkName : listOfLinks) {
			try {
				if(linkName.getAttribute("href").equals(""))
					System.out.println("\nMissing link list : " +linkName.getText());
			}
			catch(NullPointerException ne) {
				System.out.println("\nhref Missing link list :" +linkName.getText());
			}
		}
		driver.close();
	}

	public static void main(String[] args) {
		new MissingLinksFormAutomate().findMissingLinkAndCount();
	}
}
