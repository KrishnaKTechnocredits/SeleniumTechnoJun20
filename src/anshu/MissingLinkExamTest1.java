package anshu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MissingLinkExamTest1 {
	
	void verifyTitleOfLink(WebDriver driver) {
		//1. Verify Title of all link;
		int rowSize = driver.findElements(By.xpath("//table/tbody/tr")).size();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
		for(int index=1;index<=rowSize;index++) {
			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[2]")).getText();
			WebElement linkText = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[3]/a"));
						

			if(linkText.getAttribute("href").equals("")) {
				String empname = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[1]")).getText();
				System.out.println(" Missing link found in "+ companyName + " of employee "+empname );
			}
			else {
				String getTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index +"]/td[4]")).getText();
				linkText.click();				
				if(!driver.getTitle().equals(getTitle))
					System.out.println("TestFail: Title is not matched for "+ companyName);
				else
					System.out.println("TestPass: Title is  matched for "+ companyName);
				driver.navigate().back();
			}
		}
	  }
	
 public static void main(String[] args) {
	 System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("file:///E:/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		System.out.println("HtmlPage Loaded");

		MissingLinkExamTest1 missingLink = new MissingLinkExamTest1();
		missingLink.verifyTitleOfLink(driver);
		driver.close();

}
}
