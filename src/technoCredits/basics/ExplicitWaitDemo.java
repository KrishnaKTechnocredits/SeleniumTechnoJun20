package technoCredits.basics;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import technoCredits.basics.base.PredefinedActions;

public class ExplicitWaitDemo extends PredefinedActions {
	WebDriver driver;
	private void setUp() {
		driver = start("http://automationbykrishna.com");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	void doLogin() throws InterruptedException {
		driver.findElement(By.linkText("Registration")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 15); // 500 ms
		WebElement e1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='unameSignin']")));
		
		e1.sendKeys("mkanani"); // 250 + 250+ 250
		driver.findElement(By.xpath("//input[@id='pwdSignin']")).sendKeys("m123fdgdfgddffd"); // 250
		driver.findElement(By.xpath("//button[@id='btnsubmitdetails']")).click(); // 250
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		alert.accept();
		//driver.findElement(By.xpath("//input[@id='unameSignin']")).sendKeys("hvegada");
		System.out.println(actualText);
		
		driver.findElement(By.linkText("Demo Tables")).click();
		wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//table[@id='table1']/tbody/tr"), 5));
		
		int totalRows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		System.out.println(totalRows);
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExplicitWaitDemo javaScriptAlertDemo = new ExplicitWaitDemo();
		javaScriptAlertDemo.setUp();
		javaScriptAlertDemo.doLogin();
		javaScriptAlertDemo.tearDown();
	}
	
	private void tearDown() {
		driver.close();
	}
}
