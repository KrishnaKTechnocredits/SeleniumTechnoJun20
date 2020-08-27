package jagdeesh;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

public class Employee {
	int empId;
	String empName;
	int mngrId;
	String dept;
	
	public void setEmpId (int empId){
		this.empId = empId;
		}
	public int getEmpId(){
		return empId;	
		}
	
	public void setEmpName (String empName){
		this.empName = empName;
		}
	public String getEmpName(){
		return empName;	
		}
	
	public void setMngrId (int mngrId){
		this.mngrId = mngrId;
		}
	public int getMngrId(){
		return mngrId;	
		}
		
	public void setDept (String dept){
		this.dept = dept;
		}
	public String getDept(){
		return dept;	
		}
	
	@Override
	public int hashCode() {
		return empId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(getClass()!=obj.getClass())
			return false;
		Employee e = (Employee)obj;
		return (this.empId == e.empId) && (this.empName.equals(e.empName) && (this.mngrId == e.mngrId) && (this.dept.equals(e.dept)));
	}
}