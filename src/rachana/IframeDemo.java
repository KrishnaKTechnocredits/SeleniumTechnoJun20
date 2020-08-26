package rachana;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rachana.base.PredefinedActions;
import rachana.navigation.HomePageMenuNavigation;

public class IframeDemo extends PredefinedActions{
	WebDriver driver;
	WebDriverWait wait;

	void setUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='collapse navbar-collapse']")));
	}
	
	void tearDown() {
		driver.close();
	}
	
	void NavigateToMenu() {
		HomePageMenuNavigation menu = new HomePageMenuNavigation(driver);
		WebElement menuoption = menu.navigateToHomePageMenu("IFrame Demo");
		menuoption.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
	}
	
	void handleIframes() {
		//go to first Iframe and click on menu link, validate Selenium Projects
		driver.findElement(By.xpath("//iframe[1]")).click();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//a[@id='dropdownButton']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		String actualheading = "Selenium Projects";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@class='hero']/h1")));
		String expectedheading = driver.findElement(By.xpath("//section[@class='hero']/h1")).getText();
		if(actualheading.equals(expectedheading)) {
			System.out.println(actualheading+" displayed after clicking on Projects link under Menu dropdown :Pass");
		}else {
			System.out.println(actualheading+" not displayed after clicking on Projects link under Menu dropdown :Fail");
		}
		
		//go to license link in iframe 3
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='site3']")));
		driver.findElement(By.xpath("//div[@class='menu']//li/ul/li[2]/a[text()='License']")).click();
		
		//go to link on main page
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Automation By Krishna")).click();
		String actualtext ="This is Maulik.";
		String expectedtext = driver.findElement(By.xpath("//div[@id='indexBody']")).getText().trim();
		
		if(actualtext.equals(expectedtext)) {
			System.out.println("Navigated to correct Page http://automationbykrishna.com/index.html: Pass");
		}else {
			System.out.println("Naviagted to incorrect page :Fail");
		}
	}
	public static void main(String[] args) {
		IframeDemo iframedemo= new IframeDemo();
		iframedemo.setUp();
		iframedemo.NavigateToMenu();
		iframedemo.handleIframes();
		iframedemo.tearDown();
	}

}
