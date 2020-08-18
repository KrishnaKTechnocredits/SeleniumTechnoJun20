package aavruti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookValidations {
	
	void fbPageValidations(WebDriver driver) throws InterruptedException{
		driver.manage().window().maximize();
		driver.navigate().to("http://www.facebook.com");
		
		System.out.println(driver.getTitle().equals("Facebook – log in or sign up") ? "Navigated to Facebook successfully" : "Not able to navigate to Facebook Page");		
		driver.findElement(By.id("email")).sendKeys("aavrutid@yahoo.com");
		driver.findElement(By.id("pass")).sendKeys("aavruti@01");
		driver.findElement(By.name("login")).click();
		
		Thread.sleep(1000);
		System.out.println(driver.getTitle().equals("Facebook") ? "Login Successfully" : "Login unsuccessful!!");
	}
	
	void navigateCommands(WebDriver driver) {
		driver.navigate().to("https://www.google.com/");
		System.out.println(driver.getCurrentUrl());
		
		driver.navigate().back();
		System.out.println(driver.getTitle().equals("Facebook") ? "Back/History Page successfully loaded" : "Unsuccessful to load Back/History Page");
		
		driver.navigate().forward();
		System.out.println(driver.getTitle().equals("Google") ? "Google Page successfully loaded" : "Unsuccessful to load Google Page");
		
		driver.navigate().refresh();
		System.out.println(driver.getTitle().equals("Google") ? "Refresh Successful" : "Refresh unsuccessful");
	}
	
	public static void main(String[] args) throws InterruptedException{
		String osName = System.getProperty("os.name").toLowerCase();
		String chromeDriverPath = osName.contains("windows") ? "./resources/windows/chromedriver.exe" : osName.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		WebDriver driver = new ChromeDriver(); 
		
		FacebookValidations facebookValidations = new FacebookValidations();
		facebookValidations.fbPageValidations(driver);
		facebookValidations.navigateCommands(driver);
		driver.close();
	}
}
