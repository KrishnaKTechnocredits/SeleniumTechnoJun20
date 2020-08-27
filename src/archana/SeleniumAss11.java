package archana;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumAss11 extends PredefinedActions {
	void handleIframes() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='iframes']"))).click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='http://www.seleniumhq.org']")));
		driver.switchTo().frame("site1");
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		driver.findElement(By.xpath("//a[@href='/projects']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Selenium Projects']")));
		System.out.println(driver.findElement(By.xpath("//h1[text()='Selenium Projects']")).getText());
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@src='http://ant.apache.org/']")));
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//a[@href='https://www.apache.org/licenses/']")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Automation By Krishna")).click();
		System.out.println(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='indexBody']")))
				.getText());
	}

	public static void main(String[] args) {
		driver = start("http://automationbykrishna.com");
		SeleniumAss11 assignment11 = new SeleniumAss11();
		assignment11.handleIframes();
		driver.quit();
	}
}