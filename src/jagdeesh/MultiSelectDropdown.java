package jagdeesh;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MultiSelectDropdown {

	void navigateTo(WebDriver driver, String url) throws InterruptedException {
		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("basicelements")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//select[2]")));
		WebElement element = driver.findElement(By.xpath("//select[2]"));
		Select oselect = new Select(element);
		// To verify if it is multi select dropdown
		String name = element.getAttribute("multiple");
		if (name.equals("true"))
			System.out.println("Drop down is multi select dropdown");
		// Total Options in the dropdown
		System.out.println("(A) Total number of Options in the List --> " + oselect.getOptions().size());
		System.out.println("===================================");
		// To select all even options
		List<WebElement> list = oselect.getOptions();
		for (WebElement listOfOptions : list) {
			if ((Integer.parseInt(listOfOptions.getText()) % 2) == 0)
				oselect.selectByVisibleText(listOfOptions.getText());
		}
		// Print all selected options.
		int count = 0;
		System.out.println("(B) Selected Options are: all even Numbers");
		for (WebElement listOfOptions : list) {
			if (listOfOptions.isSelected()) {
				System.out.println(listOfOptions.getText());
				count++;
			}
		}
		System.out.println("(C)Count of Selected options is --> " + count);
		System.out.println("=====================");

		// Print all deselected options.
		int notselectedCount = 0;
		System.out.println("(D) DeSelected Options are:");
		for (WebElement listOfOptions : list) {
			if (!listOfOptions.isSelected()) {
				System.out.println(listOfOptions.getText());
				notselectedCount++;
			}
		}
		System.out.println("(E) Count of DeSelected options is --> " + notselectedCount);
		System.out.println("=====================");
		// Using deselectAll() method, deselect all the options.
		oselect.deselectAll();
		int selectedCount = 0;
		for (WebElement listOfOptions : list) {
			if (listOfOptions.isSelected()) {
				selectedCount++;
			}
		}
		System.out.println("(F) Total number of Selected Options count is --> " + selectedCount);
	}

	public static void main(String[] agrs) throws InterruptedException {
		MultiSelectDropdown multidd = new MultiSelectDropdown();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String url = "http://automationbykrishna.com/";
		multidd.navigateTo(driver, url);
		driver.close();
	}

}