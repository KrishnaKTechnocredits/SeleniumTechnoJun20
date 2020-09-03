package technoCredits.basics.testngdemo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

	@Test(priority=1)
	public void loginUITest() {
		System.out.println("LoginUITest");
		Assert.assertEquals(1, 2);
		/*Assert.assertEquals(true, false);
		Assert.assertTrue(false);
		Assert.assertFalse(true);
		Assert.assertNull(new LoginTest());*/
		System.out.println("LoginUITest done");
		
	}
	
	@Test(priority=2)
	public void loginFunctionalTest() {
		System.out.println("LoginFunctionalTest");
		demoTest();
	}
	
	public void demoTest() {
		System.out.println("DemoTest");
	}
}
