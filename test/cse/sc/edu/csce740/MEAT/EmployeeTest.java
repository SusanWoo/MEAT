package cse.sc.edu.csce740.MEAT;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Cizhen Wu, Wenzhi Cai
 * Tests the Employee class
 */

public class EmployeeTest {
	Employee employee = new Employee();
	
	/**
	 * Tests the setEmployeeId method
	 */
	
	@Test
	public void testSetEmployeeId() {
		try {
			employee.setEmployeeId("gayxx0627");
		} catch (Exception e) {
			fail();
		}
		assertEquals("gayxx0627",employee.getEmployeeId());
	}
	
	/**
	 * Tests the setEmployeeName method
	 */
	
	@Test
	public void testSetEmployeeName() {
		try {
			employee.setEmployeeName("gayxx0627");
		} catch (Exception e) {
			fail();
		}
		assertEquals("gayxx0627",employee.getEmployeeName());
	}
	
}
