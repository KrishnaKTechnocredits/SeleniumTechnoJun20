package mahesh;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.ArrayList;

public class LinksTest {
	
	void missingLinks(WebDriver driver) {
		driver.get(
				"file:///D:/Technocredit/Project/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		ArrayList<String> missingLinks = new ArrayList<String>();
		ArrayList<String> missinghref = new ArrayList<String>();
		if (driver.findElement(By.xpath("//p")).getText().equals("Missing Link")) {
			System.out.println("Missing Link webpage loaded successfully");
			List<WebElement> links = driver.findElements(By.xpath("//a"));
			System.out.println("Total number of links on Webpage: " + links.size());
			System.out.println("Name of all the links in Webpage are below:");
			for (WebElement element : links) {
				System.out.println(element.getText());
				if (element.getAttribute("href") == null) {
					missinghref.add(element.getText());
				} else if (element.getAttribute("href").equals("")) {
					missingLinks.add(element.getText());
				}
			}
			System.out.println("Missing Links are: " + missingLinks);
			System.out.println("Links with missing href attributes are: " + missinghref);
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		LinksTest linksTest = new LinksTest();
		linksTest.missingLinks(driver);
		driver.close();
	}
}
