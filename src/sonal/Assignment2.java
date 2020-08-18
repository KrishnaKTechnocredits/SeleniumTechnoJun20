package sonal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Assignment2 {

			public static void main(String[] args) throws InterruptedException {
				
			WebDriver driver  = new ChromeDriver();
			
			driver.get("https://www.facebook.com/");
			String acttitle = driver.getTitle();
			String exptitle = "Facebook – log in or sign up";
			if (acttitle.equals(exptitle))
				System.out.println("Test is Passed. Titles are matching");
			else
				System.out.println("Test is failed. Titles are not matching");
			
			driver.findElement(By.id("email")).sendKeys("inamdar.sonal@gmail.com");
			driver.findElement(By.name("pass")).sendKeys("fgfjyfjfj");
			driver.findElement(By.id("u_0_b")).click();
			
			Thread.sleep(5000);  //wait till page gets loaded
			
			String acttitle1 = driver.getTitle();		
			String exptitle1 = "Log in to Facebook | Facebook";
			
			if (acttitle1.equals(exptitle1))
				System.out.println("Test is Passed. Titles are matching");
			else
				System.out.println("Test is failed. Titles are not matching");

			driver.navigate().to("https://www.google.com/");
			
			Thread.sleep(5000);
			
			System.out.println("In Google"); //check navigated to Google
			
			driver.navigate().back();
			
			Thread.sleep(5000);
			
			String acttitle3 = driver.getTitle();			
			String exptitle3 = "Log in to Facebook | Facebook";
			if (acttitle3.equals(exptitle3))
				System.out.println("Test is Passed. Titles are matching");
			else
				System.out.println("Test is failed. Titles are not matching");
			
			driver.navigate().forward();
			
			String acttitle4 = driver.getTitle();		
			String exptitle4 = "Google";
			if (acttitle4.equals(exptitle4))
				System.out.println("Test is Passed. Titles are matching");
			else
				System.out.println("Test is failed. Titles are not matching");
			
			driver.navigate().refresh();
			
			String acttitle5 = driver.getTitle();
			String exptitle5 = "Google";
			if (acttitle5.equals(exptitle5))
				System.out.println("Test is Passed. Titles are matching");
			else
				System.out.println("Test is failed. Titles are not matching");
			
			driver.close();

		}

	}

