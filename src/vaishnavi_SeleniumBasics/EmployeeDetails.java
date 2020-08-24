package vaishnavi_SeleniumBasics;

public class EmployeeDetails {

	private int empId;
	private String empName;
	private int empManagerId;
	private String empDepartmentName;

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpManagerId(int empManagerId) {
		this.empManagerId = empManagerId;
	}

	public int getEmpManagerId() {
		return empManagerId;
	}

	public void setEmpDepartmentName(String empDepartmentName) {
		this.empDepartmentName = empDepartmentName;
	}

	public String getEmpDepartmentName() {
		return empDepartmentName;
	}
	
	@Override
	public int hashCode() {
		return empId;
	}	

	@Override
	public boolean equals(Object obj) {
		EmployeeDetails employeeDetails1 = (EmployeeDetails) obj;
		
		if(this.empId == employeeDetails1.empId && this.empName.equals(employeeDetails1.empName) && this.empManagerId == employeeDetails1.empManagerId && this.empDepartmentName.equals(employeeDetails1.empDepartmentName))
			return true;
		else
			return false;
			
	}
}