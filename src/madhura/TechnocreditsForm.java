package madhura;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*1) Open the form in browser and verify Title is “TECHNOCREDITS”. //->file:///E:/TechnoCredits/Notes/SeleniumAssignment_1.html
2) Fill all details in the form, for Gender radio button if ‘Female’ radio button is by default selected then select ‘Male’   radio button and vice versa.
3) Reset the form, for that click on “Reset form in same tab” button.
4) Verify Reset works properly on your text field, radio button and checkbox. (For each type, min one validation is required.)
5) Click on the “Go and Practice for it” Button.
6) Website redirect to “automationbykrishna.com”.
7) Verify title and URL of redirected page website.
    Title should be “Login Signup Demo” and the URL should be “http://automationbykrishna.com/”.*/
public class TechnocreditsForm {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("file://E:/TechnoCredits/Notes/SeleniumAssignment_1.html");
		driver.manage().window().maximize();

		// Verifying the page title
		if (driver.getTitle().equals("TECHNOCREDITS")) {
			System.out.println("Title matches to expected title");
		} else {
			System.out.println("Title is not as expected");
		}

		// filling all details in the form
		driver.findElement(By.id("first name")).sendKeys("Madhura");
		driver.findElement(By.id("last name")).sendKeys("Mulay");
		driver.findElement(By.id("E-mail")).sendKeys("madhurah02@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("Sveltoz");

		WebElement genderF = driver.findElement(By.id("femaleG"));
		WebElement genderM = driver.findElement(By.id("maleG"));
		if (genderF.isSelected()) {
			driver.findElement(By.id("maleG")).click();
		} else if (genderM.isSelected()) {
			driver.findElement(By.id("femaleG")).click();
		}

		driver.findElement(By.id("continents")).sendKeys("Europe");
		driver.findElement(By.id("entry1")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("Java");
		driver.findElement(By.id("python")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();
		System.out.println("Form completely filled");
		Thread.sleep(500);

		// reset button click
		driver.findElement(By.id("resetBtn")).click();
		System.out.println("Reset button clicked");

		// working of reset //Text field
		if (driver.findElement(By.id("first name")).getAttribute("value").equals("")
				&& driver.findElement(By.id("last name")).getAttribute("value").equals("")
				&& driver.findElement(By.id("E-mail")).getAttribute("value").equals("")
				&& driver.findElement(By.id("Company Name")).getAttribute("value").equals("")) {
			System.out.println("All Text fields reset --> Passed");
		} else {
			System.out.println("Text field reset --> Failed");
		}
		// Radio button
		if (!genderF.isSelected() && !genderM.isSelected()) {
			System.out.println("Radio button reset --> Passed");
		} else
			System.out.println("Radio button reset --> Failed");
		// Checkbox
		if (!driver.findElement(By.id("java")).isSelected() && !driver.findElement(By.id("python")).isSelected()
				&& !driver.findElement(By.id("javascript")).isSelected()) {
			System.out.println("Checkbox reset --> Passed");
		} else
			System.out.println("Checkbox reset --> Failed");

		// redirect to “automationbykrishna.com”
		driver.findElement(By.id("morePractice")).click();
		if (driver.getTitle().equals("Login Signup Demo")) {
			System.out.println("Redirected page title verified");
		} else
			System.out.println("Page redirecting failed");

		if (driver.getCurrentUrl().equals("http://automationbykrishna.com/")) {
			System.out.println("Redirected page URL verified");
		} else
			System.out.println("URL not matching");

		Thread.sleep(500);
		driver.quit();
	}
}