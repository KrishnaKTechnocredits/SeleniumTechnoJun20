package technoCredits.basics;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class MultiTabEx1 extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start("https://www.naukri.com/");
	}

	void openBrowser() {
		System.out.println(driver.getTitle());

		String mainWin = driver.getWindowHandle();
		Set<String> multiBrowser = driver.getWindowHandles();

		Iterator<String> multiBrowserWin = multiBrowser.iterator();

		while (multiBrowserWin.hasNext()) {
			String currentWin = multiBrowserWin.next();
			if (!currentWin.equals(mainWin)) {
				driver.switchTo().window(currentWin);
				System.out.println(driver.getTitle());
			}
		}
		
		driver.switchTo().window(mainWin);
		System.out.println(driver.getTitle());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();

	}

	public static void main(String[] args) {
		MultiTabEx1 multi = new MultiTabEx1();
		multi.setUp();
		multi.openBrowser();

	}

}
