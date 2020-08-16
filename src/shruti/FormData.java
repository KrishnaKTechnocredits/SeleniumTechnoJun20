package shruti;

	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class FormData {

		// WebDriver driver = new ChromeDriver();

		void dataEntryinForm() {
			System.setProperty("webdriver.chrome.driver",
					".\\Resources\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("file:///D:/Technocredit/Selenium%20recordings/@Selenium/SeleniumAssignment_1.html");
			WebElement firstName = driver.findElement(By.id("first name"));
			firstName.sendKeys("Shruti");
			WebElement lastName = driver.findElement(By.id("last name"));
			lastName.sendKeys("Dubey");
			WebElement email = driver.findElement(By.id("E-mail"));
			email.sendKeys("shrutidubey@gmail.com");
			WebElement companyName = driver.findElement(By.id("Company Name"));
			companyName.sendKeys("Infogain");

			WebElement gender = driver.findElement(By.id("maleG"));
			if (gender.isSelected()) {
				driver.findElement(By.id("femaleG")).click();
			}
			WebElement experience = driver.findElement(By.id("entry2"));
			experience.click();
			WebElement language = driver.findElement(By.id("python"));
			language.click();
			driver.findElement(By.id("google")).click();
			driver.findElement(By.id("termsAndConditions")).click();

			if (firstName.getAttribute("value").equals("Shruti")
					&& lastName.getAttribute("value").equals("Dubey")) {
				System.out.println("Value is entered correctly");
			} else
				System.out.println("Value is not entered correctly");
			
			// when Reset is clicked
			driver.findElement(By.id("resetBtn")).click();
			if (driver.findElement(By.id("first name")).getAttribute("value")
					.equals(""))
				System.out.println("Reset is Done Successfully");
			else
				System.out.println("Reset Failed!!");
			//Navigation
			driver.findElement(By.id("morePractice")).click();
			if(driver.getCurrentUrl().equals("http://automationbykrishna.com/")) {
				System.out.println("Successfully Navigated");
			}
			else {
				System.out.println("Not able to naviigate");
			}	
		}

		public static void main(String[] args) {
			FormData FormData = new FormData();
			FormData.dataEntryinForm();

		}


}
