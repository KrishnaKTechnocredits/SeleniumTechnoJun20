package shruti;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class UniqueLoacationPosition extends PtrdefinedActions {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	void setUp() {
		driver = start();
	}

	@Test
	void uniqueOffices() {
		int rowSize = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]")).size();
		Set<String> officeSet = new HashSet<String>();
		Set<String> positionSet = new HashSet<String>();
		for(int outerindex=1; outerindex<=6; outerindex++){
		if(!driver.findElement(By.xpath("//a[text()='Next']")).getAttribute("class").contains("disabled")) {
			
			for (int index = 1; index <= rowSize; index++) {
				String office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index+ "]/td[5]")).getText();
				String position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index+ "]/td[4]")).getText();
				if (officeSet.add(office)) {
					System.out.println(office + " : office loaction is unique");
				}
				if(positionSet.add(position))
					System.out.println(position+" : position is unique");
			}
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}
		}
		
	}
}