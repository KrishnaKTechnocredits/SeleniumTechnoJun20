package sonal;

	
	import java.util.HashSet;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class Assignment9 {
		
		WebDriver driver;
		
	public  WebDriver Start(String url){
			
			System.setProperty("webdriver.chrome.driver","E:/NewWorkSpace/SeleniumBasics1/chromedriver.exe"); // Method to initiate web driver and launch url
			
			WebDriver driver = new ChromeDriver();
			
			driver.get(url);
			
			driver.manage().window().maximize();
			
			return driver;
		}

	public void uniqueRows() throws InterruptedException
	{
		WebDriver driver = Start("http://automationbykrishna.com");
		driver.findElement(By.id("demotable")).click();
		WebDriverWait wait = new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-striped']/tbody/tr[1]/td[2]")));
		
		int totalRows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr")).size();
		HashSet <Employee> hs = new HashSet <Employee> ();
		for(int i = 1; i<=totalRows;i++)
		{
			String empID1  = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[2]")).getText();
			String empName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[3]")).getText();
			String mngID1 = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[4]")).getText();
			String deptName = driver.findElement(By.xpath("//table[@class='table table-striped']/tbody/tr["+i+"]/td[5]")).getText();
			
			int empID = Integer.parseInt(empID1);
			int mngID = Integer.parseInt(mngID1);
			
			Employee e2 = new Employee();
			e2.setEmpID(empID);
			e2.setEmpName(empName);
			e2.setMNGID(mngID);
			e2.setDeptName(deptName);
			
			
			if(!hs.add(e2))
			{
				System.out.println("Duplicate Row is: "+e2.getEmpID()+" : "+e2.getEmpName()+" : "+e2.getMNGID()+" : "+e2.getDeptName());
			}

			
			
		}
		
		
		System.out.println("Number of unique rows are: "+hs.size());
		driver.close();

	}



		public static void main(String[] args) throws InterruptedException {
			
			Assignment9 a1 = new Assignment9();
			
			a1.uniqueRows();
		
					
		}

	}



