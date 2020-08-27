package raj;

public class EmployeeManagerTable {

	private int empID;
	private String empName;
	private int empManagerID;
	private String empDept;
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
	public int getEmpManagerID() {
		return empManagerID;
	}
	public void setEmpManagerID(int empManagerID) {
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeManagerTable employee = (EmployeeManagerTable) obj;
		return (this.empID == employee.empID) && (this.empName.equals(employee.empName))
				&& (this.empManagerID == employee.empManagerID) && (this.empDept.equals(employee.empDept));
	}

	@Override
	public int hashCode() {
		return empID;
	}

}
