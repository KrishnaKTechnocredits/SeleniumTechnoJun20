package nikhil.assignment_9;

public class Employee {
	private int employeeId;
	private String employeeName;
	private int employeeManagerId;
	private String employeeDept;
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public int getEmployeeManagerId() {
		return employeeManagerId;
	}
	public void setEmployeeManagerId(int employeeManagerId) {
		this.employeeManagerId = employeeManagerId;
	}
	
	public String getEmployeeDept() {
		return employeeDept;
	}
	
	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}
	
	@Override
	public int hashCode() {
		return employeeId;
	}
	
	@Override
	public boolean equals(Object obj) {
		Employee e = (Employee) obj;
		if(this.employeeId == e.employeeId && this.employeeName.equals(e.employeeName) && this.employeeManagerId == e.employeeManagerId && this.employeeDept.equals(e.employeeDept)) {
			return true;
		}else {
			return false;
		}
	}
}
