package harshad.selenium_assignment16;

/*Assignment - 16 : 6th Sep'2020

1) Find Unique Office location
2) Find Unique Position

URL : https://editor.datatables.net/examples/extensions/excel*/

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import harshad.basepkg.PredefinedSetOfActions;


public class DataTableEmployeeManagement1 extends PredefinedSetOfActions{

	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver=start("https://editor.datatables.net/examples/extensions/excel");
	}

	@Test(priority=0)
	public void findUniqueOfficeLocations() {

		Set<String> uniqueOffices=new LinkedHashSet<String>();
		String offices;

		WebElement nextBtn=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]"));	
		String nextValue = nextBtn.getAttribute("class");

		List<WebElement> elements= new ArrayList<WebElement>();

		elements = driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']"));
		int size=elements.size();

		String nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");

		System.out.println("\nUnique Office Locations are:");
		while(nextValue.equals(nextValue1)){
			for(int index=1;index<=size;index++) {
				offices= driver.findElement(By.xpath("//table[@id='example']//following::tbody//tr["+index+"]//descendant::td[5]")).getText();
				uniqueOffices.add(offices);
			}

			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
			driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).click();

			size= driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']")).size();
			nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");
		}
		System.out.println(uniqueOffices);
	}

	@Test(priority=1)
	public void findUniquePositions() {
		driver.navigate().refresh();
		
		Set<String> uniquePositions=new LinkedHashSet<String>();

		String positions;

		WebElement nextBtn=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]"));	
		String nextValue = nextBtn.getAttribute("class");
		
		List<WebElement> elements= new ArrayList<WebElement>();

		elements = driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']"));
		int size=elements.size();

		String nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");
		
		System.out.println("\nUnique Positions are:");
			while(nextValue.equals(nextValue1)){
				
				for(int index=1;index<=size;index++) {
					positions= driver.findElement(By.xpath("//table[@id='example']//following::tbody//tr["+index+"]//descendant::td[4]")).getText();
					uniquePositions.add(positions);
				}				
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
				driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).click();

				size= driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']")).size();
				nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");
			}
		System.out.println(uniquePositions);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}
