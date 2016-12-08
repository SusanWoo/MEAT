package cse.sc.edu.csce740.MEAT;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cse.sc.edu.csce740.MEAT.exception.*;


/**
 * 
 * @author Cizhen Wu, Wenzhi Cai
 * Tests EventScheduler class
 *
 */

public class EventSchedulerTest {
	private EventScheduler es;

	List<Arguments> aList = new ArrayList<>();
	List<Arguments> aList1 = new ArrayList<>();
	List<Arguments> aList1_1 = new ArrayList<>();
	List<Arguments> aList1_2 = new ArrayList<>();
	List<Arguments> aList2 = new ArrayList<>();
	List<Arguments> aList3 = new ArrayList<>();
	List<Arguments> aList4 = new ArrayList<>();
	List<Arguments> aList5 = new ArrayList<>();
	List<Arguments> aList6 = new ArrayList<>();
	List<Arguments> aList7 = new ArrayList<>();
	List<Arguments> aList8 = new ArrayList<>();
	List<Arguments> aList9 = new ArrayList<>();
	List<Arguments> aList10 = new ArrayList<>();
	List<Arguments> aList11 = new ArrayList<>();
	List<Arguments> aList12 = new ArrayList<>();
	List<Arguments> aList13 = new ArrayList<>();
	List<Arguments> aList14 = new ArrayList<>();
	List<Arguments> aList15 = new ArrayList<>();
	List<Arguments> aList16 = new ArrayList<>();
	List<Arguments> aList17 = new ArrayList<>();
	List<Arguments> deletelist1 = new ArrayList<>();
	List<Arguments> deletelist2 = new ArrayList<>();
	

	public EventSchedulerTest() {
		es = new EventScheduler();

		/**
		 * test cases for add events
		 */

		// test arguments for addMeeting illegally
		Arguments a = new Arguments("date", "12032016");
		aList.add(a);
		a = new Arguments("start-time", "10:00");
		aList.add(a);
		a = new Arguments("end-time", "16:00");


		// test arguments for addMeeting successfully
		Arguments b = new Arguments("date", "12102016");
		aList1.add(b);
		b = new Arguments("start-time", "10:00");
		aList1.add(b);
		b = new Arguments("end-time", "16:00");
		aList1.add(b);
		b = new Arguments("room-id", "3D11");
		aList1.add(b);
		b = new Arguments("attendee", "gayxx0627");
		aList1.add(b);
		b = new Arguments("description", "hello");
		aList1.add(b);

		// test arguments for addVacation illegally
		Arguments d = new Arguments("employee-id", "gayxx0627");
		aList3.add(d);
		d = new Arguments("start-date", "12102016");
		aList3.add(d);
		d = new Arguments("end-date", "11012016");
		aList3.add(d);
		d = new Arguments("description", "happy");
		aList3.add(d);

		// test arguments for addVacation
		Arguments c = new Arguments("employee-id", "gayxx0627");
		aList2.add(c);
		c = new Arguments("start-date", "12152016");
		aList2.add(c);
		c = new Arguments("end-date", "12202016");
		aList2.add(c);
		c = new Arguments("description", "happy");
		aList2.add(c);

		// test arguments for addHoliday illegally-invalid end date
		Arguments f = new Arguments("start-date", "12102016");
		aList5.add(f);
		f = new Arguments("end-date", "11032016");
		aList5.add(f);
		f = new Arguments("description", "thanksgiving");
		aList5.add(f);

		// test arguments for addHoliday 
		Arguments e = new Arguments("start-date", "12102016");
		aList4.add(e);
		e = new Arguments("end-date", "12152016");
		aList4.add(e);
		e = new Arguments("description", "thanksgiving");
		aList4.add(e);

		/**
		 * test cases for edit events
		 */
	

		// TC1 successfully edit meeting
		Arguments em1 = new Arguments("meeting-id", "1");
		aList7.add(em1);
		em1 = new Arguments("date", "12102016");
		aList7.add(em1);
		em1 = new Arguments("start-time", "12:30");
		aList7.add(em1);
		em1 = new Arguments("end-time", "13:30");
		aList7.add(em1);
		em1 = new Arguments("room-id", "3D15");
		aList7.add(em1);
		em1 = new Arguments("description", "haha");
		aList7.add(em1);
		
		// TC2 unsuccessfully edit meeting -invalid date
		Arguments em = new Arguments("meeting-id", "1");
		aList6.add(em);
		em = new Arguments("date", "12032016");
		aList6.add(em);
		em = new Arguments("start-time", "11:30");
		aList6.add(em);
		em = new Arguments("end-time", "14:30");
		aList6.add(em);
		em = new Arguments("room-id", "3D11");
		aList6.add(em);
		em = new Arguments("description", "hello");
		aList6.add(em);
		
		// TC3 unsuccessfully edit meeting -invalid meetingid
		Arguments em4 = new Arguments("meeting-id", "");
		aList10.add(em4);
		em4 = new Arguments("date", "12102016");
		aList10.add(em4);
		em4 = new Arguments("start-time", "12:30");
		aList10.add(em4);
		em4 = new Arguments("end-time", "14:30");
		aList10.add(em4);
		em4 = new Arguments("room-id", "3D11");
		aList10.add(em4);
		em4 = new Arguments("description", "hello");
		aList10.add(em4);
		
		// TC4 edit meeting -nothing change
		Arguments em5 = new Arguments("meeting-id", "1");
		aList11.add(em5);
		em5 = new Arguments("date", "");
		aList11.add(em5);
		em5 = new Arguments("start-time", "");
		aList11.add(em5);
		em5 = new Arguments("end-time", "");
		aList11.add(em5);
		em5 = new Arguments("room-id", "");
		aList11.add(em5);
		em5 = new Arguments("description", "");
		aList11.add(em5);
	
		// TC5 edit meeting -illegal meeting end time
		Arguments em11 = new Arguments("meeting-id", "1");
		aList17.add(em11);
		em11 = new Arguments("date", "13012016");
		aList17.add(em11);
		em11 = new Arguments("start-time", "10:00");
		aList17.add(em11);
		em11 = new Arguments("end-time", "9:00");
		aList17.add(em11);
		em11 = new Arguments("room-id", "");
		aList17.add(em11);
		em11 = new Arguments("description", "");
		aList17.add(em11);

	/**
	 * test cases for edit meeting add attendee
	*/
		// edit
		Arguments em2 = new Arguments("meeting-id", "2");
		aList8.add(em2);
		em2 = new Arguments("attendee", "mario");
		aList8.add(em2);

		Arguments em3 = new Arguments("meeting-id", "1");
		aList9.add(em3);

		Arguments em6 = new Arguments("meeting-id", "");
		aList12.add(em6);
		em6 = new Arguments("attendee", "mario");
		aList12.add(em6);

		Arguments em7 = new Arguments("meeting-id", "1");
		aList13.add(em7);
		em7 = new Arguments("attendee", "mario");
		aList13.add(em7);

		Arguments em8 = new Arguments("meeting-id", "8");
		aList14.add(em8);
		em8 = new Arguments("attendee", "gayxx067");
		aList14.add(em8);

		/**
		 * // * test cases for edit meeting remove attendee //
		 */
		Arguments em9 = new Arguments("meeting-id", "1");
		aList15.add(em9);
		em9 = new Arguments("attendee", "manager");
		aList15.add(em9);

		Arguments em10 = new Arguments("meeting-id", "200");
		aList16.add(em10);
		em10 = new Arguments("attendee", "manager");
		aList16.add(em10);

		/**
		 * test cases for delete meeting
		 */
		Arguments dList = new Arguments("meeting-id", "2");
		deletelist1.add(dList);
		
		Arguments dList1 = new Arguments("meeting-id", "200");
		deletelist2.add(dList1);

	}
	
