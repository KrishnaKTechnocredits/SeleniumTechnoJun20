package anup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import anup.basics.PredefinedActions;

public class TableTextExam1 extends PredefinedActions {
	WebDriver driver;

	void setup() {
		driver = start();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
	}

	void tableValidation() throws InterruptedException {
		int tablerow = driver.findElements(By.xpath("//table/tbody/tr")).size();
		for (int index = 1; index <= tablerow; index++) {
			String expectedPageTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
			String company = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();
			String name = driver.findElement(By.xpath("//tbody/tr[" + index + "]/td[1]")).getText();
			WebElement element = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"));
			if (!element.getAttribute("href").equals("")) {
				element.click();
				String actualpageTitle = driver.getTitle();
				driver.navigate().back();
				if (!expectedPageTitle.equals(actualpageTitle)) {
					System.out.println("The name of the Company with wrong page Title is : " + company);
				}
			} else {
				System.out.println("The details of no url are : " + "Name " + name + "Company :" + company);
			}
		}

	}

	void sessionClose() {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		TableTextExam1 tableTextExam1 = new TableTextExam1();
		tableTextExam1.setup();
		tableTextExam1.tableValidation();
		tableTextExam1.sessionClose();
	}
}
