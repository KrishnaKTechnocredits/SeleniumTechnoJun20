package palak.testng;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import palak.base.PredefinedActions;

public class SnapDealExam2 extends PredefinedActions {
	WebDriver driver;
	WebDriverWait wait;
	void setUp() {
		driver = start("https://www.snapdeal.com/");
	}

	@Test
	void loginSnapDeal() {
		setUp();
		String expectedTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.com";
		Assert.assertEquals(driver.getTitle(), expectedTitle);

		WebElement signInElement = driver.findElement(By.xpath("//div[@class='accountInner']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(signInElement).build().perform();
		WebElement loginElement = driver.findElement(By.xpath("//span[@class='accountBtn btn rippleWhite']/a"));
		System.out.println(loginElement.getText());
		loginElement.click();
	}

	@Test(priority = 1)
	void signInUsingFB() {

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		driver.findElement(By.xpath("//div[@class='iframeSignin']/div[@class='userAuthIcons']/div/div[6]/div/div[2]"))
				.click();

		String expectedFBTitle = "Log in to Facebook | Facebook";

		String mainTab = driver.getWindowHandle();
		Set<String> multiTab = driver.getWindowHandles();
		Iterator<String> itr = multiTab.iterator();
		while (itr.hasNext()) {
			String currentTab = itr.next();
			if (!currentTab.equals(mainTab)) {
				driver.switchTo().window(currentTab);
				Assert.assertEquals(driver.getTitle(), expectedFBTitle);

				WebElement email = driver.findElement(By.name("email"));
				email.sendKeys("kiransoni0560@gmail.com");

				WebElement password = driver.findElement(By.name("pass"));
				password.sendKeys("<password>");

				driver.findElement(By.name("login")).click();
			}
		}
	}

	@Test(priority = 1)
	public void signUp() {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("j_username_new")));

		WebElement username = driver.findElement(By.id("j_username_new"));
		Assert.assertEquals(username.getAttribute("value"), "kiransoni0560@gmail.com");

		WebElement number = driver.findElement(By.id("j_username_new"));
		number.sendKeys("0123456789");
		Assert.assertEquals(number.getAttribute("value"), "0123456789");

		WebElement name = driver.findElement(By.id("j_name"));
		Assert.assertEquals(name.getAttribute("value"), "Kiran Soni");

		WebElement password = driver.findElement(By.id("j_password"));
		password.sendKeys("789654");
		Assert.assertEquals(password.getAttribute("value"), "789654");

		WebElement checkbox = driver.findElement(By.id("keepLoginSignUp"));
		if (checkbox.isSelected()) {
			checkbox.click();
		}
		Assert.assertFalse(checkbox.isSelected());

		System.out.println(driver.getTitle());
	}
	// driver.close();
}
