package anshu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ValidateUsernameInWebTable {
	void navigateDemoTables(WebDriver driver) {
		driver.findElement(By.linkText("Demo Tables")).click();
	}

	void tearDown(WebDriver driver) {
		driver.close();
	}

	void validateUserName(WebDriver driver) {
		int rowSize = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for (int index = 1; index <= rowSize; index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[2]"))
					.getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[3]"))
					.getText();
			String actualUserName = (firstName.charAt(0) + lastName).toLowerCase();
			String ExpectedUserName = driver
					.findElement(By.xpath("//table[@id='table1']//tbody/tr[" + index + "]/td[4]")).getText();

			if (!actualUserName.equals(ExpectedUserName)) {
				System.out.println("Test Fail: UserName is not matched with expected username for user" + firstName
						+ " " + lastName);

			} else
				System.out.println("Test Fail: UserName is not matched with expected username for user" + firstName
						+ " " + lastName);
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://automationbykrishna.com");
		System.out.println("AutomationByKrishna page is loaded.");

		ValidateUsernameInWebTable validateUsername = new ValidateUsernameInWebTable();
		validateUsername.navigateDemoTables(driver);
		validateUsername.validateUserName(driver);
		validateUsername.tearDown(driver);
		
	}
}
