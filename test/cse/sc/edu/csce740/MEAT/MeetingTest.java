package cse.sc.edu.csce740.MEAT;

import cse.sc.edu.csce740.MEAT.*;
import cse.sc.edu.csce740.MEAT.exception.*;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * 
 * @author Cizhen Wu, Wenzhi Cai
 *
 */
public class MeetingTest {
	Meeting m = new Meeting();
	Meeting m1 = new Meeting();
	Meeting m2 = new Meeting();
	// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	private boolean isFuture;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	LocalDateTime now = LocalDateTime.now();
	String strNow = dtf.format(now);

	// String strNow = dtf.format(now);
	/**
	 * Tests setmeetingId() method
	 */
	@Test
	public void testMeetingId(){
		try{
			m.setId("1");
		}
		catch (Exception e){
			fail();
		}
		assertEquals(1,m.getId());
		
	}
	
	/**
	 * Tests setDate() method
	 */
	@Test
	public void testSetDate() {
		// test length
		try {
			m.setDate("2016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof MeetingException);
		}
		// test month
		try {
			m.setDate("13062016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof MeetingException);
		}

		try {
			m.setDate("00122016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof MeetingException);
		}
		// test day
		try {
			m.setDate("12322016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof MeetingException);
		}

		try {
			m.setDate("12002016");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof MeetingException);
		}
		// test past

		try {	
			m.setDate("04181800");	
			fail();
		} catch (Exception e) {
			assertTrue("not pass setDateTest", e instanceof MeetingException);
		}

		try {
			// LocalDateTime now = LocalDateTime.now();
			m.setDate("12102016");

		} catch (Exception e) {
			fail();
		}
		assertEquals(20161210, m.getDate());

	}
	
	/**
	 * Tests setTime() method
	 */
	@Test
	public void testSetTime() {
		// test time length and :
		try {
			m1.setStartTime("15:234");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setStartTimeTest", e instanceof MeetingException);
		}
		try {
			m1.setStartTime("15245");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setStartTimeTest", e instanceof MeetingException);
		}

		try {
			m1.setDate(strNow.substring(4, 6) + strNow.substring(6, 8) + strNow.substring(0, 4));
			m1.setStartTime("24:00");
			// fail();
		} catch (Exception e) {
			fail();
		}
		assertEquals(2400, m1.getStartTime());

		try {
			m1.setDate(strNow.substring(4, 6) + strNow.substring(6, 8) + strNow.substring(0, 4));
			m1.setStartTime("00:00");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setStartTimeTest", e instanceof MeetingException);
		}

		try {
			m1.setDate("12102016");
			m1.setStartTime("15:30");
		} catch (Exception e) {
			fail();
		}
		assertEquals(1530, m1.getStartTime());

		// test time length and :
		try {
			m1.setDate("12012016");
			m1.setEndTime("16:234");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setStartTimeTest", e instanceof MeetingException);
		}
		try {
			m1.setEndTime("16245");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setStartTimeTest", e instanceof MeetingException);
		}

		// earlier than start time
		try {
			m1.setStartTime("15:30");
			m1.setEndTime("12:00");
			fail();
		} catch (Exception e) {
			assertTrue("not pass setEndTime", e instanceof MeetingException);
		}

		try {
			m1.setStartTime("15:30");
			m1.setEndTime("16:30");
		} catch (Exception e) {
			// assertTrue("not pass setEndTime", e instanceof MeetingException);
			fail();
		}
		assertEquals(1630, m1.getEndTime());
	}

	
	/**
	 * Tests setDescription() method
	 */

	@Test

	public void testSetDescription() {
		try {
			m.setDescription("");
			fail();
		} catch (Exception e) {
			assertTrue("not pass description", e instanceof MeetingException);
		}

		try {
			m.setDescription("Status update");

		} catch (Exception e) {
			fail();
		}
		assertEquals("Status update", m.getDescription());

	}


	/**
	 * Tests setAttendees() method
	 */
	@Test
	public void testSetAttendees() {
		try {
			m.setAttendee("gayxx067,bob099");
		} catch (Exception e) {
			fail();
		}
		assertEquals("gayxx067,bob099,", m.getAttendee());
		

	}

	/**
	 * Tests setRoomId() method
	 */
	@Test
	public void testSetRoomId(){
		try{
			m.setDate("12102016");
			m.setStartTime("12:30");
			m.setEndTime("13:30");
			m.setRoom("3D11");
		}
		catch (Exception e){
			fail();
		}
		assertEquals("3D11", m.getRoom());
		
	}

}
