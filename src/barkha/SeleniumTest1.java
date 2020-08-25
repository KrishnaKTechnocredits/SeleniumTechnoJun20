package barkha;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import barkha_base.PredefinedFunctions;

public class SeleniumTest1 extends PredefinedFunctions {
	
	WebDriver driver;
	
	void getURL(String URL) {
		driver=start("D:\\TechnoGitProjectSelenium\\SeleniumTechnoJun20\\resources\\forms\\Exam-1.html");
	}
	
	void checkTitle() throws InterruptedException {
		int countRow=driver.findElements(By.xpath("//table/tbody/tr/td[3]")).size();
		
		for (int index=1; index<=countRow; index++) {
			WebElement link=driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[3]/a"));
			String comName=driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[2]")).getText();
			
			if (link.getAttribute("href").equals("")) {
				System.out.println(comName+" company has missing link");
			}
			else {
				String tableTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[4]")).getText();
				link.click();
				Thread.sleep(2000);
				if (driver.getTitle().equals(tableTitle)) {
					System.out.println("Title is matchning for company "+comName);
				}
				else
					System.out.println("Title is not matchning for company "+comName);
			}
			driver.navigate().back();
			Thread.sleep(2000);
		}
	}
	void tearBreak() {
		driver.close();
	}	
	
	public static void main(String[] args) throws InterruptedException {
		SeleniumTest1 test=new SeleniumTest1();
		test.getURL("D:\\TechnoGitProjectSelenium\\SeleniumTechnoJun20\\resources\\forms\\Exam-1.html");
		test.checkTitle();
		test.tearBreak();
	}
}