package technoCredits.basics.testngdemo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SecondTest {
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("BeforeSuite");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("BeforeTest");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("SecondTest BeforeClass");
	}
	
	@BeforeMethod
	void setUp() {
		System.out.println("SecondTest BeforeMethod");
	}
	
	@Test(priority=0)
	public void test1() {
		System.out.println("SecondTest - 1");
	}
	
	@Test(priority=1)
	public void test2() {
		System.out.println("SecondTest - 2");
	}
	
	@Test
	public void test3() {
		System.out.println("SecondTest - 3");
	}
	
	@AfterMethod
	void tearDown() {
		System.out.println("AfterMethod - SecondTest");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass - SecondTest");
	}
	
	
	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest");
	}
}
