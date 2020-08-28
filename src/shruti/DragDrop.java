package shruti;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import shruti.predefinedActionspkg.PtrdefinedActions;

public class DragDrop extends PtrdefinedActions{
	
	WebDriver driver;
	void setUp(){
		driver=start("https://demos.telerik.com/kendo-ui/dragdrop/index");
	}
	
	void dragAction(){
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//	driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		WebElement src = driver.findElement(By.id("draggable"));
		WebElement des = driver.findElement(By.id("droptarget"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(src,des).build().perform();
		
		//String textBeforeDrag = driver.findElement(By.xpath("//div[text()='Drag the small circle here.']")).getText();
		String textAfterDrag = driver.findElement(By.xpath("//div[@id='droptarget']")).getText();
		
		if(textAfterDrag.equals("textAfterDrag"))
			System.out.println("Test Passed");
		else{
			System.out.println("Test failed");
		}
	}
	
	public static void main(String[] args) {
		DragDrop dragDrop = new DragDrop();
		dragDrop.setUp();
		dragDrop.dragAction();
		
		
	}

}
