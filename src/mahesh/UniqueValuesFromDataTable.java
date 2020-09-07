package mahesh;

import mahesh.base.PredefinedActions;
import org.testng.annotations.*;

import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UniqueValuesFromDataTable extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	void setUp() {
		driver = start("https://editor.datatables.net/examples/extensions/excel");
		wait = new WebDriverWait(driver, 20);
	}
	
	@AfterMethod
	void tearDown() {
		driver.close();
	}
	
	@Test
	void findUniquePositionAndLocation() {
		driver.manage().window().maximize();
		Set<String> uniqueOfficeLocation = new HashSet<String>();
		Set<String> uniquePosition = new HashSet<String>();
		boolean flag = true;
		while(flag == true) {
			int countOfRows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr")).size();
			for (int index=1;index<=countOfRows;index++) {
				uniquePosition.add(driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[4]")).getText());
				uniqueOfficeLocation.add(driver.findElement(By.xpath("//table[@id='example']/tbody/tr["+index+"]/td[5]")).getText());
			}
			if(driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("class").equals("paginate_button next"))
				driver.findElement(By.xpath("//a[@id='example_next']")).click();
			else
				flag=false;
		}
		System.out.println("Unique Office in Table: " + uniquePosition);
		System.out.println("Unique Office in Table: " + uniqueOfficeLocation);
	}
}
