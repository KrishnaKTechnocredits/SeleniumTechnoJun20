package jagdeesh;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class IframeDemo {
	
	private WebDriver driver;
	//void setUp() {
		
	//	driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	//}
	void iframeEx(WebDriver driver) {
		this.setDriver(driver);
		driver.manage().timeouts().implicitlyWait(1500,TimeUnit.MILLISECONDS);
		driver.findElement(By.linkText("IFrame Demo")).click();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//a[text()='MENU +']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.switchTo().parentFrame();
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//ul/li[2]/a[text()='License']")).click();
		driver.switchTo().parentFrame();
		driver.findElement(By.xpath("//a[text()='Automation By Krishna']")).click();
		String actualText= driver.findElement(By.linkText("Automation By Krishna")).getText();
		System.out.println("Text on the page is --> "+actualText);
		
	}
	public static void main(String[] args) {
		IframeDemo iframeDemo = new IframeDemo();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");	
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.manage().window().maximize();
		//ex1.setUp();
		iframeDemo.iframeEx(driver);
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
