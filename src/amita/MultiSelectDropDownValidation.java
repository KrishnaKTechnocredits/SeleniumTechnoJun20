/*Selenium Assignment-5 :  20th Aug 2020
Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]*/
package amita;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import amita.base.PredefinedActions;

public class MultiSelectDropDownValidation extends PredefinedActions{
	
	WebDriver driver;
	
	void setUp(String url) {
		driver = start(url);
	}
	
	void selectOptionFromDropDown() throws InterruptedException {
		
		//AutomationByKrishna website -> Basic Elements -> MultiDropdown
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();
		driver.manage().window().maximize();
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[@class='form-control']")));
		WebElement element = driver.findElement(By.xpath("//select[@class='form-control']"));
		Select oselect = new Select(element);
		Thread.sleep(3000);
		
		//1) Print total number of options.
		List<WebElement> list = oselect.getOptions();
		System.out.println("Total number of options avaliable in Dropdown list are : "+list.size());
		
		//2) Select all even numbers.		
		System.out.println("\nAll selected Even number options are : ");
		for(WebElement elementList : list) {
			if (Integer.parseInt(elementList.getText()) % 2 == 0) {
				oselect.selectByVisibleText(elementList.getText());
				System.out.println(elementList.getText());
			}
		}	
		
		//3) Print all selected options.
		System.out.println("All selected options are :");
		List<WebElement> selectedList = oselect.getAllSelectedOptions();
		for(WebElement elementList : selectedList) {
			System.out.println(elementList.getText());
		}
		
		//4) Print all deselected options.
		System.out.println("All deselected options are : ");
		for(WebElement elementList : list) {
			if (Integer.parseInt(elementList.getText()) % 2 != 0) {
				oselect.selectByVisibleText(elementList.getText());
				System.out.println(elementList.getText());
			}
		}	
		
		//5) Using deselectAll() method, deselect all the options.
		oselect.deselectAll();
		
		//6) Now verify total number of selected options is zero
		if(oselect.getAllSelectedOptions().size() == 0) {
			System.out.println("All options are deselected ");
		}else {
			System.out.println("All optins are not deselected ");
		}
		driver.close();
	}
	public static void main(String[] args) {
		MultiSelectDropDownValidation multiselectdropdownvalidation = new MultiSelectDropDownValidation();
		multiselectdropdownvalidation.setUp("http://automationbykrishna.com/");
		try {
			multiselectdropdownvalidation.selectOptionFromDropDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
}
