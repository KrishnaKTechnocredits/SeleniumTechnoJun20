package nikhil;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import nikhil.base.PreDefinedActions;

public class ScrollMultiSelect extends PreDefinedActions {
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}
	
	void optionOps() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		Thread.sleep(500);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[@class='form-control']")));
		
		WebElement element = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select oSelect = new Select(element);
		List<WebElement> optionList = oSelect.getOptions();
		
		//printing all available options
		System.out.println("All available option in the multi-select dropdown:-");
		for(WebElement option : optionList) {
			System.out.print(option.getText()+" ");
		}
		
		//Selecting and printing all the even numbered options
		System.out.println("\n\nSelecting all even numbered options...");
		for(WebElement option : optionList) {
			if(Integer.parseInt(option.getText())%2 == 0) {
				oSelect.selectByVisibleText(option.getText());
			}
		}
		
		//Printing selected even numbered options
		System.out.println("\nSelected even numbered options:-");
		for(WebElement option : optionList) {
			if(option.isSelected()) {
				System.out.print(option.getText()+" ");
			}
		}
		
		//Printing non-selected options
		System.out.println("\n\nNon-Selected options:-");
		for(WebElement option : optionList) {
			if(!option.isSelected()) {
				System.out.print(option.getText()+" ");
			}
		}
		
		//Deselecting all the options
		System.out.println("\n\nDeselecting all the options...");
		oSelect.deselectAll();
		
		//Verifying if the options are really deselected
		if((oSelect.getAllSelectedOptions()).size() == 0) {
			System.out.println("\nAll oprions are deselcted successfully!!!");
		}else {
			System.out.println("\nOprions Deselction Failure!!!");
		}
		driver.close();
	}
	
	public static void main(String[] args) throws InterruptedException {
		ScrollMultiSelect scrollMultiSelect = new ScrollMultiSelect();
		scrollMultiSelect.setUp("http://automationbykrishna.com/");
		scrollMultiSelect.optionOps();
	}
}
