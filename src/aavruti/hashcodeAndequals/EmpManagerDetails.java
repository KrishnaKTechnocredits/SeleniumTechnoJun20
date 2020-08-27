package aavruti.hashcodeAndequals;

public class EmpManagerDetails {

	private int empId;
	private String empName;
	private int managerId;
	private String empDept;
	
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
	
	public int getManagerId() {
		return managerId;
	}
	
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	public String getEmpDept() {
		return empDept;
	}
	
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	
	@Override
	public int hashCode() {
		return empId;
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o == null)
			return false;		
		if(o == this)
			return true;		
		if(getClass() != o.getClass())
			return false;
		
		EmpManagerDetails empDetails = (EmpManagerDetails) o;
		return (empDetails.empId == this.empId && 
				empDetails.empName.equals(this.empName) && 
				empDetails.managerId == this.managerId && 
				empDetails.empDept.equals(this.empDept));		
	}
}