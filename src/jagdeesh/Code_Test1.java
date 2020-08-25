package jagdeesh;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Code_Test1 {
	WebDriver driver;

	void verifyTitle(WebDriver driver) throws InterruptedException {
		this.driver = driver;

		int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();
		// To find missing link
		for (int index = 1; index <= rowCount; index++) {
			if (driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a")).getAttribute("href").equals(""))
				System.out.println("Missing link for company : "
						+ driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText()
						+ "and Name : "
						+ driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[1]")).getText());
		}
		System.out.println("Links with Title not as expected :");
		for (int index = 1; index <= rowCount; index++) {
			String Title = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
			WebElement companyLink = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"));
			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();
			driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
			if (!companyLink.getAttribute("href").equals("")) {
				companyLink.click();
				if (!driver.getTitle().equals(Title))
					System.out.println(companyName);
				driver.navigate().back();
			}
		}
	}

	public static void main(String[] agrs) throws InterruptedException {
		Code_Test1 codeTest = new Code_Test1();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///D:/techoncredit/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		codeTest.verifyTitle(driver);
		driver.close();
}
}
