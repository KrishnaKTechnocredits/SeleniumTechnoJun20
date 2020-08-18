package anshu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class FindElementInFacbookUsingXPath {
	String fname , lastname ,mobileNo , Password, day , Month , Year,gender , signup;
	void findAllElementInFacbookSignInPage(WebDriver driver) throws InterruptedException {
	
		driver.manage().window().maximize();
		driver.findElement(By.id("u_0_2")).click();
		Thread.sleep(1000);
	
		
		System.out.println(driver.getTitle());
		
	//  Enter Firstname, surname.
	 
	 driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Anshu");
	 fname = driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value");
	 System.out.println("Name : "+fname);
	 Thread.sleep(1000);
	 
	 driver.findElement(By.xpath("//input[@id='u_1_d']")).sendKeys("Kashyap");
	 lastname = driver.findElement(By.xpath("//input[@id='u_1_d']")).getAttribute("value");
	 System.out.println("SurName : " + lastname);
	 Thread.sleep(1000);
	 
	 driver.findElement(By.xpath("//input[@id='u_1_g']")).sendKeys("875785AAa47");
	 mobileNo = driver.findElement(By.xpath("//input[@id='u_1_g']")).getAttribute("value");
	 System.out.println("Mobile No : "+mobileNo);
	 Thread.sleep(1000);
	 
	 driver.findElement(By.xpath("//input[@id='password_step_input']")).sendKeys("Looser123#");
	 Password = driver.findElement(By.xpath("//input[@id='password_step_input']")).getAttribute("value");
	System.out.println("Password : "+Password);
	Thread.sleep(1000);
	
	// 3. Select date moth year 
	WebElement daydropdown = driver.findElement(By.xpath("//select[@id='day']"));
	Select selectDay = new Select(daydropdown);
    selectDay.selectByVisibleText("28");
    day = selectDay.getFirstSelectedOption().getText();
    System.out.println("BirthDay : "+day);
    Thread.sleep(1000);
    
	WebElement monthdropdown = driver.findElement(By.xpath("//select[@id='month']"));
	Select selectMonth = new Select(monthdropdown);
	selectMonth.selectByIndex(7);
	Month = selectMonth.getFirstSelectedOption().getText();
	System.out.println("BirthMonth : "+Month);
	Thread.sleep(1000);
	
	WebElement yeardropdown = driver.findElement(By.xpath("//select[@id='year']"));
	Select selectyear = new Select(yeardropdown);
	selectyear.selectByValue("1995");
	Year = selectyear.getFirstSelectedOption().getText();
	System.out.println("BirthYear : "+Year);
	Thread.sleep(1000);
	
	//4 
	driver.findElement(By.xpath("//input[@name='sex' and @value='1']")).click();
	gender = driver.findElement(By.xpath("//input[@name='sex' and @value='1']")).getAttribute("value");
	System.out.println(" Gender : "+ gender);
	Thread.sleep(1000);
	
	//5 signupClick
	driver.findElement(By.xpath("//button[@name='websubmit']")).click();
	signup = driver.findElement(By.xpath("//button[@name='websubmit']")).getAttribute("value");
    Thread.sleep(1000);
	}
	
void testCaseOFAllElement(WebDriver driver) {
	
	
	System.out.println(" '\\n'Verifation of all elements");
	
	
	//  Verify Title
	if(driver.getTitle().equals("Sign up for Facebook|Facebook")) {
		 System.out.println("Test Pass : got 'Sign up for Facebook|Facebook ' title");
	 }else {
		 System.out.println("TestFail : Unable to find title ");
	}
	
  // Test Case for Dropdown
	if(day.length() != 0  && Month.length() !=0 && Year.length()!=0 ||day.equals("28") && Month.equals("Jul") && Year.equals("1995") ) {
		System.out.println("TestPass : Dropdown Selected");
	}else {
		System.out.println("TestFail : Dropdown is not selected");
	}
	
	// Test Case for Input Text
	if(fname.length() != 0  && lastname.length() !=0 && mobileNo.length()!=0 && Password.length() != 0 ||fname.equals("Anshu") && lastname.equals("Kashyap") && mobileNo.equals("875785AAa47") && Password.equals("Looser123#")) {
		System.out.println("TestPass : Selected dropdown is same");
	}else {
		System.out.println("TestFail : Selected dropdown is not same");
	 } 
	
	// Test Case for Radio Button
	if(gender.equals("female")|| gender.length() !=0 ) {
		System.out.println(" TestPass : Gender is selected.");
	}else {
		System.out.println(" TestFail : Gender is not selected");
	}
	System.out.println( " All verification are done");
	}

 public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
    System.out.println(" Launch Chrome Browser");
    driver.navigate().to("http://facebook.com");
    System.out.println("Facebook Tab Open");
	
	FindElementInFacbookUsingXPath findElement = new FindElementInFacbookUsingXPath();
	try {
		findElement.findAllElementInFacbookSignInPage(driver);
	}catch(InterruptedException ie) {
		
	}
	findElement.testCaseOFAllElement(driver);
	driver.close();
	
	
	
}
}
