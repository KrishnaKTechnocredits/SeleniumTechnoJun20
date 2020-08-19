package abhishek;
/*Verify below scenarios from SeleniumTechnoJun20 > resources > forms > MissingLink.html
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

public class Assignment_4 {

	public void Findmissinglink() {

		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		String ExepectedURL = "file:///Users/adityashivankar/Documents/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html";
		driver.get("file:///Users/adityashivankar/Documents/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");

		String currentURl = driver.getCurrentUrl();
		if (currentURl.equals(ExepectedURL))
			System.out.println("user is successfully hit the URL");
		else
			System.out.println("user is not successfully hit the URL");

		List<WebElement> linkList = driver.findElements(By.xpath("//a"));
		System.out.println("output: " + linkList.size() + " links on the page");

		System.out.println("List of all Links are ");
		for (WebElement webElement : linkList) {
			System.out.println(webElement.getText());
		}

		for (WebElement webElement : linkList) {
			try {
				if (webElement.getAttribute("href").equals("")) {
					System.out.println("Missing Link -> " + webElement.getText());
				}
			} catch (NullPointerException e) {
				System.out.println("href is missing -> " + webElement.getText());
			}

		}
		driver.close();
	}

	public static void main(String[] args) {
		new Assignment_4().Findmissinglink();

	}

}
