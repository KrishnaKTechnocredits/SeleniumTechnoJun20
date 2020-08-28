//Print page title and then close that window & open next window for same operation
package barkha;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import barkha_base.PredefinedFunctions;

public class HandleMultiBrowsers extends PredefinedFunctions {

	WebDriver driver;

	void setUP(String URL) {
		driver = start(URL);
	}

	void multiBrowsers() {

		String mainWindow = driver.getWindowHandle(); // after opening URL means main page it gives session ID of that
														// page

		int elementCount = driver.findElements(By.xpath("//button")).size();
		
		System.out.println("Page titles of different pages(sites) given on the link are: ");
		
		for (int index = 1; index <= elementCount; index++) {
			driver.findElement(By.xpath("//button[" + index + "]")).click();

			Set<String> multiTab = driver.getWindowHandles();   // set of session IDs
			Iterator<String> itr = multiTab.iterator();

			while (itr.hasNext()) {
				String tab = itr.next(); 						// will give session ID of each tab
				
				if (!tab.equals(mainWindow)) {
					driver.switchTo().window(tab);

					String title = driver.getTitle();
					System.out.println(index+". "+title);

					driver.close();
				}
			}
			driver.switchTo().window(mainWindow);
		}
	}

	void tearDown() {
		driver.close();
	}

	public static void main(String[] args) {
		HandleMultiBrowsers handleMultiBrowsers = new HandleMultiBrowsers();
		handleMultiBrowsers.setUP(
				"file:///C:/Users/DELL%201801/Downloads/Microsoft.SkypeApp_kzf8qxf38zg5c!App/All/New%20Tab%20Open.html");
		handleMultiBrowsers.multiBrowsers();
		handleMultiBrowsers.tearDown();
	}
}