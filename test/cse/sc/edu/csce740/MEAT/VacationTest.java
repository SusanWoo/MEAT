package cse.sc.edu.csce740.MEAT;

import cse.sc.edu.csce740.MEAT.*;
import cse.sc.edu.csce740.MEAT.exception.*;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * 
 * @author Cizhen Wu, Wenzhi Cai
 *
 */

public class VacationTest {
	Vacation v = new Vacation();
	Vacation v1 = new Vacation();

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	
	/**
	 * Tests setDate() method
	 */
	@Test
	public void testSetDate() {
		// test length
		try {
			v.setStartDate("2016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof VacationException);
		}
		// test month
		try {
			v.setStartDate("13062016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof VacationException);
		}

		try {
			v.setStartDate("00122016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof VacationException);
		}
		// test day
		try {
			v.setStartDate("12322016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof VacationException);
		}

		try {
			v.setStartDate("12002016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof VacationException);
		}
		// test past

		try {
			LocalDateTime now = LocalDateTime.now();
			v.setStartDate("04181800");
			// m.setDate("12012016");
			// fail();
		} catch (Exception e) {
			assertFalse("not pass setDateTest", e instanceof VacationException);
		}

		try {
			LocalDateTime now = LocalDateTime.now();
			v.setStartDate("12012016");

		} catch (Exception e) {
			fail();
		}
		assertEquals(20161201, v.getStartDate());
		
		
		// test length
		try {
			v.setEndDate("2016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof VacationException);
		}
		// test month
		try {
			v.setEndDate("13062016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof VacationException);
		}

		try {
			v.setEndDate("00122016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof VacationException);
		}
		// test day
		try {
			v.setEndDate("12322016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof VacationException);
		}

		try {
			v.setEndDate("12002016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof VacationException);
		}
		
		//test endDate later than startDate
		try{
			v.setStartDate("12010216");
			v.setEndDate("11302016");
			fail();
		}
		catch(Exception e){
			assertTrue("not pass setEndDate", e instanceof VacationException);
		}
		
		try{
			v.setStartDate("12012016");
			v.setEndDate("12302016");
			//fail();
		}
		catch(Exception e){
			fail();//assertTrue("not pass setEndDate", e instanceof VacationException);
		}
		assertEquals(20161230, v.getEndDate());

	}
	
	/**
	 * Tests setEmployee() method
	 */
	@Test
	public void testEmployee(){
		try{
			v.setEmployee("gayxx067");
		}
		catch(Exception e){
			fail();
		}
		assertEquals("gayxx067", v.getEmployee());
	}
	
	/**
	 * Tests setDescription() method
	 */
	@Test
	public void testSetDescription(){
		
		try{
			v.setDescription("Status update");
			
		}
		catch (Exception e) {
			fail();
		}
		assertEquals("Status update", v.getDescription());
	
	}
}
