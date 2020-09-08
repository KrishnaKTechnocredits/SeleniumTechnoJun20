package pooja;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenAproch_Assignment_14 {
	@Test(dataProvider="logindetails")
	public void start(String uname,String psw,String result ) {
		System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com");
		driver.findElement(By.id("registration2")).click();
		driver.manage().window().maximize();
		driver.findElement(By.id("unameSignin")).sendKeys(uname);
		driver.findElement(By.id("pwdSignin")).sendKeys(psw);
		driver.findElement(By.id("btnsubmitdetails")).click();
		
		String expected=driver.switchTo().alert().getText();
		Assert.assertEquals(result, expected);

	}

	@DataProvider(name = "logindetails")
	public String[][] dataprovider() {
		String [][] data=new String [2][3];
		data[0][0]="pooja";
		data[0][1]="Pooja9009";
		data[0][2]="Success!";
		
		data[1][0]="pooja";
		data[1][1]="raut123";
		data[1][2]="Failed! please enter strong password";
		return data;

	}

}
