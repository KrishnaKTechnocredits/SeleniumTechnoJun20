package technoCredits.basics;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class JavaScriptAlertDemo extends PredefinedActions {
	WebDriver driver;
	private void setUp() {
		driver = start("http://automationbykrishna.com");
	}
	
	void handleAlert() throws InterruptedException {
		driver.findElement(By.linkText("Registration")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys("mkanani");
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys("m123");
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click();
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		//driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys("hvegada");
		System.out.println(actualText);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JavaScriptAlertDemo javaScriptAlertDemo = new JavaScriptAlertDemo();
		javaScriptAlertDemo.setUp();
		javaScriptAlertDemo.handleAlert();
		javaScriptAlertDemo.tearDown();
	}
	
	private void tearDown() {
		driver.close();
	}
}
