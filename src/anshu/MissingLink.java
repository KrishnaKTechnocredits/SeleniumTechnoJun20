package anshu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MissingLink {

	void printMissingLink(WebDriver driver) {
		int count =1;
		// 1 . Find total number of links.
		List<WebElement> linklist = driver.findElements(By.xpath("/html/body/a"));
		System.out.println("--> Total Link available : " + linklist.size());

		// 2 .Print text of all links.
		System.out.println('\n' + "--> Text of all Link :-");
		for (WebElement element : linklist) {
			System.out.println( count++ +":" + element.getText());
		}
		printMisssingTextAndhrefAttribute(linklist);
	}

	private void printMisssingTextAndhrefAttribute(List<WebElement> linklist) {
		for (WebElement element : linklist) {
			try {
				// 3 . print missing text of missing link
				if (element.getAttribute("href").equals("")) {
					System.out.println('\n' + "--> Missing href text in given link is : " + element.getText());
				}
			} catch (NullPointerException ne) {
				// 4 . Miising href attribute
				System.out.println('\n' + "--> Missing href attribute in given link is : " + element.getText());
			}

		}
	}
	@Test
	public void main() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("file:///E:/SeleniumTechnoJun20/resources/forms/MissingLink.html");

		MissingLink missingLink = new MissingLink();
		missingLink.printMissingLink(driver);

		driver.close();
	}
}
