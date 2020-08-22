package pooja;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment_4 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("file:///F:/Techno/MissingLink.html");
		
		//find no of links
	   List<WebElement> ele=	driver.findElements(By.xpath("//a"));
	   System.out.println(ele.size());
	   
	   //print text of all links
	   for(WebElement element:ele)
	   {
		   System.out.println(element.getText());
	   }
	   
	   //print text of missing links
	   List<WebElement> ele2=	driver.findElements(By.xpath("//a[@href]"));
	   ele.removeAll(ele2);
	   for(WebElement element:ele)
	   {
		   System.out.println("missing link  "+element.getText());
	   }
	  
	   

	}

}
