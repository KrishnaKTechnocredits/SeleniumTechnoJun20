package jagdeesh;

	import java.util.HashMap;
	import java.util.Map;
	import org.openqa.selenium.By;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

public class EmpPerLocAndPosition extends PreDefinedActions{	

		@BeforeTest
		public void openApplication() {
			 driver = start("https://editor.datatables.net/examples/extensions/excel");
		}
		
		@Test
		public void employeesPerOffice() {
			Map<String,Integer> mapEmp = new HashMap<String,Integer>();
			Map<String,Integer> mapEmpPosition = new HashMap<String,Integer>();
			do {
				int countOfRows = driver.findElements(By.xpath("//table[@id='example']/tbody/tr/td[5]")).size();
				for (int index = 1; index <= countOfRows; index++) {
					String location = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index + "]/td[5]"))
							.getText();
					String position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + index + "]/td[4]"))
							.getText();
					
					if(mapEmp.containsKey(location)) {
						mapEmp.put(location, mapEmp.get(location)+1);
					}else {
						mapEmp.put(location, 1);
					}
					
					if(mapEmpPosition.containsKey(position)) {
						mapEmpPosition.put(position, mapEmpPosition.get(position)+1);
					}else {
						mapEmpPosition.put(position, 1);
					}
				}
				driver.findElement(By.xpath("//a[@id='example_next']")).click();
			} while (!driver.findElement(By.xpath("//a[@id='example_next']")).getAttribute("class").contains("disabled"));
			System.out.println("Employees in each location :");
			for(String location : mapEmp.keySet())
				System.out.println(location +" : "+mapEmp.get(location));
				
			System.out.println("=============================");
			System.out.println("Employees in each Position :");
			
			for(String position : mapEmpPosition.keySet())
				System.out.println(position +" : "+mapEmpPosition.get(position));
	
		}
		
	}

