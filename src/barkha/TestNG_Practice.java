package barkha;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_Practice {
	
	@BeforeMethod									//will always execute before all @Test (means test case)
	public void test1() {
		System.out.println("Before Methods");
	}
	@AfterMethod									//will always execute after all @Test (means test case)
	public void test2() {
		System.out.println("After Methods");
	}
	@Test(priority=1)
	public void test3() {
		System.out.println("This is Test3");
	}
	@Test											//this is default priority so first will execute this
	public void test4() {
		System.out.println("This is Test4");
	}
}