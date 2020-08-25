package archana;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAss4 {
	static WebDriver driver;

	void launchBrowser(String url) {
		String osName = System.getProperty("os.name").toLowerCase();
		String path = osName.contains("windows") ? "./resources/windows/chromedriver.exe"
				: osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	List<WebElement> totalLinks() {
		launchBrowser("file:///E:/JAVA_class/TechnoGitProject/SeleniumProject/resources/forms/MissingLink.html");
		List<WebElement> listOfLink = driver.findElements(By.xpath("//a"));
		System.out.println(listOfLink.size() + " links on the page");
		System.out.println("All Links are->");
		for (WebElement links : listOfLink)
			System.out.println(links.getText());
		System.out.println("************");
		return listOfLink;
	}

	void missingLink(List<WebElement> listOfLink) {
		for (WebElement links : listOfLink) {
			try {
				if (links.getAttribute("href").equals(""))
					System.out.println("Missing Link ->" + links.getText());
			} catch (NullPointerException n) {
				System.out.println("Missing Attribute->" + links.getText());
			} finally {
				driver.quit();
			}
		}
	}

	public static void main(String[] args) {
		SeleniumAss4 assignment4 = new SeleniumAss4();
		List<WebElement> listOfLink = assignment4.totalLinks();
		assignment4.missingLink(listOfLink);

	}
}