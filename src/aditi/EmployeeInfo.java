package aditi;

public class EmployeeInfo {

	private int empId;
	private String empName;
	private int managerId;
	private String deptName;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public int hashCode() {
		return empId;
	}

	@Override
	public boolean equals(Object obj) {
		EmployeeInfo employeeInfo = (EmployeeInfo) obj;
		if ((this.empId == employeeInfo.empId) && (this.managerId == employeeInfo.managerId)
				&& (this.empName == employeeInfo.empName) && (this.deptName == employeeInfo.deptName))
			return true;
		else
			return false;
	}
}
