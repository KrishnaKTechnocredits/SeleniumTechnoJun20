package viresh.utility;

import org.openqa.selenium.WebDriver;

import viresh.base.PredefinedActions;

public class SetUp extends PredefinedActions{
	
	WebDriver driver;
	
	public WebDriver setUp() {
		driver= start();
		return driver;
	}
	
	public WebDriver setUp(String url) {
		driver=start(url);
		return driver;
	}
	
	public void tearDown() {
		driver.close();
	}
}
