/*Assignment-8 Validate user name in Demo table*/
 package amita;
 
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import amita.base.PredefinedActions;

public class DemoTableUserNameValidation extends PredefinedActions {
	
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}
	
	void navigateDemoTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	void verifyUserName() {
		int rowSize = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for(int index=1; index<=rowSize; index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[2]")).getText();
			String lastName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[3]")).getText();
			String actualUserName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[4]")).getText();
			String expectedUserName = (firstName.charAt(0)+lastName).toLowerCase();
			
			if(actualUserName.equals(expectedUserName)) {
				System.out.println("TEST PASS : The Username "+expectedUserName+" is validated with First Name- "+firstName+" and Last Name- "+lastName);
			}else {
				System.out.println("TEST FAIL : The Username "+expectedUserName+" is not validated with First Name- "+firstName+" and Last Name- "+lastName);
			}		
		}
	}

	public static void main(String[] args) {
		DemoTableUserNameValidation usernameValidation = new DemoTableUserNameValidation();
		usernameValidation.setUp("http://automationbykrishna.com");
		usernameValidation.navigateDemoTable();
		usernameValidation.verifyUserName();
		usernameValidation.driver.close();
	}
}