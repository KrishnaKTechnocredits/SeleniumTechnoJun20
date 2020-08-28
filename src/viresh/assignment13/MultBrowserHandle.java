package viresh.assignment13;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import viresh.utility.SetUp;

public class MultBrowserHandle extends SetUp {

	WebDriver driver;

	void multiWindowHandle() {
		driver = setUp(
				"file:///C:/Users/Viresh.jangam/OneDrive%20-%20Wolters%20Kluwer/Desktop/Viresh/TechnoCredits/TechnoGitProject/javatechnojun20/SeleniumTechnoJun20/src/viresh/assignment13/New%20Tab%20Open.html");
		
		WebDriverWait wait= new WebDriverWait(driver,15);
		
		int buttonCount=driver.findElements(By.xpath("//button")).size();
		
		for (int index=1; index<=buttonCount; index++) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[" + index + "]/a"))).click();
			String mainWindow = driver.getWindowHandle();			
			String button = driver.findElement(By.xpath("//button[" + index + "]/a")).getText();
			Set<String> multiWin = driver.getWindowHandles();		
			Iterator<String> itr = multiWin.iterator();
			while(itr.hasNext()) {
				String newTab = itr.next();
				if(!newTab.equals(mainWindow)) {
					driver.switchTo().window(newTab);
					System.out.println("Clicked on: " + button);
					System.out.println("  - title >> " + driver.getTitle());
				}
			}
			driver.close();
			driver.switchTo().window(mainWindow);
		}
		tearDown();
	}

	public static void main(String[] args) {
		MultBrowserHandle multiBHandle = new MultBrowserHandle();
		multiBHandle.multiWindowHandle();
	}
}
