/*Selenium Assignment-5 :  20th Aug 2020
Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()]
*/
package pranita;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pranita.basic.PredefinedFunctions;

public class Assignment_5_ScrollMultiDropdown extends PredefinedFunctions {
	WebDriver driver;

	void setUp(String url) {
		driver = start(url);
	}
	
	public static void main(String[] args) {
		Assignment_5_ScrollMultiDropdown assignment_5_ScrollMultiDropdown = new Assignment_5_ScrollMultiDropdown();
		assignment_5_ScrollMultiDropdown.setUp("http://automationbykrishna.com/");
		try {
			assignment_5_ScrollMultiDropdown.handleMultidropdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleMultidropdown() throws InterruptedException {
		//Print total number of options.
		driver.findElement(By.xpath("//a[text()='Basic Elements']")).click();//By.id("basicelements")
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//select[@class='form-control']")));
		//driver.findElement(By.xpath("//select[2]")));
		
		Select oselect = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
		
		List<WebElement> listOfOptions = oselect.getOptions();
		System.out.println("\nTotal number of options in MultiDropdown : " + listOfOptions.size());
		
		//Select all even numbers.
		for (WebElement currentOption : listOfOptions) {
			if (Integer.parseInt(currentOption.getText()) % 2 == 0)
				currentOption.click();
		}
		
		//Print all selected options.
		//Print all deselected options
		System.out.println("\n Below options are Selected or Deselected in MultiDropdown:");
		for (WebElement currentOption : listOfOptions) {
			if (currentOption.isSelected())
				System.out.println(currentOption.getText() + " - selected");
			else
				System.out.println(currentOption.getText() + " - deSelected");
		}
		
		//Using deselectAll() method, deselect all the options.
		oselect.deselectAll();
		
		
		//Now verify total number of selected options is zero [hint : getAllSelectedOptions()]
		List<WebElement> listOfDeselectedOptions = oselect.getAllSelectedOptions();
		if (listOfDeselectedOptions.size() == 0) {
			System.out.println("\nAll the options are deselected");
			System.out.println("  Total number of selected options is : " + listOfDeselectedOptions.size());
		} else
			System.out.println("\nAll the dropdown options are not deselected");
		driver.close();
		
		
		/*
		 * if (oSelect.getAllSelectedOptions().isEmpty())
			System.out.println("Verified Total No of Selected option is : " + oSelect.getAllSelectedOptions().size());
		else
			System.out.println("All the options are not deselected ");
	}
		 */
	}
		
	}


