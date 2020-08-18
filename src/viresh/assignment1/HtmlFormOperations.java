package viresh.assignment1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HtmlFormOperations {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".//resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(
				"file:///C:/Users/Viresh.jangam/OneDrive%20-%20Wolters%20Kluwer/Desktop/Viresh/TechnoCredits/Assignments/Selenium/Assignment1/SeleniumAssignment_1.html");

		// adding a value into textbox
		System.out.println("The browser name is: " + driver.getTitle());
		driver.findElement(By.id("first name")).sendKeys("Viresh");
		System.out.println("first name is: " + driver.findElement(By.id("first name")).getAttribute("value"));

		// selecting a radio button
		driver.findElement(By.id("entry3")).click();
		System.out.println("Experience radio button selected?: " + driver.findElement(By.id("entry3")).isSelected());

		// selecting a checkbox
		driver.findElement(By.id("python")).click();
		System.out.println("Language checkbox selected?: " + driver.findElement(By.id("python")).isSelected());

		// clicking on Reset form button
		driver.findElement(By.id("resetBtn")).click();
		System.out.println("Reset button clicked.");
		
		//to verify if reset form functionality works
		boolean resetflag = false;
		if (driver.findElement(By.id("first name")).getAttribute("value").isEmpty() == true) {
			if (driver.findElement(By.id("entry3")).isSelected() == false) {
				if (driver.findElement(By.id("python")).isSelected() == false) {
					resetflag = true;
				}
			}
		}

		if (resetflag == true)
			System.out.println("Reset form functiality: Worked!!!");
		else
			System.out.println("Reset form functionality: NOT worked!!!");
		
		//clicking on a hyperlink
		driver.findElement(By.id("morePractice")).click();
		System.out.println("Clicked to Redirect to: http://automationbykrishna.com/");
		
		//To verify if we are getting landed on a expected page
		if (driver.getTitle().equals("Login Signup Demo")) {
			if (driver.getCurrentUrl().equals("http://automationbykrishna.com/")) {
				System.out.println("Successfully directed to: " + driver.getCurrentUrl() + " and page title is: "
						+ driver.getTitle());
			}
		} else
			System.out.println("diverted to wrong URL");
		driver.close();
		/*
		 * driver.findElement(By.id("last name")).sendKeys("Jangam");
		 * driver.findElement(By.id("E-mail")).sendKeys("viresh.jangam@gmail.com");
		 * driver.findElement(By.id("Company Name")).sendKeys("Larsen & Toubro Ltd");
		 * driver.findElement(By.id("maleG")).click();
		 * driver.findElement(By.id("continents")).sendKeys("Europe");
		 * driver.findElement(By.id("knownlanguages")).sendKeys("C++");
		 * driver.findElement(By.id("python")).click();
		 * driver.findElement(By.id("tw")).click();
		 * driver.findElement(By.id("termsAndConditions")).click();
		 */
	}
}
