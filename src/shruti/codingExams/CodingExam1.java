package shruti.codingExams;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CodingExam1 {

	void verifyLink() {
		System.setProperty("webdriver.chrome.driver",
				".\\Resources\\windows\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);

		driver.get("file:///D:/Technocredit/TechnoGitProject/SeleniumBasics/SeleniumBasics/resources/forms/Exam-1.html");

		int totalRows = driver.findElements(By.xpath("//table/tbody/tr"))
				.size();

		for (int index = 1; index <= totalRows; index++) {
			List<WebElement> companyLink = driver.findElements(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"));
			String companyName = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[2]")).getText();
			String linkTitle = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();

			for (WebElement currentLink : companyLink) {
				if(!currentLink.getAttribute("href").equals("")){
				currentLink.click();
				if (driver.getTitle().equals(linkTitle)) {
					System.out.println(companyName+ " has a valid title and title is : "+ linkTitle);
				} else {
					System.out.println("Title does not match for "+ companyName);
				}
			}
				else{
					System.out.println(companyName+ " has no link");
				}
			driver.navigate().back();
		}
	}
	}
	public static void main(String[] args) {
		CodingExam1 codingExam1 = new CodingExam1();
		codingExam1.verifyLink();
	}
}
