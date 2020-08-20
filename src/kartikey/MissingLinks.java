package kartikey;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MissingLinks {
	private static WebDriver start(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? ".//resources//windows//chromedriver.exe"
				: os.contains("mac") ? ".//resources//mac//chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		return driver;
	}

	void findmissingLinks(WebDriver driver) {
		// List<WebElement> lostOfLinks = driver.findElements(By.xpath("//a"));
		// List<WebElement> lostOfLinks = driver.findElements(By.xpath("html/body/a"));
		List<WebElement> lostOfLinks = driver.findElements(By.xpath("html//a"));

		System.out.println("Total links on page: " + lostOfLinks.size());

		// using iterator
		Iterator<WebElement> itr = lostOfLinks.iterator();
		while (itr.hasNext()) {
			WebElement element = itr.next();
			System.out.println(element.getText());
		}

		// ehnanced for loop
		for (WebElement element : lostOfLinks) {
			try {
				if (element.getAttribute("href").equals(""))
					System.out.println("Link having no value of href :" + element.getText());
			} catch (NullPointerException e) {
				System.out.println("Element not having href Attribute :" + element.getText());
			}
		}
	}

	void end(WebDriver driver) {
		driver.close();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provie the url to proceed");
		String url = scanner.next();
		MissingLinks missingLinks = new MissingLinks();
		WebDriver driver = MissingLinks.start(url);
		missingLinks.findmissingLinks(driver);
		missingLinks.end(driver);

	}
}
