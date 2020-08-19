package kartikey;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElements_HtmlPage {
	boolean verifyTitle(WebDriver driver, String title) {
		if (driver.getTitle().equals(title))
			return true;
		else
			return false;
	}

	void fillform(WebDriver driver, String url) {
		driver.get(url);
		driver.findElement(By.id("first name")).sendKeys("Kartikey");
		driver.findElement(By.id("last name")).sendKeys("Dev");
		driver.findElement(By.id("E-mail")).sendKeys("Kd@gamil.com");
		driver.findElement(By.id("Company Name")).sendKeys("Capgemini");
		if (driver.findElement(By.id("maleG")).isSelected() == true)
			driver.findElement(By.id("femaleG")).click();
		else
			driver.findElement(By.id("maleG")).click();
		driver.findElement(By.id("continents")).sendKeys("Australia");
		driver.findElement(By.id("entry1")).click();
		driver.findElement(By.id("knownlanguages")).sendKeys("English, Hindi and Punjabi");
		driver.findElement(By.id("java")).click();
		driver.findElement(By.id("google")).click();
		driver.findElement(By.id("termsAndConditions")).click();
	}

	void resetForm(WebDriver driver) {
		driver.findElement(By.id("resetBtn")).click();
	}

	boolean isFormisReset(WebDriver driver) {
		;
		if (driver.findElement(By.id("first name")).getAttribute("name").equals("")
				|| (driver.findElement(By.id("maleG")).isSelected() == false
						&& driver.findElement(By.id("femaleG")).isSelected() == false)
				|| driver.findElement(By.id("java")).isSelected() == false)
			return true;
		else
			return false;
	}
	
	boolean verifyTitleAndUrlOfRedirectedPage(WebDriver driver) {
		
		driver.findElement(By.id("morePractice")).click();	
		if(driver.getCurrentUrl().equals("http://automationbykrishna.com/") && driver.getTitle().equals("Login Signup Demo"))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		String os = System.getProperty("os.name").toLowerCase();
		String path = os.contains("windows") ? ".//resources//windows//chromedriver.exe"
				: os.contains("mac") ? "./resources/mac/chromedriver" : null;
		System.setProperty("webdriver.chrome.driver", path);
		WebDriver driver = new ChromeDriver();
		WebElements_HtmlPage webElementsHtmlPage = new WebElements_HtmlPage();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the url to access");
		String url = scanner.next();

		webElementsHtmlPage.fillform(driver, url);
		
		if (webElementsHtmlPage.verifyTitle(driver, "TECHNOCREDITS") == true)
			System.out.println("Title is verified");
		else
			System.out.println("Title is not verified");

		webElementsHtmlPage.resetForm(driver);

		if (webElementsHtmlPage.isFormisReset(driver) == true)
			System.out.println("Form reset is successfull");
		else
			System.out.println("Form reset is unsuccessfull");
			
		if(webElementsHtmlPage.verifyTitleAndUrlOfRedirectedPage(driver)== true)
			System.out.println("Redirected page is verified");
		else
			System.out.println("Rediredted page is not verified");
		
		driver.close();
		

	}

}
