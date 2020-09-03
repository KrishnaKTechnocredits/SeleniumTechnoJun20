package technoCredits.basics;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import technoCredits.basics.base.PredefinedActions;

public class MultiTabEx2 extends PredefinedActions {

	WebDriver driver;

	void setUp() {
		driver = start("file:///D:/TechnoCredits/Selenium/Automation%20Form/New%20Tab%20Open.html");
	}

	void openTab() {
		String mainWin = driver.getWindowHandle();
		driver.findElement(By.xpath("//button[1]")).click();

		Set<String> multiWin = driver.getWindowHandles();

		Iterator<String> itr = multiWin.iterator();

		while (itr.hasNext()) {
			String currentWin = itr.next();
			if (!currentWin.equals(mainWin)) {
				driver.switchTo().window(currentWin);
				System.out.println("After focus change : " + driver.getTitle());
			}
			driver.switchTo().window(mainWin);
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//button[2]")).click();
		System.out.println(driver.getTitle());

	}

	public static void main(String[] args) {
		MultiTabEx2 multi = new MultiTabEx2();
		multi.setUp();
		multi.openTab();

	}

}
