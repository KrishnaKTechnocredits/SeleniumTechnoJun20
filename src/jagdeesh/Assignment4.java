package jagdeesh;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment4 {
	Assignment4(){
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");		
	}
//to get count of links and names of links
	void printAllLinks(WebDriver driver,ArrayList<WebElement> webElement) {
		System.out.println("Total Number of Links:    "+webElement.size());
		System.out.println("=========================");
		for( int index=0;index<webElement.size();index++) {
			System.out.println(webElement.get(index).getText());
		}	
		System.out.println("=========================");
	}
//To get missing link and href attribute missing text
	void missLink(WebDriver driver,ArrayList<WebElement> webElement) {
		for( int index=0;index<webElement.size();index++) {
			String linkname = webElement.get(index).getAttribute("href");
			try	{
			if(linkname.isEmpty())
				System.out.println("Missing link -->"+webElement.get(index).getText() );
			}catch(NullPointerException e) {
				System.out.println("=========================");
				System.out.println("Output --> "+webElement.get(index).getText());
			}
		}
	}
	public static void main(String[] args) {
		Assignment4 assignment4 = new Assignment4();
		WebDriver driver = new ChromeDriver();
		driver.get("file:///D:/techoncredit/TechnoGitProject/SeleniumTechnoJun20/resources/forms/MissingLink.html");
		ArrayList<WebElement> webElement = new ArrayList<WebElement>(driver.findElements(By.xpath("//a")));
		assignment4.printAllLinks(driver,webElement);
		assignment4.missLink(driver,webElement);
	}
	}