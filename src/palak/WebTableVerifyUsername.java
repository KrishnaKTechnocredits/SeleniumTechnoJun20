package palak;

//Verify Username is correct or not
import palak.base.PredefinedActions;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebTableVerifyUsername extends PredefinedActions {
	WebDriver driver;
	void setUp(String url) {
		driver = start(url);
	}
	
	void navigateDemoTable() {
		driver.findElement(By.linkText("Demo Tables")).click();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}
	
	void verifyUserNameByFirstAndLastName() {
		Set<String> UnameSet = new LinkedHashSet<String>();
		boolean flag = true;
		int rowSize = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for (int index = 1; index <= rowSize; index++) {
			String firstName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[2]")).getText();
			String lastName  = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[3]")).getText();
			
			String ExpectedUserName = firstName.charAt(0) + lastName;
			ExpectedUserName = ExpectedUserName.toLowerCase();
			
			String actualUserName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr["+index+"]/td[4]")).getText();
			if (actualUserName.equals(ExpectedUserName)) {
				UnameSet.add(ExpectedUserName);
			}else {
				flag = false;
				break;
			}
		}
		if (flag == true) {
			System.out.println("Test Pass all Usernames are : " + UnameSet);
		}else
			System.out.println("Test Fail Username doesn't Match");
	}

	public static void main(String[] args) {
		WebTableVerifyUsername verifyUsername = new WebTableVerifyUsername();
		verifyUsername.setUp("http://automationbykrishna.com/");
		verifyUsername.navigateDemoTable();
		verifyUsername.verifyUserNameByFirstAndLastName();
	}
}

