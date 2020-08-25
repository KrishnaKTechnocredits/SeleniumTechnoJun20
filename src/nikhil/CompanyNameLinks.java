package nikhil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import nikhil.base.PreDefinedActions;

public class CompanyNameLinks extends PreDefinedActions {
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}
	
	void tearDown() {
		driver.close();
	}
	
	void linkActions() throws InterruptedException {
		int maxRows = driver.findElements(By.xpath("//table/tbody/tr")).size();
		for(int index = 1; index <= maxRows; index++) {
			WebElement link = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[3]"));
			String companyName = link.getText();
			if(driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[3]/a")).getAttribute("href").length()==0) {
				System.out.println("Missing link : "+companyName+" ---> User Name : "+driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[1]")).getText()+"  Company Name : "+driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[2]")).getText());
				System.out.println();
				break;
			}
			String expectedPageTitle = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[4]")).getText();
			String url = driver.findElement(By.xpath("//table/tbody/tr["+index+"]/td[3]/a")).getAttribute("href");
			driver.get(url);
			Thread.sleep(1500);
			String pageTitle = driver.getTitle();
			
			if(!expectedPageTitle.equals(pageTitle)) {
				System.out.println("Page Title does not match for : "+companyName);
			}
			driver.navigate().back();
			Thread.sleep(500);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CompanyNameLinks companyNameLinks = new CompanyNameLinks();
		companyNameLinks.setUp("C:\\Users\\Nikhil.Nikhil-Universe.000\\Documents\\TechnoCredits\\TechnoGitProject\\SeleniumTechnoJun20\\resources\\forms\\Exam-1.html");
		companyNameLinks.linkActions();
		companyNameLinks.tearDown();
	}
}
