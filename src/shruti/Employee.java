package shruti;

public class Employee {
	
	int empID;
	String empName;
	int managerId;
	String empDept;
	
	public static void main(String[] args) {
		
	}

	public int getEmpID() {
		return empID;
	}

	public void setEmpID(int empID) {
		this.empID = empID;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmoName(String empName) {
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
	public int hashCode(){
		return empID;
	}

	@Override
	public boolean equals(Object obj){
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Employee e = (Employee) obj;
		return (this.empID==e.empID)&& (this.empName.equals(e.empName)) && (this.managerId==e.managerId) && (this.empDept.equals(e.empDept));
	}

}
