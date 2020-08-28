package sonal;

public class Employee {


	
	int empID;
	String empName;
	int mngID;
	String deptName;
	
	public int setEmpID(int empID)
	{
		this.empID = empID;
		return empID;
	
	}
	
	public String setEmpName(String empName)
	{
		this.empName = empName;
		return empName;
	}
	
	public int setMNGID(int mngID)
	{
		this.mngID = mngID;
		return mngID;
	
	}
	
	public String setDeptName(String deptName)
	{
		this.deptName = deptName;
		return deptName;
	}
	
	public int getEmpID()
	{
		return empID;
	}
	
	public String getEmpName()
	{
		return empName;
	}
	
	public int getMNGID()
	{
		return mngID;
	}
	
	public String getDeptName()
	{
		return deptName;
	}

	@Override
	
	public int hashCode()
	{
		return empID;
	}
@Override
public boolean equals(Object obj)
{
 Employee e1 =(Employee) obj;
 if((this.empID == e1.empID) && (this.empName.equals(e1.empName)) && (this.mngID == e1.mngID) && (this.deptName.equals(e1.deptName)))
	 return true;
 else
	 return false;
}
 


}


