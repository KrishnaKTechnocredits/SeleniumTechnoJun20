package harshad.selenium_assignment17;

/*Assignment - 17 : 6th Sep'2020

1) Office & Number of employees mapping
2) Position and Number of Employees mapping

URL : https://editor.datatables.net/examples/extensions/excel*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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



public class DataTableEmployeeManagement2 extends PredefinedSetOfActions {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
		driver=start("https://editor.datatables.net/examples/extensions/excel");
	}

	@Test(priority=0)
	public void mappingOfUniqueOfficeLocationsAndNumberOfEmployees() {
		
		String offices;

		WebElement nextBtn=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]"));	
		String nextValue = nextBtn.getAttribute("class");

		List<WebElement> elements= new ArrayList<WebElement>();

		elements = driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']"));
		int size=elements.size();

		String nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");

		HashMap<String,Integer> officeLocationAndEmployeeMap = new HashMap<String,Integer>();

		System.out.println("\nUnique Office Locations and Number of Emploees mapping:");
		while(nextValue.equals(nextValue1)){

			for(int index=1;index<=size;index++) {
				offices= driver.findElement(By.xpath("//table[@id='example']//following::tbody//tr["+index+"]//descendant::td[5]")).getText();
				if(officeLocationAndEmployeeMap.containsKey(offices)) {
					officeLocationAndEmployeeMap.put(offices,officeLocationAndEmployeeMap.get(offices)+1);
				}else {
					officeLocationAndEmployeeMap.put(offices, 1);
				}
			}

			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
			driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).click();

			size= driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']")).size();
			nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");
		}

		System.out.println(officeLocationAndEmployeeMap);
		Set<String> uniqueOffices = officeLocationAndEmployeeMap.keySet();

		Iterator<String> itr = uniqueOffices.iterator();

		String uniqueOfficesValue="";
		int numberOfEmployees=0;
		while(itr.hasNext()) {
			uniqueOfficesValue = itr.next();
			numberOfEmployees = officeLocationAndEmployeeMap.get(uniqueOfficesValue);
			System.out.println(uniqueOfficesValue+" : "+numberOfEmployees);
		}
	}

	@Test(priority=1)
	public void mappingOfPositionsAndNumberOfEmployees() {

		driver.navigate().refresh();
		String positions;

		WebElement nextBtn=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]"));	
		String nextValue = nextBtn.getAttribute("class");

		List<WebElement> elements= new ArrayList<WebElement>();

		elements = driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']"));
		int size=elements.size();

		String nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");

		HashMap<String,Integer> positionsAndEmployeeMap = new HashMap<String,Integer>();

		System.out.println("\nUnique Positions and Number of Employees mapping:");
		while(nextValue.equals(nextValue1)){

			for(int index=1;index<=size;index++) {
				positions= driver.findElement(By.xpath("//table[@id='example']//following::tbody//tr["+index+"]//descendant::td[4]")).getText();
				if(positionsAndEmployeeMap.containsKey(positions)) {
					positionsAndEmployeeMap.put(positions,positionsAndEmployeeMap.get(positions)+1);
				}else {
					positionsAndEmployeeMap.put(positions, 1);
				}
			}				
			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
			driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).click();

			size= driver.findElements(By.xpath("//table[@id='example']//following::tbody/tr[@role='row']")).size();
			nextValue1=driver.findElement(By.xpath("//node()[contains(@class,'paginate_button next')]")).getAttribute("class");
		}

		System.out.println(positionsAndEmployeeMap);
		Set<String> uniquePositions = positionsAndEmployeeMap.keySet();

		Iterator<String> itr = uniquePositions.iterator();

		String uniquePositionsValue="";
		int numberOfEmployees=0;
		while(itr.hasNext()) {
			uniquePositionsValue = itr.next();
			numberOfEmployees = positionsAndEmployeeMap.get(uniquePositionsValue);
			System.out.println(uniquePositionsValue+" : "+numberOfEmployees);
		}
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}
}