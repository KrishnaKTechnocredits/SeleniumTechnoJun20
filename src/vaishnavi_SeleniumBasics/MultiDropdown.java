package vaishnavi_SeleniumBasics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/* Selenium Assignment-5 :  20th Aug 2020

Go to AutomationByKrishna website -> Basic Elements -> MultiDropdown
1) Print total number of options.
2) Select all even numbers.
3) Print all selected options.
4) Print all deselected options.
5) Using deselectAll() method, deselect all the options.
6) Now verify total number of selected options is zero [hint : getAllSelectedOptions()] */

public class MultiDropdown {

	void getMultiDropdownOptions(WebDriver driver) throws InterruptedException {
		try {
			driver.findElement(By.xpath("//a[@id='basicelements']")).click();
			Thread.sleep(2000);

			// Now we need to scroll down for accessing MultiDropdown for that we need JavascriptExecutor

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[2]")));

			WebElement multiSelect = driver.findElement(By.xpath("//select[2]"));
			Select ms = new Select(multiSelect);

			// Getting count for total number of options
			List<WebElement> li = ms.getOptions();
			System.out.println("Total Number of Options: " + li.size());

			System.out.println("============================================");

			// Select all even numbers from options and also Print all selected options
            System.out.println("All the Selected Option are: ===================");
			for (WebElement num : li) {
				if (Integer.parseInt(num.getText()) % 2 == 0) {
					num.click();
					System.out.println(num.getText());
				} 
			}
			
			// Print All deselcted options
			System.out.println("All the Deselected Option are: ===================");
			for (WebElement num : li) {
				if (Integer.parseInt(num.getText()) % 2 != 0) 
					System.out.println(num.getText());
			}

			// Deselect all the options
			ms.deselectAll();
			
            // Verify using getAllSelectedOptions if select count is zero
			if (ms.getAllSelectedOptions().size() == 0)
				System.out.println("========All the elemnts are succesfully deselected");
			else
				System.out.println("=========Deselect All operation failed");
		} catch (NoSuchElementException ns) {
			ns.printStackTrace();
		} finally {
			driver.close();
		}
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\windows//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.manage().window().maximize();
		try {
			new MultiDropdown().getMultiDropdownOptions(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
