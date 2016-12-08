package cse.sc.edu.csce740.MEAT;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import cse.sc.edu.csce740.MEAT.*;
import cse.sc.edu.csce740.MEAT.exception.HolidayException;

/**
 * 
 * @author Cizhen Wu, Wenzhi Cai
 *
 */

public class HolidayTest {
	Holiday h = new Holiday();
	Holiday h1 = new Holiday();

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

	/**
	 * Tests setDate() method
	 */
	@Test
	public void testSetDate() {
		// test length
		try {
			h.setStartDate("2016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof HolidayException);
		}
		// test month
		try {
			h.setStartDate("13062016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof HolidayException);
		}

		try {
			h.setStartDate("00122016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof HolidayException);
		}
		// test day
		try {
			h.setStartDate("12322016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof HolidayException);
		}
		
		try {
			h.setStartDate("12002016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof HolidayException);
		}
		// test past

		try {
			LocalDateTime now = LocalDateTime.now();
			h.setStartDate("04181800");
			// m.setDate("12012016");
			// fail();
		} catch (Exception e) {
			assertFalse("not pass setDateTest", e instanceof HolidayException);
		}

		try {
			LocalDateTime now = LocalDateTime.now();
			h.setStartDate("12012016");

		} catch (Exception e) {
			fail();
		}
		assertEquals(20161201, h.getStartDate());
		
		
		// test length
		try {
			h.setEndDate("2016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof HolidayException);
		}
		// test month
		try {
			h.setEndDate("13062016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof HolidayException);
		}

		try {
			h.setEndDate("00122016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof HolidayException);
		}
		// test day
		try {
			h.setEndDate("12322016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof HolidayException);
		}

		try {
			h.setEndDate("12002016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndDate", e instanceof HolidayException);
		}
		
		//test endDate later than startDate
		try{
			h.setStartDate("12010216");
			h.setEndDate("11302016");
			fail();
		}
		catch(Exception e){
			assertTrue("not pass setEndDate", e instanceof HolidayException);
		}
		
		try{
			h.setStartDate("12012016");
			h.setEndDate("12302016");
			//fail();
		}
		catch(Exception e){
			fail();//assertTrue("not pass setEndDate", e instanceof VacationException);
		}
		assertEquals(20161230, h.getEndDate());

	}
	
	/**
	 * Tests setDesciprtion() method
	 */
	@Test
	public void testSetDescription(){
		
		try{
			h.setDescription("Happy holiday!");
			
		}
		catch (Exception e) {
			fail();
		}
		assertEquals("Happy holiday!", h.getDescription());
	
	}
}

