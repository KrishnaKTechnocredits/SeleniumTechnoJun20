package viresh.assignment7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UserNameValidation {
	WebDriver driver;

	void setUp() {
		System.setProperty("webdriver.chrome.driver", ".//resources/windows/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
	}

	void tearDown() {
		driver.close();
	}

	void validateUname() {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		int rowCount = driver.findElements(By.xpath("//table[@id= 'table1']/tbody/tr")).size();
		List<Integer> passedTC = new ArrayList<>();
		List<Integer> failedTC = new ArrayList<>();
		for (int index = 1; index <= rowCount; index++) {
			String fName = driver.findElement(By.xpath("//table[@id= 'table1']/tbody/tr[" + index + "]/td[2]"))
					.getText();
			String lName = driver.findElement(By.xpath("//table[@id= 'table1']/tbody/tr[" + index + "]/td[3]"))
					.getText();
			String uName = (fName.charAt(0) + lName).toLowerCase();
			if (driver.findElement(By.xpath("//table[@id= 'table1']/tbody/tr[" + index + "]/td[4]")).getText()
					.equals(uName)) {
				// System.out.println("Row " + index + " has a valid user name.");
				passedTC.add(index);
			} else {
				// System.out.println("Row " + index + " has a invalid user name.");
				failedTC.add(index);
			}
		}
		System.out.println("The Rows with valid userName: " + passedTC);
		System.out.println("The rows with invalid userName: " + failedTC);
	}

	public static void main(String[] args) {
		UserNameValidation unVal = new UserNameValidation();
		unVal.setUp();
		unVal.validateUname();
		unVal.tearDown();
	}
}
