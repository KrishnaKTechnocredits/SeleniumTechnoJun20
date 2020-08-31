package technoCredits.basics.testngdemo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FristTest {
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("BeforeTest-1");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("BeforeClass");
	}
	
	@BeforeMethod
	void setUp() {
		System.out.println("BeforeMethod");
	}
	
	
	@Test
	public void test1() {
		System.out.println("1");
	}
	
	@AfterMethod
	void tearDown() {
		System.out.println("AfterMethod");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("AfterTes-1");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("AfterSuite");
	}
}
