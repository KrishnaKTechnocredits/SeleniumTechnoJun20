package anshu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import anshu.base.PredefinedProperty;

public class MouseHoverValidation extends PredefinedProperty{
	WebDriver driver;

	void setUp() {
		driver = start("https://www.amazon.in/");
	}

	void mouseHover() {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		actions.moveToElement(element).build().perform();
		driver.findElement(By.xpath("//span[text()='Your Account']")).click();
		System.out.println("TEST PASS: Successfully hovered mouse and got  Title is : "+ driver.getTitle());		
	}

	void tearDown() {
		driver.quit();
	}
	public static void main(String[] args) {
		MouseHoverValidation mouseAction = new MouseHoverValidation();
		mouseAction.setUp();
		mouseAction.mouseHover();
		mouseAction.tearDown();
	}
}