	/**
	 * Tests addEvent method
	 */

	@Test
	public void testAddEvents() {
		try {
			es.addMeeting(aList);
			es.getMeetings();
		} catch (Exception e) {
			fail();
		}
		assertFalse(false);

		try {
			es.addMeeting(aList1);
		} catch (Exception e) {
			fail();

		}

		try {
			es.addVacation(aList3);
		} catch (Exception e) {
			fail();
		}
		assertFalse(false);

		try {
			es.addVacation(aList2);
			es.getVacations();
		} catch (Exception e) {
			fail();
		}

		try {
			es.addHoliday(aList5);
		} catch (Exception e) {
			fail();
		}
		assertFalse(false);

		try {
			es.addHoliday(aList4);
			es.getHolidays();
		} catch (Exception e) {
			fail();
		}

	}

	/**
	 * Tests editMeeting method
	 */
	
	@Test
	public void testEditMeeting() {
		try {
			es.editMeeting(aList6);
		} catch (Exception e) {
			fail();
		}

		try {
			es.editMeeting(aList7);
		} catch (Exception e) {
			fail();
		}
		assertFalse(false);

		try {
			es.editMeeting(aList10);
			fail();
		} catch (Exception e) {
			assertFalse(false);
			
		}

		try {
			es.editMeeting(aList11);
		} catch (Exception e) {
			
		}

	}
	
	/**
	 * Tests editMeetingAddAttendee method
	 */

	@Test
	public void testEditMeetingAddAttendee() {
		try {
			es.editMeetingAddAttendee(aList8);
		} catch (Exception e) {
			fail();
		}

		try {
			es.editMeetingAddAttendee(aList12);
			fail();
		} catch (Exception e) {
			assertFalse(false);
			
		}

		try {
			es.editMeetingAddAttendee(aList13);
			
		} catch (Exception e) {
			
		}
		assertFalse(false);

		try {
			es.editMeetingAddAttendee(aList14);
			
		} catch (Exception e) {
			
		}
		assertFalse(false);

		try {
			es.editMeetingAddAttendee(aList16);
		} catch (Exception e) {
			
		}
		assertFalse(false);
	}

	/**
	 * Tests editMeetingRemoveAttendee method
	 */
	@Test
	public void testEditMeetingRemoveAttendee() {
		try {
			es.editMeetingRemoveAttendee(aList15);
		} catch (Exception e) {
			fail();
		}

		try {
			es.editMeetingRemoveAttendee(aList16);
		} catch (Exception e) {
			
		}
		assertFalse(false);

	}
	
	/**
	 * Tests deleteMeeting method
	 */
	@Test
	public void testDeleteMeeting() {
		try {
			es.deleteMeeting(deletelist1);

		} catch (Exception e) {
			fail();
		}

		try {
			es.deleteMeeting(deletelist2);
		} catch (Exception e) {
			fail();
		}
		assertFalse(false); // meeting not in database;
	}

}
