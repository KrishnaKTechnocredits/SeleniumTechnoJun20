package deavina;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment1 {

	public static void main(String[] args) throws InterruptedException {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.get("file:///C:/Users/sunita/Desktop/Technocredits%20Jun2020/SeleniumAssignment_1.html");

		String title = driver.getTitle();
		if (title.equals("TECHNOCREDITS"))
			System.out.println("Title of the page is TECHNOCREDITS");

		driver.findElement(By.id("first name")).sendKeys("Deavina");
		driver.findElement(By.id("last name")).sendKeys("Sharma");
		driver.findElement(By.id("E-mail")).sendKeys("deavina.sharma@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Accenture");

		driver.findElement(By.id("G1")).click();
		driver.findElement(By.id("G2")).click();
		driver.findElement(By.id("G1")).click();

		Select dropdown = new Select(driver.findElement(By.id("continents")));
		dropdown.selectByVisibleText("Africa");
		dropdown.selectByVisibleText("Australia");

		driver.findElement(By.id("exp2")).click();
		driver.findElement(By.id("exp4")).click();

		driver.findElement(By.id("p1")).sendKeys("Java");
		
		driver.findElement(By.id("l1")).click();
		driver.findElement(By.id("l3")).click();

		driver.findElement(By.id("c1")).click();
		driver.findElement(By.id("c3")).click();

		driver.findElement(By.id("checkbox")).click();
		
		driver.findElement(By.id("res1")).click();

		driver.findElement(By.linkText("Go And Practice For it")).click();
		if ((driver.getTitle().equals("Login Signup Demo"))
				&& (driver.getCurrentUrl().equals("http://automationbykrishna.com/")))
					System.out.println("Page has been Successfully redirected");

		Thread.sleep(3000);
		driver.close();
	}
}
