package abhishek;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment1 {

	public static void main(String[] args) throws InterruptedException {
		String file = "/Users/adityashivankar/Documents/TechnoGitProject/SeleniumTechnoJun20/resources/mac/chromedriver";
		System.setProperty("webdriver.chrome.driver", file);

		// open a browser
		WebDriver driver = new ChromeDriver();
		driver.get("file:///Users/adityashivankar/Downloads/SeleniumAssignment_1.html");
		String expectedtitle = "TECHNOCREDITS";
		String currenttitle = driver.getTitle();

		// verify Title is “TECHNOCREDITS”.
		if (currenttitle.equals(expectedtitle)) {
			System.out.println("test title passed");
		} else
			System.out.println("test title fail");
		// fill the form
		driver.findElement(By.id("first name")).sendKeys("Abhishek");
		driver.findElement(By.id("last name")).sendKeys("Singh");
		driver.findElement(By.id("E-mail")).sendKeys("abhishekram@gmail.com");
		driver.findElement(By.id("Company Name")).sendKeys("XYZ");

		driver.findElement(By.id("maleG")).click();

		// driver.findElement(By.id("continents")).click();
		driver.findElement(By.id("entry2")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("java");

		driver.findElement(By.id("python")).click();

		driver.findElement(By.id("other")).click();

		driver.findElement(By.id("termsAndConditions")).click();
		// click on the reset button
		driver.findElement(By.id("resetBtn")).click();

		// find the attribute value
		String fname = driver.findElement(By.id("first name")).getAttribute("text");
		String Lname = driver.findElement(By.id("last name")).getAttribute("text");
		String email = driver.findElement(By.id("E-mail")).getAttribute("text");
		String companyname = driver.findElement(By.id("Company Name")).getAttribute("text");

		// check text field is rest or not
		if ((fname == null) && (Lname == null) && (email == null) && (companyname == null)) {
			System.out.println("All text field are reset");
		} else {
			System.out.println("All text field are not reset");
		}
		
		// check gender is reset or not
		WebElement genderElemnet = driver.findElement(By.id("maleG"));
		// System.out.println(genderElemnet.isSelected());
		if (!genderElemnet.isSelected()) {
			System.out.println("gender radio button is reset");
		} else {
			System.out.println("gender radio is not reset");
		}

		// check Experience is reset or not
		WebElement ExperienceElemnet = driver.findElement(By.id("entry2"));
		// System.out.println(ExperienceElemnet.isSelected());
		if (!ExperienceElemnet.isSelected()) {
			System.out.println("Experience radio button is reset");
		} else {
			System.out.println("Experience radio button is not reset");
		}

		// check learnlanguage is reset or not
		WebElement learnlanguageElemnet = driver.findElement(By.id("python"));
		// System.out.println(learnlanguageElemnet.isSelected());
		if (!learnlanguageElemnet.isSelected()) {
			System.out.println("learn language radio buuton is reset");
		} else {
			System.out.println("learn language radio buuton is not reset");
		}
		// check Dreamcompany is reset or not
		WebElement DreamcompanyElemnet = driver.findElement(By.id("other"));
		// System.out.println(DreamcompanyElemnet.isSelected());
		if (!DreamcompanyElemnet.isSelected()) {
			System.out.println("Dreamcompany radio button is reset");
		} else {
			System.out.println(" Dreamcompany radio button is not reset");
		}

		// click on the Go And Practice For it link
		driver.findElement(By.linkText("Go And Practice For it")).click();

		String expectedpagetitle = "Login Signup Demo";
		String currentpageTitle = driver.getTitle();
		String ExpectedURl = "http://automationbykrishna.com/";
		String currentURL = driver.getCurrentUrl();

		if (currentpageTitle.equals(expectedpagetitle) && (currentURL.equals(ExpectedURl))) {
			System.out.println("test passed");
		} else {
			System.out.println("test fail");
		}
		driver.close();
	}

}
