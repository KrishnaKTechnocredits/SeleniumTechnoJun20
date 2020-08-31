package shruti.testNgPkg;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

	@Test
	void loginUiTest(){
		String s1  = "A";
		String s2  = "B";
		System.out.println("Login UI Test SOP");
		Assert.assertEquals(s1, s2);
		
	}
	@Test
	void loginUiFunctionalTest(){
		String s1  = "A";
		String s2  = "A";
		System.out.println("Login Ui Functional Test SOP");
		Assert.assertEquals(s1, s2);
	}
	
}
