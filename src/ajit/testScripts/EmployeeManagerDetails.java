package ajit.testScripts;

public class EmployeeManagerDetails {
	private int empId;
	private String empName;
	private int managerId;
	private String managerName;

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

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerName() {
		return managerName;
	}

	@Override
	public int hashCode() {
		return empId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (getClass() != obj.getClass())
			return false;
		EmployeeManagerDetails emp = (EmployeeManagerDetails) obj;
		return (this.empId == emp.empId && this.empName.equals(emp.empName) && this.managerId == emp.managerId
				&& this.managerName.equals(emp.managerName));
	}
}
