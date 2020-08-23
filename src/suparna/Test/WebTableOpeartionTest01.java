package suparna.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import suparna.basics.base.PredefineAction;
public class WebTableOpeartionTest01 extends PredefineAction {
	public void titltCheck() throws InterruptedException {
		int rowCnt = driver.findElements(By.xpath("//table//tbody/tr")).size();
		System.out.println("Test case 1 : Check Title column value and Navigated page title value  ");
		System.out.println("Expected Title Vaue --> \t\t\t Actual Title Value -->  \t\t\t Test Case Result");
		for (int index = 1; index <= rowCnt; index++) {
			String tableTitleCol = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[4]")).getText();
			driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a")).click();
			Thread.sleep(200);
			String navigateURL = driver.getTitle();
			boolean testCase1Flag = true;
			if (navigateURL.equals(tableTitleCol)) {
				testCase1Flag = true;
				System.out.print(tableTitleCol + " --> " + navigateURL + " --> Test case  Pass");
				System.out.println("\n");
			} else {
				testCase1Flag = false;
				System.out.print(tableTitleCol + " --> " + navigateURL + " -->  Test case  fail");
				System.out.println("\n");
			}
			driver.navigate().back();
		}
	}

	public void linkCheck() throws InterruptedException {
		driver.get("D:/Suparna_Automation/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		Thread.sleep(200);
		System.out.println("Test case 2:Find Comapany whoes link is missing ");
		int rowCnt = driver.findElements(By.xpath("//table//tbody/tr")).size();
		for (int index = 1; index <= rowCnt; index++) {
			String linkAddress = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a"))
					.getAttribute("href");
			if (linkAddress.length() == 0) {
				System.out.println("Llink value is missing for : "
						+ driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[3]/a")).getText());
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebTableOpeartionTest01 checkTitle = new WebTableOpeartionTest01();
		checkTitle.setDriver("D:/Suparna_Automation/TechnoGitProject/SeleniumTechnoJun20/resources/forms/Exam-1.html");
		checkTitle.titltCheck();
		checkTitle.linkCheck();
		driver.close();
	}
}
