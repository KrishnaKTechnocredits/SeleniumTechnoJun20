package sonal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Exam_1 {

	
	
	public static void main(String[] args) throws InterruptedException{
			
				
				String os = System.getProperty("os.name").toLowerCase();
				System.out.println("os : "+ os);
				String path = os.contains("windows") ? "./resources/windows/chromedriver.exe"
						: os.contains("mac") ? "./resources/mac/chromedriver" : null;

				System.setProperty("webdriver.chrome.driver", path);
				WebDriver driver = new ChromeDriver();
				driver.get("file:///C:/Users/Dell/Desktop/Exam_1.html");
				
				//Find total number of rows 
				
				int totalRows = driver.findElements(By.xpath("//table[1]/tbody/tr")).size();
				
				for(int i=1;i<=totalRows;i++)
				{
					WebElement cmpLink = driver.findElement(By.xpath("//table[1]/tbody/tr["+i+"]/td[3]"));
					
					String expTitle = driver.findElement(By.xpath("//table[1]/tbody/tr["+i+"]/td[4]")).getText();
					
					String cmpName = driver.findElement(By.xpath("//table[1]/tbody/tr["+i+"]/td[2]")).getText();
											
					String brokenLink = driver.findElement(By.xpath("//table[1]/tbody/tr["+i+"]/td[3]/a")).getAttribute("href");
					
					if(brokenLink.isEmpty()) //find if any link is broken.
						
					{
						System.out.println("This is Missing Link. Its Company name is: "+cmpName+" and Name is: "+driver.findElement(By.xpath("//table[1]/tbody/tr["+i+"]/td[3]")).getText());
					}else //if link is not broken then only proceed.
					{
						cmpLink.click();
						Thread.sleep(5000);
					
						String actTitle = driver.getTitle();
						System.out.println(actTitle);
					
						
						driver.navigate().back();
						
						Thread.sleep(5000);
					
					
					if(actTitle.equals(expTitle))
					{
						System.out.println("Title of company link "+cmpName+" is matching with the title in table ");
					}
					else
					{
						System.out.println("Title of company link "+cmpName+" are not matching with the title in table ");
					}
					
				}
				
				}
	}

	
			
		}



	


