package raj;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import raj.selenium.base.PredefinedActions;

public class SeleniumExam1 extends PredefinedActions {
	WebDriver driver;
	void setUp(String url) {
		driver = start(url);
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	}

	void linksValidation() {
		int totalRowsInTable = driver.findElements(By.xpath("//table/tbody/tr")).size();

		for(int index=1;index<=totalRowsInTable;index++) {
			WebElement linkElement = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[3]/a"));
			String companyName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[2]")).getText();			

			if(linkElement.getAttribute("href").equals("")) {
				String empName = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[1]")).getText();
				System.out.println("Link is missing for Employee " +empName + " of Company "+ companyName );
			}
			else {
				String tableTitle = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[4]")).getText();
				linkElement.click();	

				if(!driver.getTitle().equals(tableTitle))
					System.out.println(companyName + " company title matching failed...!!");
				else
					System.out.println(companyName + " company title matching passed");
				driver.navigate().back();
			}
		}
	}

	public static void main(String[] args) {
		SeleniumExam1 exam1 = new SeleniumExam1();
		exam1.setUp("file:///G:/Technocredits/Projects/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		exam1.linksValidation();
	}
}
