package jagdeesh;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class Assignment3 {
	void validateTitle(String expectedTitle, WebDriver driver) {
		if(driver.getTitle().equals(expectedTitle))
			System.out.println("Title of Facebook is as expected, titel is : "+driver.getTitle());
		else
			System.out.println("Title of Facebook is NOT as expected, titel is : "+driver.getTitle());
	}
	void enterNewAccountDetails(WebDriver driver) {
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Sretan");
		//driver.findElement(By.xpath("//input[@id='u_0_m']")).sendKeys("Sretan");
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Mangipudi");
		driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("9001122551iii");
		driver.findElement(By.xpath("//input[@data-type='password']")).sendKeys("techno@2020");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("25");
		new Select(driver.findElement(By.name("birthday_month"))).selectByValue("4");
		new Select(driver.findElement(By.id("year"))).selectByIndex(7);
		driver.findElement(By.xpath("//input[@id='u_0_5']")).click();
		//get Attributes of all the fields
		String fName = driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value");
		String lName = driver.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value");
		String mobileNumber = driver.findElement(By.xpath("//input[@name='reg_email__']")).getAttribute("value");
		String password = driver.findElement(By.xpath("//input[@data-type='password']")).getAttribute("value");
		String day = new Select(driver.findElement(By.id("day"))).getOptions().get(25).getText();
		String month = new Select(driver.findElement(By.name("birthday_month"))).getOptions().get(4).getText();
		String year = new Select(driver.findElement(By.id("year"))).getOptions().get(7).getText();
		if(fName.equals("Sretan") && lName.equals("Mangipudi") && mobileNumber.equals("9001122551iii") 
				&& password.equals("techno@2020") && day.equals("25") && month.equals("Apr") && year.equals("2014")) 
			System.out.println("New login details are filled successfully");
			else
			System.out.println("Details are not entered as expected");	
		driver.findElement(By.xpath("//button[@name='websubmit']")).click();	
		driver.close();
	}
	public static void main(String[] agrs) {
		Assignment3 assignment3 = new Assignment3();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/r.php");
		driver.manage().window().maximize();
		String expectedTitle = "Sign up for Facebook | Facebook";
		assignment3.validateTitle(expectedTitle, driver);
		assignment3.enterNewAccountDetails(driver);
	}
}
