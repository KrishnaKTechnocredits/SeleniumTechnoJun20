package aavruti;

import aavruti.base.PredefinedActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MultiDropDown extends PredefinedActions{

	WebDriver driver;

	void setupDriver(String url) {
		driver = start(url);
	}

	//Navigate to Basic Element and Scroll Down to select multi drop-down
	void navigateToBasicElement() {
		if(driver.getTitle().equals("Login Signup Demo")) {
			driver.findElement(By.xpath("//div[@id='bs-example-navbar-collapse-1']//ul/li[4]//a[@id='basicelements']")).click();

			try{
				try {
					Thread.sleep(3000);
				}
				catch(InterruptedException ie) {
					System.out.println(ie.getMessage());
				}

				JavascriptExecutor js = (JavascriptExecutor) driver;

				WebElement selectElement = driver.findElement(By.xpath("//div[@class='form-group']//div[@class='col-lg-10']//select[@class='form-control']"));
				js.executeScript("arguments[0].scrollIntoView();", selectElement);
				driver.findElement(By.xpath("//div[@class='form-group']//label[text()='Selects']"));
				System.out.println("Scrolled Down Successfully !!");
			}
			catch(NoSuchElementException ne) {
				System.out.println("Not able to Scroll Down");
				System.exit(0);
			}
		}
		else {
			System.out.println("url not loaded");
			System.exit(0);
		}
	}

	//select even numbers from multi drop down
	List<WebElement> selectEvenNumbers(Select oselect) {		
		List<WebElement> selectList = oselect.getOptions();
		System.out.println("Total element in the select MultiDrop-down is --> " + selectList.size());
		for(WebElement element : selectList) {
			if(Integer.parseInt(element.getText()) % 2 == 0) {
				oselect.selectByVisibleText(element.getText());
			}
		}
		return selectList;
	}

	//print select numbers from multi drop down
	List<WebElement> printSelectedOptions(Select oselect) {
		List<WebElement> selectedOptions = oselect.getAllSelectedOptions();
		System.out.print("Selected numbers are --> ");
		for(WebElement element : selectedOptions) {
			System.out.print(element.getText() + ", ");
		}
		return selectedOptions;
	}

	//print deselected options in multi drop-down
	void printDeSelectedOptions(Select oselect,List<WebElement> selectList) {
		selectList.removeAll(printSelectedOptions(oselect));
		System.out.print("\nDeselected Options are --> ");
		for(WebElement element : selectList) {
			System.out.print(element.getText() + ", ");
		}
	}

	void deselectAllOptions(Select oselect) {
		oselect.deselectAll();
	}

	void getCountOfSelectedOptions(Select oselect) {
		List<WebElement> countOfSelectedOptions = oselect.getAllSelectedOptions();
		System.out.println(countOfSelectedOptions.size() == 0 ? "\nAll options are successfully deselected" : "\nDeselectAll function failed!!");
	}

	public static void main(String[] args) {
		MultiDropDown multiDropDown = new MultiDropDown();
		multiDropDown.setupDriver("http://automationbykrishna.com");
		multiDropDown.navigateToBasicElement();
		Select oselect = new Select(multiDropDown.driver.findElement(By.xpath("//div[@class='form-group']//div[@class='col-lg-10']//select[@class='form-control']")));
		multiDropDown.printDeSelectedOptions(oselect,multiDropDown.selectEvenNumbers(oselect));
		multiDropDown.deselectAllOptions(oselect);
		multiDropDown.getCountOfSelectedOptions(oselect);
	}
}