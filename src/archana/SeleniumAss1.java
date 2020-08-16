package archana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAss1 {
	static WebDriver driver;

	void launchBrowser() {
		// Launch chrome with form
		System.setProperty("webdriver.chrome.driver", "E:\\JAVA_class\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("file:///E:/JAVA_class/Selenium/Ass1/SeleniumAssignment_1.html");
		// verify Title
		String title = driver.getTitle();
		if (title.equals("TECHNOCREDITS"))
			System.out.println("Correct page loaded");
		else
			System.out.println("Incorrect page loaded");
	}

	void fillAllDetails() {
		driver.findElement(By.id("first name")).sendKeys("Archana");
		driver.findElement(By.id("last name")).sendKeys("Nawale");
		driver.findElement(By.id("E-mail")).sendKeys("A.N@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Techno");
		if (driver.findElement(By.id("maleG")).isSelected())
			driver.findElement(By.id("femaleG")).click();
		else
			driver.findElement(By.id("maleG")).click();
		driver.findElement(By.id("fresher")).click();
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("python")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();
	}

	void resetForm() {
		driver.findElement(By.id("resetBtn")).click();
		if (driver.findElement(By.id("first name")).getAttribute("value").equals("")
				&& driver.findElement(By.id("fresher")).isSelected() == false
				&& driver.findElement(By.id("java")).isSelected() == false) {
			System.out.println("Reset Sucessfully");
		}
	}

	void verifyMorePracticeLink() {
		driver.findElement(By.id("morePractice")).click();
		String newpageTitle = driver.getTitle();
		String url = driver.getCurrentUrl();
		if (newpageTitle.equals("Login Signup Demo") && url.equals("http://automationbykrishna.com/"))
			System.out.println("Correct redirected webpage loaded");
		else
			System.out.println("Incorrect rediredted webpage loaded");
	}

	public static void main(String[] args) {
		SeleniumAss1 assignment1 = new SeleniumAss1();
		assignment1.launchBrowser();
		assignment1.fillAllDetails();
		assignment1.resetForm();
		assignment1.verifyMorePracticeLink();
		driver.close();
	}

}
