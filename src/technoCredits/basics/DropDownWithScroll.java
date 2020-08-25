package technoCredits.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import technoCredits.basics.base.PredefinedActions;

public class DropDownWithScroll extends PredefinedActions {
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}
	
	void selectOptionFromDropdown() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		Thread.sleep(3000);
		
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[1]")));
		
		WebElement element = driver.findElement(By.xpath("//select[1]"));
		Select oselect = new Select(element);
		List<WebElement> listOfOptions = oselect.getOptions();
		for(WebElement optionElement : listOfOptions){
			if(optionElement.getText().equals("3"))
				optionElement.click();
		}
		
		int count=0;
		for(WebElement optionElement : listOfOptions){
			if(optionElement.isSelected()) {
				optionElement.getText();
				count++;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		DropDownWithScroll ddws = new DropDownWithScroll();
		ddws.setUp("http://automationbykrishna.com");
		ddws.selectOptionFromDropdown();
		
	}
}
