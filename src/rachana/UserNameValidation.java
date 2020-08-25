package rachana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rachana.base.PredefinedActions;

public class UserNameValidation extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	
	void SetUp() {
		driver = start();
		wait = new WebDriverWait(driver, 15);
	}
	
	void navigateToMenu() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='demotable']")));
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
	}

	void tearDown() {
		driver.close();
	}

	void verifyUserName() {
		//WebDriverWait wait = new WebDriverWait(driver, 15); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table']/tbody")));
		int totalrows = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
		
		for(int index=1;index<=totalrows;index++) {
			String firstName = driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+index+"]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+index+"]/td[3]")).getText();
			String actualuserName = driver.findElement(By.xpath("//table[@class='table']/tbody/tr["+index+"]/td[4]")).getText();
			String expecteduserName = (firstName.charAt(0)+lastName).toLowerCase();
			if(actualuserName.equals(expecteduserName)) {
				System.out.println("Pass to verify username: "+actualuserName+" for Firstname: "+firstName+" and LastName: "+lastName);
			}else
				System.out.println("Failed to verify usrname: "+actualuserName+" for Firstname: "+firstName+" and Lastname: "+lastName);
		}
	}

	public static void main(String[] args) {
		UserNameValidation usernamevalidation = new UserNameValidation();
		usernamevalidation.SetUp();
		usernamevalidation.navigateToMenu();
		usernamevalidation.verifyUserName();
		usernamevalidation.tearDown();
	}

}
