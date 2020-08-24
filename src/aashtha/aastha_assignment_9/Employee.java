package aashtha.aastha_assignment_9;

public class Employee {
	private int empId;
	private String empName, empManagerId, empDept;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpManagerId() {
		return empManagerId;
	}

	public void setEmpManagerId(String empManagerId) {
		this.empManagerId = empManagerId;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (object == this)
			return true;
		if (getClass() != object.getClass())
			return false;
		Employee employee = (Employee) object;
		return (this.getEmpId() == employee.getEmpId() && this.empName.equals(employee.empName)
				&& this.empManagerId.equals(employee.empManagerId) && this.empDept.equals(employee.empDept));
	}

	@Override
	public int hashCode() {
		return getEmpId();
	}
}
