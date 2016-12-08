package cse.sc.edu.csce740.MEAT;

import java.util.List;

import cse.sc.edu.csce740.MEAT.exception.HolidayException;
import cse.sc.edu.csce740.MEAT.exception.MeetingException;
import cse.sc.edu.csce740.MEAT.exception.VacationException;


public class EventScheduler {
	private Events events;
	
	/**
     * Constructor for the event scheduler
     *
     */
	public EventScheduler() {
		events = new Events();
	}
	
	/**
	 * Returns true if the meeting is added to the
	 * list of meetings in the Events and false
	 * otherwise.
	 * @param aList
	 * @return boolean
	 */
	public boolean addMeeting(List<Arguments> aList) {
		Meeting m = new Meeting();
		String date = "";
		String startTime = "";
		String endTime = "";
		String room = "";
		String attendee = "";
		String description = "";
		try {
			for (Arguments a : aList) {
				switch (a.name) {
					case "date" :
						date = a.value;
						break;
					case "start-time":
						startTime = a.value;
						break;
					case "end-time":
						endTime = a.value;
						break;
					case "room-id":
						room = a.value;
						break;
					case "attendee":
						attendee += a.value + ",";
						break;
					case "description":
						description = a.value;
						break;
				}
			}
			m.setDate(date);
			m.setStartTime(startTime);
			m.setEndTime(endTime);
			m.setRoom(room);
			m.setAttendee(attendee);
			m.setDescription(description);
			
			return events.addMeeting(m);
		} catch (MeetingException e) {
//			hasError = true;
			System.out.println(e.getMessage());
			return false;
		}	
	}
	
	/**
	 * Returns true if the vacation is added to the
	 * list of vacations in the Events and false
	 * otherwise.
	 * @param v
	 * @return boolean
	 */
	public boolean addVacation(List<Arguments> aList) {
		Vacation v = new Vacation();
		String employeeId = "";
		String startDate = "";
		String endDate = "";
		String description = "";
		try {
			for (Arguments a : aList) {
				switch (a.name) {
					case "employee-id":
						employeeId = a.value;
						break;
					case "start-date":
						startDate = a.value;
						break;
					case "end-date":
						endDate = a.value;
						break;
					case "description":
						description = a.value;
						break;
				}
			}
			
			v.setEmployee(employeeId);
			v.setStartDate(startDate);
			v.setEndDate(endDate);
			v.setDescription(description);
			
			return events.addVacation(v);
		} catch (VacationException e) {
			
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
	}
	
	/**
	 * Returns true if the holiday is added to the holiday database
	 * otherwise.
	 * @param h
	 * @return boolean
	 */
	public boolean addHoliday(List<Arguments> aList) {
		Holiday h = new Holiday();
		String startDate = "";
		String endDate = "";
		String description = "";
		
		try {
			for (Arguments a : aList) {
				switch (a.name) {
					case "start-date":
						startDate = a.value;
						break;
					case "end-date":
						endDate = a.value;
						break;
					case "description":
						description = a.value;
						break;
				}
			}
			
			h.setStartDate(startDate);
			h.setEndDate(endDate);
			h.setDescription(description);
			
			return events.addHoliday(h);
		} catch (HolidayException e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}
	}
	
	/**
	 * Returns the list of meeting in the Meeting database.
	 * @return List<List<String>>
	 */
	public synchronized List<List<String>> getMeetings() {
		return events.getMeetings();
	}
	
	/**
	 * Returns the list of vacation in the Vacation database.
	 * @return List<List<String>>
	 */
	public synchronized List<List<String>> getVacations() {
		return events.getVacations();
	}
	
	/**
	 * Returns the list of holiday in the Holiday database.
	 * @return List<List<String>>
	 */
	public synchronized List<List<String>> getHolidays() {
		return events.getHolidays();
	}
	
	
	/**
	 * Returns the meeting id of the successfully deleted meeting
	 * or null if the meeting cannot be deleted.
	 * 
	 * @param meetingToDelete
	 * @return boolean
	 */
	public boolean deleteMeeting(List<Arguments> aList) {
		String id = aList.get(0).value;
		return events.deleteMeeting(id);
	}
	
	/**
	 * Returns the id of the successfully edited meeting
	 * or null if the meeting cannot be edited.
	 * @param List<Arguments>
	 * @return boolean
	 */
	public boolean editMeeting(List<Arguments> aList) {
						String meetingId = "";
						String date = "";
						String startTime = "";
						String endTime = "";
						String room = "";
						String description = "";
		
		Meeting m = new Meeting();
		try {
			for (Arguments a : aList) {
				switch (a.name) {
					case "meeting-id":
						meetingId = a.value;
						break;
					case "date" :
						date = a.value;
						break;
					case "start-time":
						startTime = a.value;
						break;
					case "end-time":
						endTime = a.value;
						break;
					case "room-id":
						room = a.value;
						break;
					case "description":
						description = a.value;
						break;
				}
			}
			
			m.setId(meetingId);
			if (!date.equals("")) {
				m.setDate(date);
			}
			if (!startTime.equals("")) {
				m.setStartTime(startTime);
			}
			if (!endTime.equals("")) {
				m.setEndTime(endTime);
			}
			if (!room.equals("")) {
				m.setRoom(room);
			}
			if (!description.equals("")) {
				m.setDescription(description);
			}
			
			return events.editMeeting(m);
		} catch (MeetingException e) {
//			hasError = true;
			System.out.println(e.getMessage());
			return false;
		}	
		
	}
	
	/**
	 * Returns the id of the successfully added attendees for meeting
	 * or null if the attendees cannot be added.
	 * @param List<Arguments>
	 * @return boolean
	 */
	public boolean editMeetingAddAttendee(List<Arguments> aList) {
		Meeting m = new Meeting();
		String meetingId = "";
		String attendee = "";
		try {
			for (Arguments a : aList) {
				switch (a.name) {
				case "meeting-id" :
					meetingId = a.value;
					break;
				case "attendee" :
					attendee = a.value;
					break;
				}
			}
			m.setId(meetingId);
			m.setAttendee(attendee);
			return events.editMeetingAddAttendee(m);
			
		} catch (MeetingException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Returns the id of the successfully added attendees for meeting
	 * or null if the attendees cannot be added.
	 * @param List<Arguments>
	 * @return boolean
	 */
	public boolean editMeetingRemoveAttendee(List<Arguments> aList) {
		Meeting m = new Meeting();
		String meetingId = "";
		String attendee = "";
		try {
			for (Arguments a : aList) {
				switch (a.name) {
				case "meeting-id" :
					meetingId = a.value;
					break;
				case "attendee" :
					attendee = a.value;
					break;
				}
			}
			m.setId(meetingId);
			m.setAttendee(attendee);
			return events.editMeetingRemoveAttendee(m);
			
		} catch (MeetingException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
