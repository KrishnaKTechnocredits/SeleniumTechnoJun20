package barkha;

public class EmployeeRowsValidation_Employee {
	
	private int empID;
	private String empName, empManagerID, empDept;
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpManagerID() {
		return empManagerID;
	}
	public void setEmpManagerID(String empManagerID) {
		this.empManagerID = empManagerID;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (getClass() != obj.getClass())
			return false;
		
		EmployeeRowsValidation_Employee employee = (EmployeeRowsValidation_Employee) obj;
		
		return (this.empID== employee.empID && this.empName.equals(employee.empName)
				&& this.empManagerID.equals(employee.empManagerID) && this.empDept.equals(employee.empDept));
	}
	@Override
	public int hashCode() {
		return empID;
	}
}
