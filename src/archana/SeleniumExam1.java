package archana;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SeleniumExam1 extends PredefinedActions {
	static void findMissingLinks() {
		int rows = driver.findElements(By.xpath("//table/tbody/tr")).size();

		for (int index = 1; index <= rows; index++) {

			WebElement linkName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"));

			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();

			if (linkName.getAttribute("href").equals("")) {
				String empName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[1]")).getText();
				System.out.println(empName + " of Company " + companyName + " is Missing Link.");
			} else {
				String tableTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
				linkName.click();
				if (!driver.getTitle().equals(tableTitle))
					System.out.println(companyName + "Title not matched.");
				else
					System.out.println(companyName + " Title matched.");
				driver.navigate().back();
			}
		}
	}
	public static void main(String[] args) {
		driver = start("file:///E:/JAVA_class/TechnoGitProject/SeleniumProject/resources/forms/Exam-1.html");
		findMissingLinks();
	}

}
