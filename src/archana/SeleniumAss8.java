package archana;

import org.openqa.selenium.By;

public class SeleniumAss8 extends PredefinedActions {
	void userNamevalidation() throws InterruptedException {
		driver.findElement(By.xpath("//a[@id='demotable']")).click();
		Thread.sleep(3000);
		int rows = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr")).size();
		for (int index = 1; index <= rows; index++) {
			// concat firstname and lastname
			String name = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[2]")).getText()
					.charAt(0)
					+ driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[3]")).getText();
			String userName = driver.findElement(By.xpath("//table[@id='table1']/tbody/tr[" + index + "]/td[4]"))
					.getText();
			// compare user name
			if (name.toLowerCase().equals(userName))
				System.out.println(userName + "--> Username is correct");
			else
				System.out.println(userName + "--> Incorrect UserName");
		}
	}

	public static void main(String[] args) {
		SeleniumAss8 assignment8 = new SeleniumAss8();
		driver = start("http://automationbykrishna.com");
		try {
			assignment8.userNamevalidation();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}
}