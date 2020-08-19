package jagdeesh;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Assignment1 {
	void verifyPageTitle(WebDriver driver){
		if(driver.getTitle().equals("TECHNOCREDITS"))
			System.out.println("Logged into the application : --> Pass");
		else	
			System.out.println("Login not successful : --> Fail");	
	}
	void enterPageDetails(WebDriver driver) {
		driver.findElement(By.id("first name")).sendKeys("Jagadeesh");
		driver.findElement(By.id("last name")).sendKeys("Mangipudi");
		driver.findElement(By.id("e-mail")).sendKeys("jagadeesh.mangipudi412@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Cognizant");
		driver.findElement(By.name("gender")).click();
		driver.findElement(By.id("continents")).sendKeys("Europe");
		driver.findElement(By.id("entry3")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("NA");
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("python")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();
	}
	void validateEnteredDetails(WebDriver driver) {
		String fName = driver.findElement(By.id("first name")).getAttribute("value");
		String lName = driver.findElement(By.id("last name")).getAttribute("value");
		String emailId = driver.findElement(By.id("E-mail")).getAttribute("value");
		String companyName = driver.findElement(By.id("Company Name")).getAttribute("value");
		String gender = driver.findElement(By.name("gender")).getAttribute("value");
		String region = driver.findElement(By.id("continents")).getAttribute("value");
		Boolean experienceFlag = driver.findElement(By.id("entry3")).isSelected();
		String knownLanguages = driver.findElement(By.id("knownlanguages")).getAttribute("value");
		Boolean languageFlag = driver.findElement(By.id("java")).isSelected();
		Boolean dreamCompanyFlag = driver.findElement(By.id("google")).isSelected();
	//	System.out.println(experienceFlag);
		if( ( fName != null || fName.length() !=0 ) && (lName !=null || lName.length()!=0 ) && (emailId !=null || emailId.length() !=0)
				&&(gender!=null || gender.length()!=0) && (region.equals("Europe")) && (experienceFlag != false ) 
				&& (companyName!= null || companyName.length()!=0) && (dreamCompanyFlag != false)
				&&  (knownLanguages!=null || knownLanguages.length()!=0) && (languageFlag!= false) ) 
			System.out.println("Test Case is Pass");
		else
			System.out.println("Test Case is Fail");
	}
	void resetData(WebDriver driver) {
		driver.findElement(By.id("resetBtn")).click();
		String fName = driver.findElement(By.id("first name")).getAttribute("value");
		String lName = driver.findElement(By.id("last name")).getAttribute("value");
		String emailId = driver.findElement(By.id("E-mail")).getAttribute("value");
		String companyName = driver.findElement(By.id("Company Name")).getAttribute("value");
		Boolean gender = driver.findElement(By.id("femaleG")).isSelected();
		String region = driver.findElement(By.id("continents")).getAttribute("value");
		Boolean experienceFlag = driver.findElement(By.id("entry3")).isSelected();
		String knownLanguages = driver.findElement(By.id("knownlanguages")).getAttribute("value");
		Boolean languageFlag = driver.findElement(By.id("java")).isSelected();
		Boolean dreamCompanyFlag = driver.findElement(By.id("google")).isSelected();
	//	System.out.println(languageFlag);
		//System.out.println(gender);
		if(fName.length() ==0 && lName.length()==0  && emailId.length()==0 && companyName.length() == 0 
				&& gender == true && experienceFlag == false && knownLanguages.length() == 0
				&& region.equals("Asia") && languageFlag == false && dreamCompanyFlag == false)
			System.out.println("Data on the form is Reset successfuly");
		else
			System.out.println("Data reset is not successful");
	}
	void clickOnmorePractice(WebDriver driver) {
		driver.findElement(By.id("morePractice")).click();
		String currentURL = driver.getCurrentUrl();
		if(currentURL.equals("http://automationbykrishna.com/"))
			System.out.println("Navigated to next page successfully");
		else
			System.out.println("Failed to navigate to next page");
	}
	public static void main(String[] agrs)  {
		Assignment1 assignment1 = new Assignment1();
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file:///D://techoncredit//SeleniumAssignment_1.html"); 
		assignment1.verifyPageTitle(driver);
		assignment1.enterPageDetails(driver);
		assignment1.validateEnteredDetails(driver);
		assignment1.resetData(driver);
		assignment1.clickOnmorePractice(driver);
	}
}

