package jagdeesh;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserNameValidation {
	WebDriver driver;
	void navigateToDemoTables(WebDriver driver){
		this.driver = driver;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='table1']/tbody/tr")));
		int rowCount=driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for(int index=1;index<rowCount;index++) {
			String actualUserName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[4]")).getText();
			String expectedUsrName = (driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[2]")).getText().charAt(0))+
					(driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[3]")).getText());
			expectedUsrName = expectedUsrName.toLowerCase();
			if(actualUserName.equals(expectedUsrName))
				System.out.println("Expeced UserName --> "+expectedUsrName+" and Actual UserName --> "+actualUserName +" is same");
		}
	}

	public static void main(String[] agrs) throws IOException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.manage().window().maximize();
		new UserNameValidation().navigateToDemoTables(driver);
		driver.close();
		
	}
}