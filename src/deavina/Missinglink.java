package deavina;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Missinglink {

	private static WebDriver start(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	void getmissinglink(WebDriver driver) {
		List<WebElement> list = driver.findElements(By.tagName("a"));
		System.out.println("Total links on web page :" + list.size());
		for (int index = 0; index < list.size(); index++) {
			String linktext = list.get(index).getText();

			if (driver.findElement(By.linkText(linktext)).getAttribute("href") == null)
				System.out.println("Href Attribute is missing for Link" + index + " : "
						+ driver.findElement(By.linkText(linktext)).getText());
			else if (driver.findElement(By.linkText(linktext)).getAttribute("href").equals(""))
				System.out.println("Missing Link :- " + driver.findElement(By.linkText(linktext)).getText());

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = start("file:///C:/Users/sunita/Desktop/Technocredits%20Jun2020/selenium/MissingLink.html");
		Missinglink missinglink = new Missinglink();
		missinglink.getmissinglink(driver);
		driver.quit();
	}

}
