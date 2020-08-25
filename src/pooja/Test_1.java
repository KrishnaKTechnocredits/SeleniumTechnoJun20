package pooja;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("file:///C:/Users/Omkar/Downloads/Microsoft.SkypeApp_kzf8qxf38zg5c!App/All/Exam-1.html");
		
		

	int row=driver.findElements(By.xpath("//tbody/tr")).size();

	for(int i=1;i<=row;i++)	
	{
		driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]")).click();
		Thread.sleep(2000);
	    String actualtext=driver.findElement(By.xpath("//tbody/tr["+i+"]/td[4]")).getText();
	                                                  
	 if(   driver.getTitle().equals(actualtext))
	 {
		 driver.navigate().back();
	 }
	 else
	 {
		driver.navigate().back();
		System.out.println(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText());
	 }
	
	}
	
	}

}
