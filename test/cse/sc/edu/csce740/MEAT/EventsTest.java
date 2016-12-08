package cse.sc.edu.csce740.MEAT;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.management.MBeanException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import cse.sc.edu.csce740.MEAT.exception.MeetingException;

/**
 * 
 * @author Cizhen Wu, Wenzhi Cai
 *
 */

public class EventsTest {
	private Events event;
	private Meeting m;
	private Holiday h;
	private Vacation v;

	/*
	 * Construct the EventsTest class
	 */
	public EventsTest() {
		event = new Events();
		List<String> mList = new ArrayList<String>();
		List<List<String>> meetings = new ArrayList<>();

		String mDate = new String("12012016");
		mList.add(mDate);
		String mStartTime = new String("10:00");
		mList.add(mStartTime);
		String mEndTime = new String("12:00");
		mList.add(mEndTime);
		meetings.add(mList);

	}

	/**
	 * Tests getMeetings method
	 */
	@Test
	public void testGetMeetings() {
		try {
			event.getMeetings();
		} catch (Exception e) {
			fail();
		}
	}		
	
	/**
	 * Tests editMeeting extreme parameter method
	 */
	@Test
	public void testEditMeeting() throws MeetingException{
		event.getMeetings();
		boolean edited = false;
		Meeting meetingToedit = new Meeting();
		meetingToedit.setId("-1");
		edited = event.editMeeting(meetingToedit);
		assertFalse(edited);
		
		edited = event.editMeetingAddAttendee(meetingToedit);
		assertFalse(edited);
		
		edited = event.editMeetingRemoveAttendee(meetingToedit);
		assertFalse(edited);		
	}

	
}