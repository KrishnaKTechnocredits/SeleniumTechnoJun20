package pooja;

import java.sql.Driver;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Assignment_16 {
	
	WebDriver driver;
	
	void start() {
	System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://editor.datatables.net/examples/extensions/excel");
	driver.manage().window().maximize();
	
	}
	@Test
	void uniqueOffice() {
		start();
		Set set=new HashSet();
		int size=driver.findElements(By.xpath("//tr/td[5]")).size();
		for(int j=2;j<=6;j++) {
			
			for(int i=1;i<=size;i++) {
				set.add(driver.findElement(By.xpath("//tr["+i+"]/td[5]")).getText());
			
			}
			driver.findElement(By.xpath("//span/a[@data-dt-idx='"+j+"']")).click();
		}
		System.out.println(set);
		
	}
	@Test
	void uniquePosition() {
		start();
		Set set=new HashSet();
		int size=driver.findElements(By.xpath("//tr/td[4]")).size();
		for(int j=2;j<=6;j++) {
			
			for(int i=1;i<=size;i++) {
				set.add(driver.findElement(By.xpath("//tr["+i+"]/td[4]")).getText());
			
			}
			driver.findElement(By.xpath("//span/a[@data-dt-idx='"+j+"']")).click();
		}
		System.out.println(set);
		
		
	}
	
}
