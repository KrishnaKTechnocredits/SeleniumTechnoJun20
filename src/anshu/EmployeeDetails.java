package anshu;

public class EmployeeDetails {
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
	public int hashCode() {
		return empID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDetails empManager = (EmployeeDetails) obj;
		return (this.empID == empManager.empID) && (this.empName.equals(empManager.empName))
				&& (this.empManagerID == empManager.empManagerID) && (this.empDept.equals(empManager.empDept));
	}

}
