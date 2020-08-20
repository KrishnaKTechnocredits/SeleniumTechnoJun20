package abhishek;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import abhishek.base.PredefinedActions;

	public class MultiDropdown extends PredefinedActions{
		WebDriver driver;

		void setUp(String url) {
			driver = start(url);
		}


		void printAlloption() throws InterruptedException {
			driver.findElement(By.xpath("//a[@id='basicelements']")).click();
			Thread.sleep(5000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[2]")));

			Select select = new Select(driver.findElement(By.xpath("//select[2]")));
			List<WebElement> optionList =select.getOptions();

			System.out.println("Total Number of Options in Dropdown : " + optionList.size());

			System.out.println("All Selected Options : ");
			for (WebElement webElement : optionList) {
				int elementNumber = Integer.parseInt(webElement.getText());
				if (elementNumber % 2 == 0) {
					select.selectByVisibleText(String.valueOf(elementNumber));
					System.out.println(webElement.getText());
				}
			}

			// Print all deselected options
			System.out.println("All Deselected Options : ");
			for (WebElement webElement : optionList) {
				if (!webElement.isSelected()) {
					System.out.println(webElement.getText());
				}
			}
			select.deselectAll();

			// verify total number of selected options
			if (select.getAllSelectedOptions().isEmpty())
				System.out.println("Verified Total No of Selected option is : " + select.getAllSelectedOptions().size());
			else
				System.out.println("All the options are not deselected in dropdown");
			
			driver.close();
		}
		

		public static void main(String[] args) throws InterruptedException  {
			MultiDropdown multiDropdown = new MultiDropdown();
			multiDropdown.setUp("http://automationbykrishna.com/");
			multiDropdown.printAlloption();
		}
	}

