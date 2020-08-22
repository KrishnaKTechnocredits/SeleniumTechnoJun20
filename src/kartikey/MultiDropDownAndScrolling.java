package kartikey;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import kartikey.base.PredDefindActions;

public class MultiDropDownAndScrolling extends PredDefindActions {
	private WebDriver driver;

	String getUrl() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the url");
		String url = scanner.next();
		return url;
	}

	WebDriver setup(String url) {
		return driver = start(url);
	}

	void multiDropDownScroll(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()= 'Basic Elements']")).click();
		Thread.sleep(3000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//select[@class='form-control']")));// //select[2]

		if ((driver.findElement(By.xpath("//select[@class='form-control']")).getAttribute("multiple") != null)) {
			Select select = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
			List<WebElement> listOfdropOptions = select.getOptions();

			// All the options
			Iterator<WebElement> itr = listOfdropOptions.iterator();
			while (itr.hasNext()) {
				WebElement element = itr.next();
				System.out.println(element.getText());
			}

			// Select even and print
			for (WebElement element : listOfdropOptions) {
				if ((Integer.parseInt(element.getText()) % 2 == 0)) {
					select.selectByVisibleText(element.getText());
					System.out.println(element.getText() + " Is selected");
				}
			}
			// Print deselect
			for (WebElement element : listOfdropOptions) {
				if ((Integer.parseInt(element.getText()) % 2 == 0)) {
					select.selectByVisibleText(element.getText());
				} else
					System.out.println(element.getText() + " Is disselected");
			}
			// deselectAll

			select.deselectAll();

			// Verify all are deselected
			if (select.getAllSelectedOptions().size() == 0)
				System.out.println("All are deselected successfully");
			else
				System.out.println("All options are not deselected successfully");
		} else
			System.out.println("Element doesnt support multiselect functionality");
		
		driver.close();
	}

	void end(WebDriver driver) {
		driver.close();
	}

	public static void main(String[] args) throws InterruptedException {
		MultiDropDownAndScrolling multiDropDownAndScrolling = new MultiDropDownAndScrolling();
		multiDropDownAndScrolling
				.multiDropDownScroll(multiDropDownAndScrolling.setup(multiDropDownAndScrolling.getUrl()));
		// http://www.automationbykrishna.com
	}

}
