package suparna.Assignment09;

import suparna.basics.base.PredefineAction;

public class EmpDetails extends PredefineAction {

	private int empID, empManagerID;
	private String empName, empDept;
	public int getEmpID() {
		return empID;
	}
	public int getEmpManagerID() {
		return empManagerID;
	}
	public String getEmpDept() {
		return empDept;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpManagerID(int empManagerID) {
		this.empManagerID = empManagerID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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

		EmpDetails empDetails = (EmpDetails) obj;
		if ((this.empID == empDetails.empID) && (this.empName.equals(empDetails.empName))
				&& (this.empManagerID == empDetails.empManagerID) && (this.empDept.equals(empDetails.empDept)))
			return true;
		else
			return false;
	}

}