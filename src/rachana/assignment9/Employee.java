package rachana.assignment9;

public class Employee {
	
	private String employeeId;
	private String employeeManagerId;
	private String employeeName;
	private String employeeDepartment;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeManagerId() {
		return employeeManagerId;
	}
	public void setEmployeeManagerId(String employeeManagerId) {
		this.employeeManagerId = employeeManagerId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeDepartment() {
		return employeeDepartment;
	}
	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeDepartment == null) ? 0 : employeeDepartment.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((employeeManagerId == null) ? 0 : employeeManagerId.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (employeeDepartment == null && other.employeeDepartment != null) {
				return false;
		} else if (!employeeDepartment.equals(other.employeeDepartment))
			return false;
		if (employeeId == null && other.employeeId != null ) {
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (employeeManagerId == null && other.employeeManagerId != null) {
				return false;
		} else if (!employeeManagerId.equals(other.employeeManagerId))
			return false;
		if (employeeName == null && other.employeeName != null ) {
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		return true;
	}
	
	
	
}
