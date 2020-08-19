package technoCredits.basics;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDownDemo {
	static private WebDriver start(String url) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : " + os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		return driver;
	}

	public static void main(String[] args) {
		WebDriver driver = start("file:///C:/Users/Krishna%20Kanani/Downloads/SeleniumAssignment_1.html");
		WebElement continentsDropdown= driver.findElement(By.id("continents"));
		Select oselect = new Select(continentsDropdown);
		List<WebElement> listOfContinents = oselect.getOptions();
		System.out.println(listOfContinents.size());
		
	/*	WebElement element = listOfContinents.get(0);
		String textOfElement = element.getText();
		System.out.println(textOfElement);*/
		
		for(WebElement element : listOfContinents) {
			System.out.println(element.getText());
		}
		
		oselect.selectByVisibleText("Africa");
		oselect.selectByValue("1");
		oselect.selectByIndex(5);
		
	}
}



