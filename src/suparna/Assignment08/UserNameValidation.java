package suparna.Assignment08;
import org.openqa.selenium.By;
import suparna.basics.base.PredefineAction;
public class UserNameValidation extends PredefineAction {
	public void userNameValidation() throws InterruptedException {
		String fName, lName, uName;
		boolean flag = true;
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		Thread.sleep(2000);
		int rowCnt = driver.findElements(By.xpath("//table[@class='table']/tbody/tr")).size();
		System.out.println("FirstName \t LastName \t UserName ");
		for (int outerIndex = 1; outerIndex <= rowCnt; outerIndex++) {
			fName = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[" + outerIndex + "]/td[2]"))
					.getText();
			lName = driver.findElement(By.xpath("//table[@class='table']/tbody/tr[" + outerIndex + "]/td[3]"))
					.getText();
			uName = fName.charAt(0) + lName;
			System.out.println(fName + "\t\t" + lName + "\t\t" + uName.toLowerCase());
			if (!(uName.toLowerCase().contentEquals(driver
					.findElement(By.xpath("//table[@class='table']/tbody/tr[" + outerIndex + "]/td[4]")).getText())))
				flag = false;
		}
		if (flag)
			System.out.println("Test case pass");
		else
			System.out.println("Test case fail");
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		UserNameValidation unameValidation = new UserNameValidation();
		unameValidation.setDriver();
		unameValidation.userNameValidation();
		driver.close();
	}

}
