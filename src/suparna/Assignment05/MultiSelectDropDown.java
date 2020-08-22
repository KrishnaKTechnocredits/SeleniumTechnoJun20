package suparna.Assignment05;
/*
Selenium Assignment-5 :  20th Aug 2020

Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]
-----------------------------------
*/
//import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.*;
import suparna.basics.base.PredefineAction;

public class MultiSelectDropDown extends PredefineAction {

	List<WebElement> dropDownElementList;
	Select dropDown;

	public void findTotalNoOFOption() {
			System.out.println("Total no of options avilable in  multi select  drop dwon   :" + dropDownElementList.size());
	}
	public void selectEvenIndexOption() {
				for (WebElement linkElement : dropDownElementList) {
			if (Integer.parseInt(linkElement.getText()) % 2 == 0)
				dropDown.selectByVisibleText(linkElement.getText());
		}
	}
	public void displayAllSelectedOption() {
		System.out.println("Below are seleceted options from drop down ");
		for (WebElement linkElement : dropDownElementList) {
			if (linkElement.isSelected())
				System.out.println(linkElement.getText());
		}
	}
	public void displayAllDeselectedOption() {
		System.out.println("Below are Non seleceted options from drop down ");
		List<WebElement> allList = dropDown.getOptions();
		List<WebElement> deSlectedList = dropDown.getAllSelectedOptions();
		allList.removeAll(deSlectedList);
		for (WebElement linkElement : allList) {
			System.out.println(linkElement.getText());
		}
	}

	public void deselecteAlldOption() {
		dropDown.deselectAll();
		List<WebElement> slectedList = dropDown.getAllSelectedOptions();
		if (slectedList.size() == 0)
			System.out.println("All options are deslected now ");
		else {
			System.out.println("Below are seleceted options from drop down ");
			for (WebElement linkElement : dropDownElementList) {
				if (linkElement.isSelected())
					System.out.println(linkElement.getText());
			}
		}
	}
	MultiSelectDropDown(String URLPath) {
		setDriver();
		driver.get(URLPath);
		try {
			driver.findElement(By.id("basicelements")).click();
			Thread.sleep(1000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();",
					driver.findElement(By.xpath("//select[@class='form-control']")));
			Thread.sleep(1000);
			dropDown = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
			dropDownElementList = dropDown.getOptions();
		} catch (InterruptedException IE) {
			IE.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiSelectDropDown dropdownOperation = new MultiSelectDropDown("http://automationbykrishna.com/");
		dropdownOperation.findTotalNoOFOption();
		dropdownOperation.selectEvenIndexOption();
		dropdownOperation.displayAllSelectedOption();
		dropdownOperation.displayAllDeselectedOption();
		dropdownOperation.deselecteAlldOption();
		MultiSelectDropDown.driver.close();
	}
	
}
