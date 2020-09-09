package shruti;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import shruti.predefinedActionspkg.PtrdefinedActions;

public class EmployeeMap extends PtrdefinedActions {

		WebDriver driver;
		
		@BeforeMethod
		void setUp() {
			driver = start();
		}
		@Test
		void numberOfEmp(){
			int rowSize = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[1]")).size();
			int tableSize= driver.findElements(By.xpath("//a[@aria-controls='example' and @data-dt-idx>=1 and @data-dt-idx<=6]")).size();
			Map<String, Integer> officeMap = new HashMap<String, Integer>();
			Map<String, Integer> positionMap = new HashMap<String, Integer>();
			for(int outerindex=1; outerindex<=tableSize; outerindex++){
			if(!driver.findElement(By.xpath("//a[text()='Next']")).getAttribute("class").contains("disabled")) {
				
				for (int index = 1; index <= rowSize; index++) {
					String office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index+ "]/td[5]")).getText();
					
					if (officeMap.containsKey(office)) {
						officeMap.put(office,officeMap.get(office)+1 );
					}
					else{
						officeMap.put(office, 1);
					}
				}
					for (int index = 1; index <= rowSize; index++) {
						String position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index+ "]/td[4]")).getText();
					if (positionMap.containsKey(position)) {
						positionMap.put(position,positionMap.get(position)+1 );
					}
					else{
						positionMap.put(position, 1);
					}
				}
			}
				driver.findElement(By.xpath("//a[text()='Next']")).click();
			}
			System.out.println(officeMap);
			System.out.println(positionMap);
			}	

}
