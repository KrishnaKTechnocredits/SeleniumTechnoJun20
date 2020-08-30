package pooja;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.javafx.print.Units;

public class Assignment_9 {
	void test() {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.findElement(By.id("demotable")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		int row = driver.findElements(By.xpath("//table[@class='table table-striped']//tr")).size();
		List li = new ArrayList<>();
		List li2 = new ArrayList<>();

		for (int i = 1; i < row; i++) {
			String empid = driver.findElement(By.xpath("//table[@class='table table-striped']//tr[" + i + "]/td[2]"))
					.getText();
			li.add(empid);

			if (li.indexOf(empid) == li.lastIndexOf(empid)) {
				li2.add(empid);
			} else {
				String empName = driver
						.findElement(By.xpath("//table[@class='table table-striped']//tr[" + i + "]/td[3]")).getText();
				System.out.println("duplicate row empId : " + empid + "   empName : " + empName);
			}
		}

		System.out.println("total no of rows : " + li2.size());
	}

	public static void main(String[] args) {
		Assignment_9 assignment_9 = new Assignment_9();
		assignment_9.test();

	}

}
