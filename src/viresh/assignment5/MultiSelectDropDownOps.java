package viresh.assignment5;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import viresh.base.PredefinedActions;

public class MultiSelectDropDownOps extends PredefinedActions {
	WebDriver driver;
	int selected;
	Select oselect;
	List<WebElement> optionElements;
	
	void setUp(String url) throws InterruptedException {
		driver = start(url);
		driver.findElement(By.xpath("//a[@id='basicelements']")).click();
		Thread.sleep(3000);
		WebElement we= driver.findElement(By.xpath("//select[2]")); //By.className("form-control")
		oselect = new Select(we);// driver.findElement(By.xpath("//select[@class='form-control']"))
		optionElements = oselect.getOptions();
		Thread.sleep(3000);
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(); ", we);
	}

	void totalOption() throws InterruptedException {
		System.out.println("Total Options in dropdown are: " + optionElements.size());
	}

	void displayEvenNumbers() {
		System.out.println("Even numbered options in dropdown: ");
		for( WebElement element: optionElements) {
			String tempElement= element.getText();
			if(Integer.parseInt(tempElement) %2==0) {
				System.out.println("  > " + tempElement);
			}
		}
	}
		
		void printSelectedOptions() {
			oselect.selectByVisibleText("1");
			oselect.selectByVisibleText("3");
			System.out.println("Selected options: ");
			for (WebElement element : optionElements) {
				if (element.isSelected()) {
					System.out.println("  > "+ element.getText());
				}
			}
		}
		
		void printDeselectedOptions() {
			System.out.println("Deselected options: ");
			for (WebElement element : optionElements) {
				if(!(element.isSelected())) {
					System.out.println("  > " + element.getText());
				}
			}
		}
		
		void deselectAll() {
			oselect.deselectAll();
			List<WebElement> selected=oselect.getAllSelectedOptions();
			if (selected.size()==0) {
				System.out.println("Total number of selected options is: " + selected.size());
			}
			driver.close();
		}
	

	public static void main(String[] args) throws InterruptedException {
		MultiSelectDropDownOps msdd = new MultiSelectDropDownOps();
		msdd.setUp("http://automationbykrishna.com/");
		msdd.totalOption();
		msdd.displayEvenNumbers();
		msdd.printSelectedOptions();
		msdd.printDeselectedOptions();
		msdd.deselectAll();
	}
}
