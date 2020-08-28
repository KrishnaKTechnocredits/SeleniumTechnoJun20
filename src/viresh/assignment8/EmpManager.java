package viresh.assignment8;

public class EmpManager {

	int empId;
	String eName;
	String eManagerId;
	String eDept;

	int compareEmpId(int eid) {
		this.empId = eid;
		return empId;
	}

	String compareEmpName(String ename) {
		this.eName = ename;
		return eName;
	}

	String compareManager(String eMan) {
		this.eManagerId = eMan;
		return eManagerId;
	}

	String compareDept(String dept) {
		this.eDept = dept;
		return eDept;
	}

	@Override
	public int hashCode() {
		return empId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpManager emp = (EmpManager) obj;
		return this.empId==emp.empId && this.eName.equals(emp.eName) && this.eManagerId.equals(emp.eManagerId)
				&& this.eDept.equals(emp.eDept);
	}
}
