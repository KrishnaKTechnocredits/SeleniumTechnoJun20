package palak;

public class WebTableEmployeeManager {

	private int employeeId;
	private String employeeName;
	private String employeeManagerId;
	private String employeeDept;

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(obj == this)
			return true;
		if(getClass() != obj.getClass())
			return false;
		
		WebTableEmployeeManager e = (WebTableEmployeeManager) obj;
		if ((this.employeeId == e.employeeId) && (this.employeeName.equals(e.employeeName))
				&& (this.employeeManagerId.equals(e.employeeManagerId)) && (this.employeeDept.equals(e.employeeDept))) {
			return true;
		} else
			return false;
	}

	@Override
	public int hashCode() {
		return employeeId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId2) {
		this.employeeId = Integer.parseInt(employeeId2);
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeManagerId() {
		return employeeManagerId;
	}

	public void setEmployeeManagerId(String employeeManagerId) {
		this.employeeManagerId = employeeManagerId;
	}

	public String getEmployeeDept() {
		return employeeDept;
	}

	public void setEmployeeDept(String employeeDept) {
		this.employeeDept = employeeDept;
	}

}
