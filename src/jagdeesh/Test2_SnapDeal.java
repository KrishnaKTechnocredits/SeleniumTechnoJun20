package jagdeesh;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test2_SnapDeal {
WebDriver driver;
void clickLogin(WebDriver driver) {
this.driver = driver;
driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
WebDriverWait wait = new WebDriverWait(driver, 15);
String mainWindow = driver.getWindowHandle();

Actions actions = new Actions(driver);
WebElement element = driver.findElement(By.xpath("//span[@class='accountUserName col-xs-12 reset-padding']"));
actions.moveToElement(element).build().perform();
driver.findElement(By.xpath("//div[2]/span[2]/a")).click();

//click on fb link
driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
driver.findElement(By.xpath("//div[@class='login-register-common']/div/div[2][@id='fbUserLogin']")).click();

Set<String> multWindows=driver.getWindowHandles();
//To switch to fb page
Iterator<String> itr = multWindows.iterator();
while (itr.hasNext()) {
String currentWindow = itr.next();
if (!currentWindow.equals(mainWindow)) {
driver.switchTo().window(currentWindow);
System.out.println("--> Title is : " + driver.getTitle());
}
}
driver.findElement(By.xpath("//input[@name='email']")).sendKeys("m_jagadeesh_99@yahoo.com");
driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("Sretan@#2014");
driver.findElement(By.xpath("//button[@id='loginbutton']")).click();
driver.switchTo().window(mainWindow);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='loginIframe']")));
driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='loginIframe']")));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='j_username_new']")));
driver.findElement(By.xpath("//input[@id='j_number']")).sendKeys("1234567890");
driver.findElement(By.xpath("//input[@id='j_password']")).sendKeys("Sretan@#2014");

System.out.println(driver.findElement(By.xpath("//input[@id='keepLoginSignUp']")).isSelected()
? "Checkbox already selected"
: "Checkbox not selected");
driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).click();
if (!driver.findElement(By.xpath("//label[@for='keepLoginSignUp']")).isSelected()) {
System.out.println("unchecked 'Keep me logged in' checkbox successfully.");
}
}
public static void main(String[] agrs) {
System.setProperty("webdriver.chrome.driver", "./resources/windows/chromedriver.exe");
WebDriver driver = new ChromeDriver();
driver.get("https://www.snapdeal.com/");
driver.manage().window().maximize();
new Test2_SnapDeal().clickLogin(driver);
}
}