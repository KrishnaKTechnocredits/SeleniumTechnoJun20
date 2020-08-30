package mahesh.Exam.CodingExam3;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateOrdersAndCart extends CommonValidations {
	
	void validateCart() {
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Shopping Cart is empty!']"))).isDisplayed());
		System.out.println("Number of Browser Categaries in Shopping cart: " + driver.findElements(By.xpath("//li[@class='col-xs-6']/a")).size());
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='btn btn-xl rippleWhite cart-shopping-button col-xs-7']")).isDisplayed());
	}
	
	void validateOrders() {
		Assert.assertEquals(driver.getTitle(), "Order History");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.snapdeal.com/myorders");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='MY ORDERS']")).isDisplayed());
	}
	
	void printOrderHistory() {
		List<WebElement> orderList = driver.findElements(By.xpath("//div[@class='orderId']"));
		System.out.println("Number of order placed by User: " + orderList.size() + " and order ID's are below:");
		for (WebElement order: orderList) {
			System.out.println(order.getText().substring(0,21));
		}
	}
	
	void validateUserAccountOptions() {
		action.moveToElement(driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']")))
		.build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdownAccount']/div/ul/li/a[@href='https://www.snapdeal.com/myorders']")));
		List<WebElement> accountOptions = driver.findElements(By.xpath("//div[@class='dropdownAccount']/div/ul/li/a/i"));
		for (WebElement options: accountOptions) {
			System.out.println(options.getText());
		}
		driver.findElement(By.xpath("//a[@href='https://www.snapdeal.com/logout']"));
	}
	
	@Test
	void validateOrderPage() {
		login("mkumavat31@gmail.com", "Password@1");
		driver.manage().window().maximize();
		validateUserAccountOptions();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='dropdownAccount']/div/ul/li/a[@href='https://www.snapdeal.com/myorders']"))).click();
		validateOrders();
		printOrderHistory();
		driver.findElement(By.xpath("//span[text()='Home']")).click();
		validateSnapdealHomePage();
		driver.findElement(By.xpath("//span[@class='cartTextSpan']")).click();
		validateCart();
	}
}
