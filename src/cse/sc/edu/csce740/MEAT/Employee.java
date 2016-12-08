package cse.sc.edu.csce740.MEAT;


public class Employee {
	private String pEmployeeId;
	private String pEmployeeName;
	
	/**
     * Creates a default Employee for MEAT.
     */
	public Employee() {
		pEmployeeId = "";
		pEmployeeName = "";
	}
	
	/**
	 * @return   Returns the employee id.
	 */
    public String getEmployeeId() {
		return pEmployeeId;
	}
    
    /**
	 * @param employeeId   Sets the pEmployeeId.
	 */
    public void setEmployeeId(String employeeId) {
    	this.pEmployeeId = employeeId;
	}
    
    /**
	 * @return   Returns the employee name.
	 */
    public String getEmployeeName() {
		return pEmployeeName;
	}
    
    /**
	 * @param eName   Sets the pEmployeeName.
	 */
    public void setEmployeeName(String eName){
    	this.pEmployeeName = eName;
	}
	
}
