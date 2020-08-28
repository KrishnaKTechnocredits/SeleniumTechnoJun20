package pooja;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment_8 {
	void userIdValidation(){
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.findElement(By.id("demotable")).click();

		WebDriverWait wait = new WebDriverWait(driver, 15); // 500 ms
		WebElement e1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id]//tr")));
		int row=driver.findElements(By.xpath("//table[@id]//tr")).size();
		for(int i=1;i<row;i++)
		{   String actual=driver.findElement(By.xpath("//table[@id]//tr["+i+"]/td[4]")).getText();
			String fname=driver.findElement(By.xpath("//table[@id]//tr["+i+"]/td[2]")).getText();
			String lname=driver.findElement(By.xpath("//table[@id]//tr["+i+"]/td[3]")).getText();
			String expectedUsername=fname.charAt(0)+lname;
			String expected=expectedUsername.toLowerCase();
			if(expected.equals(actual))
				System.out.println("test pass for  "+fname);
			else {
				System.out.println("test fail for  "+fname);
				
			}
		}
	}

	public static void main(String[] args) {
		
		Assignment_8 assignment_8=new Assignment_8();
		assignment_8.userIdValidation();;
	}

}
