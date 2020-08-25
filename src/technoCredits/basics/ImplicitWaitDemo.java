package technoCredits.basics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class ImplicitWaitDemo extends PredefinedActions {
	WebDriver driver;
	private void setUp() {
		driver = start("http://automationbykrishna.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS); // 15 secs // 250 ms
	}
	
	void doLogin() throws InterruptedException {
		driver.findElement(By.linkText("Registration")).click();
		driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys("mkanani"); // 250 + 250+ 250
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys("m123fdgdfgddffd"); // 250
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click(); // 250
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		//driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys("hvegada");
		System.out.println(actualText);
	}
	
	public static void main(String[] args) throws InterruptedException {
		ImplicitWaitDemo javaScriptAlertDemo = new ImplicitWaitDemo();
		javaScriptAlertDemo.setUp();
		javaScriptAlertDemo.doLogin();
		javaScriptAlertDemo.tearDown();
	}
	
	private void tearDown() {
		driver.close();
	}
}
