package ajit;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLinks {

	WebDriver start() {
		System.setProperty("webdriver.chrome.driver", ".//resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	void operationsOnMissingLinks() {
		WebDriver driver = start();
		driver.get("D:/Users/asing480/Downloads/MissingLink.html");
		driver.manage().window().maximize();
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(
				"Total " + links.size() + " links are available on the Page\n===================================");
		System.out.println("Text of All Links on the Page :");
		int index = 1;
		for (WebElement element : links) {
			System.out.println("Link" + index + " : " + element.getText());
			if (element.getAttribute("href") == null)
				System.out.println("Href Attribute is missing for Link" + index + " : " + element.getText());
			else if (element.getAttribute("href").equals(""))
				System.out.println("Missing Link : " + element.getText());
			index++;
		}
	}

	public static void main(String[] args) {
		new BrokenLinks().operationsOnMissingLinks();

	}
}
