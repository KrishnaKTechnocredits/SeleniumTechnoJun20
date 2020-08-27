package suparna.Assignment10;
/* automationbyKrishna-->BasicElemet--> send key value by property file --> check alert value and then accepy alert.*/
import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.WebDriverInfo;
import org.openqa.selenium.support.ui.WebDriverWait;
import suparna.basics.base.PredefineAction;
import suparna.utility.PopertyFileRead;

public class FeatchDataFromPropertyFile extends PredefineAction {

	public void alertDemo() throws IOException {
		// String firstName,lastName,companyName;
		driver.findElement(By.linkText("Basic Elements")).click();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement fName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserFirstName']")));
		WebElement lName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserLastName']")));
		WebElement companyName = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='UserCompanyName']")));
		PopertyFileRead fileReader = new PopertyFileRead(
				"D:\\Suparna_Automation\\TechnoGitProject\\SeleniumTechnoJun20\\src\\suparna\\config\\credentials.properties");
		// firstName = fileReader.getPropertyValue("firstName");
		fName.sendKeys(fileReader.getPropertyValue("firstName"));
		lName.sendKeys(fileReader.getPropertyValue("lastName"));
		companyName.sendKeys(fileReader.getPropertyValue("companyName"));
		driver.findElement(By.xpath("//div[4]/button[@class='btn btn-primary']")).click();
		Alert alert = driver.switchTo().alert();
		String expectedResult = alert.getText();
		String actualResult = fileReader.getPropertyValue("firstName") + " and "
				+ fileReader.getPropertyValue("lastName") + " and " + fileReader.getPropertyValue("companyName");
		if (expectedResult.equals(actualResult))
			System.out.println("String disalpay in Alert is correct : Test case pass ");
		else
			System.out.println("String disalpay in Alert is in correct : Test case fail ");
		alert.accept();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FeatchDataFromPropertyFile readFile = new FeatchDataFromPropertyFile();
		readFile.setDriver();
		readFile.alertDemo();
		readFile.tearDown();
	}

}
