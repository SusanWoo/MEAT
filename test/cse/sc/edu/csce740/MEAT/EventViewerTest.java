package cse.sc.edu.csce740.MEAT;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Cizhen Wu, Wenzhi Cai
 *
 */
public class EventViewerTest {
	private EventViewer ev;
	List<Arguments> list1 = new ArrayList<>();
	List<Arguments> list2 = new ArrayList<>();
	List<Arguments> list3 = new ArrayList<>();
	List<Arguments> list4 = new ArrayList<>();
	List<Arguments> list5 = new ArrayList<>();
	List<Arguments> list6 = new ArrayList<>();
	List<Arguments> list7 = new ArrayList<>();
	/**
	 * Constructor for the test
	 */
	public EventViewerTest() {
		ev = new EventViewer();

		// test case for adding legal universal schedule
		Arguments a = new Arguments("start-date", "11022016");
		list1.add(a);
		a = new Arguments("end-date", "12102016");
		list1.add(a);
		a = new Arguments("output-file", "out1");
		list1.add(a);
		
		// test case for adding illegal universal schedule
		Arguments a2 = new Arguments("start-date", "11022016");
		list2.add(a2);
		a2 = new Arguments("end-date", "");
		list2.add(a2);
		a2 = new Arguments("output-file", "");
		list2.add(a2);

		// test case for adding legal employee schedule
		Arguments a3 = new Arguments("start-date", "11022016");
		list3.add(a3);
		a3 = new Arguments("end-date", "12102016");
		list3.add(a3);
		a3 = new Arguments("output-file", "out1");
		list3.add(a3);
		a3 = new Arguments("employee-id", "gayxx0627");
		list3.add(a3);

		// test case for adding illegal employee schedule
		Arguments a4 = new Arguments("start-date", "");
		list4.add(a4);
		a4 = new Arguments("end-date", "12102016");
		list4.add(a4);
		a4 = new Arguments("output-file", "out1");
		list4.add(a4);
		a4 = new Arguments("employee-id", "");
		list4.add(a4);

		// test case for adding legal employee schedule
		Arguments a5 = new Arguments("start-date", "11022016");
		list5.add(a5);
		a5 = new Arguments("end-date", "12102016");
		list5.add(a5);
		a5 = new Arguments("output-file", "out1");
		list5.add(a5);
		a5 = new Arguments("room-id", "3A55");
		list5.add(a5);

		// test case for adding illegal employee schedule
		Arguments a6 = new Arguments("start-date", "");
		list6.add(a6);
		a6 = new Arguments("end-date", "");
		list6.add(a6);
		a6 = new Arguments("output-file", "");
		list6.add(a6);
		a6 = new Arguments("room-id", "");
		list6.add(a6);
		
		// end date is earlier than start date
		Arguments b = new Arguments("start-date", "11012016");
		list7.add(b);
		b = new Arguments("end-date", "10102016");
		list7.add(b);
		b = new Arguments("output-file", "testb");
		list7.add(b);
//		b = new Arguments("room-id", "");
//		list7.add(a6);
	}
	
	/**
	 * Tests printScheduleALl() method
	 */

	@Test
	public void testPrintScheduleAll() {
		try {
			ev.printScheduleAll(list1);
		} catch (Exception e) {
			fail();
		}
		assertTrue(true);

		try {
			ev.printScheduleAll(list2);
		} catch (Exception e) {
			assertTrue(true);
		}
		assertFalse(false);
		
	
	}
	
	/**
	 * Tests printScheduleEmployee() method
	 */
	@Test
	public void testPrintScheduleEmployee() {
		try {
			ev.printScheduleEmployee(list3);
		} catch (Exception e) {
			fail();
		}
		assertTrue(true);

		try {
			ev.printScheduleEmployee(list4);
		} catch (Exception e) {

		}
		assertFalse(false);
	}

	/**
	 * Tests printShceduleRoom() method
	 */
	@Test
	public void testPrintScheduleRoom() {
		try {
			ev.printScheduleRoom(list5);
		} catch (Exception e) {
			fail();
		}
		assertTrue(true);

		try {
			ev.printScheduleRoom(list6);
		} catch (Exception e) {

		}
		assertFalse(false);
	}


}
