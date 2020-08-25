package pranita;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pranita.basic.PredefinedFunctions;

public class Exam_1 extends PredefinedFunctions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}

	void linkOperations() {
		driver.get("file:///E:/Technocredits/TechnoGitProject/SeleniumPranitaTechno20/resources/forms/Exam-1.html");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		int totalTableRows = driver.findElements(By.xpath("//table/tbody/tr")).size();
		
		for (int index = 1; index <= totalTableRows; index++) {
			WebElement linkName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[3]/a"));
			String companyName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[2]")).getText();

			if (linkName.getAttribute("href").equals("")) {
				String empName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[1]")).getText();
				System.out.println("Link is missing for company "+ companyName +" whose employee is "+empName);
			} else {
				String tableTitle = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[4]")).getText();
				linkName.click();
				if (!driver.getTitle().equals(tableTitle))
					System.out.println(companyName + " company title is not displayed as expected.");
				else
					System.out.println(companyName + " company title is displayed as expected.");
				driver.navigate().back();
			}
		}
		driver.close();
	}

	public static void main(String[] args) {
		Exam_1 exam_1 = new Exam_1();
		exam_1.setUp("http://automationbykrishna.com/");
		exam_1.linkOperations();

	}
}