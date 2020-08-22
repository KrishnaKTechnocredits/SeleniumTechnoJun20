package pooja;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class Assignment_5 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[1]")));
		
		//print total no of options
		WebElement element=driver.findElement(By.xpath("//select[1]"));
		Select sel=new Select(element);
	 List <WebElement> ele=sel.getOptions();
	 System.out.println(ele.size());
	 
	 for(WebElement ele1:ele)
	 {
		 if(Integer.parseInt(ele1.getText()) % 2 == 0)
		 {
			 sel.selectByVisibleText(ele1.getText());
			 System.out.println("selected  "+ele1.getText());
		 }
		 else
		 {
			 System.out.println("deselected  "+ele1.getText());
		 }
			 
		 
	 }
	 //deselect all
	WebElement ele2 =driver.findElement(By.xpath("//select[2]"));
	 Select sel1=new Select(ele2);
	 sel1.deselectAll();
	
	 //verify selected zero 
	System.out.println( sel1.getAllSelectedOptions().size());
	   
	}

}
