package suparna.Test02;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import suparna.basics.base.PredefineAction;
public class SnapdealLogin extends PredefineAction {

	private static final String Itratot = null;

	public void sanpdealLogin() {
		String actualTitle = "Online Shopping Site India - Shop Electronics, Mobiles, Men & Women Clothing, Shoes - www. Snapdeal.co";
		if (actualTitle.equals(driver.getTitle()))
			System.out.println("Test case 1 : Title validation Pass");
		else
			System.out.println("Test case 1 : Title validation Pass");
		System.out.println("Actual Title is : " + actualTitle + "\n  Expected title is : " + driver.getTitle());
		// div[@class='myAccountTab accountHeaderClass col-xs-13
		// reset-padding']/div[@class='accountInner']
		Actions mouseover = new Actions(driver);
		mouseover.moveToElement(driver.findElement(By.xpath(
				"//div[@class='myAccountTab accountHeaderClass col-xs-13 reset-padding']/div[@class='accountInner']")))
				.build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement login = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='accountBtn btn rippleWhite']/a[text()='login']")));
		if ((driver.findElements(By.xpath("//span[@class='accountBtn btn rippleWhite']/a[text()='login']"))
				.size() != 0))
			System.out.println(" Test case 2 : User bale to see Login button Pass ");
		else
			System.out.println(" Test case 2 : User bale to see Login button Fail ");
		String mainPageSession1 = driver.getWindowHandle();
		login.click();
		// Switching to iFrame for logging options
		driver.switchTo().frame("loginIframe");
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//header[text()='login/sign up on snapdeal']")));
		driver.findElement(By.xpath("//div[@class='login-register-common']/div/div[@id='fbUserLogin']")).click();

		Set<String> childSessionId = driver.getWindowHandles();
		Iterator<String> itr = childSessionId.iterator();
		{
			while (itr.hasNext()) {
				String currentSessionId = itr.next();

				if (!(currentSessionId.equals(mainPageSession1))) {

					driver.switchTo().window(currentSessionId);
					if (driver.getTitle().equals("Log in to Facebook | Facebook")) {
						System.out.println(
								"Test case 5 : Facebook login page should be open in new window with page title : "
										+ driver.getTitle() + " Pass");
					} else {
						System.out.println(
								"Test case 5 : Facebook login page should be open in new window with page title : "
										+ driver.getTitle() + " Fail");
					}

					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
					driver.findElement(By.xpath("//input[@id='email']")).sendKeys("nikam.aradhya.v@gmail.com");
					driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Aradhya@01");
					driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
				}
			}

			 driver.switchTo().window(mainPageSession1);

			// Input verification for FB data
			//driver.switchTo().frame("loginIframe");
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SnapdealLogin loginValidation = new SnapdealLogin();
		loginValidation.setDriver("https://www.snapdeal.com/");
		loginValidation.sanpdealLogin();
		 loginValidation.tearDown();

	}

}
