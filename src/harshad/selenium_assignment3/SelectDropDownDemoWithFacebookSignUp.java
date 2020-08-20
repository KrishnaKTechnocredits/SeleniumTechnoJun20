package harshad.selenium_assignment3;


/*Selenium Assignment -3 : 18th Aug 2020

1. Navigate to facebook sign in page using url https://www.facebook.com/r.php
2. Verify page title is “Sign up for Facebook | Facebook”.
3. Enter Firstname, surname.
4. Enter wrong mobile number (like 788898989hjhj88)
5. Enter any combination of password
6. Using Select class select Date, Month and Year using sequentially selectByValue(), selectByIndex() and selectByVisibleText() 
7. Select gender from given radio button options.
8. Click on SignUp button.
(No need to validate error occurred for not entering a valid phone number.)*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class SelectDropDownDemoWithFacebookSignUp {
	
	static WebElement firstName,surname, emailIDOrMobile,newPassword,birthDate,birthMonth,birthYear,gender,signUpBtn;
	
	//This method will perform steps by finding elements By.id or By.name etc. 
	/*private static WebDriver openBrowser(String url) {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to(url);
		System.out.println("Create new account page of facebook is successfully opened");
		if(driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("Verified page title is “Sign up for Facebook | Facebook”");
			
			
			firstName = driver.findElement(By.name("firstname"));
			firstName.sendKeys("Harshad");
			System.out.println("FirstName entered as: "+firstName.getAttribute("value"));
			
			surname = driver.findElement(By.name("lastname"));
			surname.sendKeys("Bhore");
			System.out.println("Surname entered as: "+surname.getAttribute("value"));
			
			emailIDOrMobile = driver.findElement(By.name("reg_email__"));
			emailIDOrMobile.sendKeys("788898989hjhj88");
			System.out.println("Email ID or Mobile number entered as: "+emailIDOrMobile.getAttribute("value"));
			
			newPassword = driver.findElement(By.name("reg_passwd__"));
			newPassword.sendKeys("abc123456");
			System.out.println("Password entered");
			
			birthDate = driver.findElement(By.id("day"));
			Select daySelect = new Select(birthDate);
			daySelect.selectByValue("5");
			
			birthMonth = driver.findElement(By.id("month"));
			Select monthSelect = new Select(birthMonth);
			monthSelect.selectByVisibleText("Apr");
			
			birthYear = driver.findElement(By.id("year"));
			Select yearSelect = new Select(birthYear);
			yearSelect.selectByValue("1995");
			
			System.out.println("Date of Birth selected from dropdown is: "+birthDate.getAttribute("value")+" " +birthMonth.getAttribute("value").toString()+ " "+ birthYear.getAttribute("value"));
			
			gender = driver.findElement(By.id("u_0_5"));
			gender.click();
			if(gender.getAttribute("value").equals("1")) {
				System.out.println("Gender selected as Female");
			}else if(gender.getAttribute("value").equals("2")) {
				System.out.println("Gender selected as Male");
			}else {
				System.out.println("Gender selected as Custom");
			}
			
			signUpBtn = driver.findElement(By.name("websubmit"));
			signUpBtn.click();
			System.out.println("SignUp button is clicked");
		}else {
			System.out.println("Verified title of current page is not “Sign up for Facebook | Facebook” it is "+driver.getTitle());
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return driver;
	}*/
	
	//This method will perform steps by finding elements By.xpath
	private static WebDriver openBrowserAndPerformStepsUsingXpath(String url) {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to(url);
		System.out.println("Create new account page of facebook is successfully opened");
		if(driver.getTitle().equals("Sign up for Facebook | Facebook")) {
			System.out.println("Verified page title is “Sign up for Facebook | Facebook”");
			
			
			firstName = driver.findElement(By.xpath("//input[@name=\"firstname\"]"));
			firstName.sendKeys("Harshad");
			System.out.println("FirstName entered as: "+firstName.getAttribute("value"));
			
			surname = driver.findElement(By.xpath("//input[@name=\"lastname\"]"));
			surname.sendKeys("Bhore");
			System.out.println("Surname entered as: "+surname.getAttribute("value"));
			
			emailIDOrMobile = driver.findElement(By.xpath("//input[@name=\"reg_email__\"]"));
			emailIDOrMobile.sendKeys("788898989hjhj88");
			System.out.println("Email ID or Mobile number entered as: "+emailIDOrMobile.getAttribute("value"));
			
			newPassword = driver.findElement(By.xpath("//input[@name=\"reg_passwd__\"]"));
			newPassword.sendKeys("abc123456");
			System.out.println("Password entered");
			
			birthDate = driver.findElement(By.xpath("//select[@id=\"day\"]"));
			Select daySelect = new Select(birthDate);
			daySelect.selectByValue("5");
			
			birthMonth = driver.findElement(By.xpath("//select[@id=\"month\"]"));
			Select monthSelect = new Select(birthMonth);
			monthSelect.selectByVisibleText("Apr");
			
			birthYear = driver.findElement(By.xpath("//select[@id=\"year\"]"));
			Select yearSelect = new Select(birthYear);
			yearSelect.selectByValue("1995");
			
			System.out.println("Date of Birth selected from dropdown is: "+birthDate.getAttribute("value")+" " +birthMonth.getAttribute("value").toString()+ " "+ birthYear.getAttribute("value"));
			
			//gender = driver.findElement(By.xpath("//input[@id=\"u_0_5\"]"));
			gender = driver.findElement(By.xpath("//input[@value=\"2\"]"));
			gender.click();
			if(gender.getAttribute("value").equals("1")) {
				System.out.println("Gender selected as Female");
			}else if(gender.getAttribute("value").equals("2")) {
				System.out.println("Gender selected as Male");
			}else {
				System.out.println("Gender selected as Custom");
			}
			
			signUpBtn = driver.findElement(By.xpath("//button[@name=\"websubmit\"]"));
			signUpBtn.click();
			System.out.println("SignUp button is clicked");
		}else {
			System.out.println("Verified title of current page is not “Sign up for Facebook | Facebook” it is "+driver.getTitle());
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return driver;
	}
	
	public static void main(String[] args) {
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("os : "+ os);
		String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;

		System.setProperty("webdriver.chrome.driver", path);
		//WebDriver driver = openBrowser("https://www.facebook.com/r.php");
		WebDriver driver = openBrowserAndPerformStepsUsingXpath("https://www.facebook.com/r.php");
		driver.close();
	}
}
