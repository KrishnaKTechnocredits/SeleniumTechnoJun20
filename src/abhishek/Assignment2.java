package abhishek;
/*Selenium Assignment 2 : 17th Aug 2020
1. Navigate to facebook sign in page using url https://www.facebook.com/-done
2. Verify page title is “Facebook – log in or sign up”.-done
3. Enter email id & password, and click on ‘Log In’ button.-done
4. Verify page title is “Facebook”.done
5. Now using navigation command go to https://www.google.com/-done
6. Verify when user navigate back at that time page title is “Facebook”.-done
7. Verify when user navigate forward at that time page title is “Google”
8. Verify when user refresh the page, title remains same. */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment2 {

	void navigationMethod() {
		String ExpectedPagetitle = "Facebook – log in or sign up";
		String file = "/Users/adityashivankar/Documents/TechnoGitProject/SeleniumTechnoJun20/resources/mac/chromedriver";
		System.setProperty("webdriver.chrome.driver", file);
		// open a browser
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://www.facebook.com/");
		String currentpagetitle = driver.getTitle();
		// verify page title
		if (currentpagetitle.equals(ExpectedPagetitle))
			System.out.println("Facebook page opened sucessfully");
		else
			System.out.println("Incorrect page opened");
		// maximize the window
		driver.manage().window().maximize();

		// enter Username and password
		driver.findElement(By.id("email")).sendKeys("Abhishek.satyam10@gmail.com");
		driver.findElement(By.name("pass")).sendKeys("9415898177");
		driver.findElement(By.name("login")).click();

		// System.out.println(driver.getTitle());
		if (driver.getTitle().contains("Facebook"))
			System.out.println("user is on Facebook page ");
		else
			System.out.println("user is not Facebook page");

		// Navigation commands
		driver.navigate().to("https://www.google.com/");
		if (driver.getTitle().equals("Google"))
			System.out.println("You are on Google page");
		else
			System.out.println("You are still on same page");

		// Navigation back to facebook page
		driver.navigate().back();
		if (driver.getTitle().equals("Facebook"))
			System.out.println("You are on Facebook page");
		else
			System.out.println("You are still on same page");

		// Navigation forword to google
		driver.navigate().forward();
		if (driver.getTitle().equals("Google"))
			System.out.println("You are on Google page");
		else
			System.out.println("You are still on same page");

		driver.navigate().refresh();
		if (driver.getTitle().equals("Google"))
			System.out.println("You are on Google page");
		else
			System.out.println("You are still on same page");
		driver.close();
	}
		public static void main(String[] args) {
		new Assignment2().navigationMethod();

	}

}
