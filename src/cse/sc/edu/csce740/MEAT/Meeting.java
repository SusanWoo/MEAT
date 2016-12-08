package cse.sc.edu.csce740.MEAT;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import cse.sc.edu.csce740.MEAT.exception.MeetingException;

public class Meeting {
	private int pMeetingId;
	private int pDate;
	private int pStartTime;
	private int pEndTime;
	private String pDescription;
	private String pAttendees;
	private String pRoomId;
	
	private boolean isFuture;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	LocalDateTime now = LocalDateTime.now();
	String strNow = dtf.format(now);
	
	
	/**
     * Creates a default Meeting for MEAT.
     */
    public Meeting() {
    	this.pMeetingId = 0;
    	this.pDate = 0;
    	this.pStartTime = 0;
    	this.pEndTime = 0;
    	this.pDescription = "";
    	this.pAttendees = "";
    	this.pRoomId = "";
    	
    	isFuture = false;
    }
    
    /**
	 * @return   Returns the id.
	 */
    public int getId() {
		return pMeetingId;
	}
    
    /**
     * Sets the meeting id
     * @param meetingId
	 * @return  void.
	 */
    public void setId(String meetingId) {
		this.pMeetingId = Integer.parseInt(meetingId);
	}
    
    
    /**
	 * @return   Returns the meeting date.
	 */
    public int getDate() {
		return pDate;
	}
    
    /**
	 * @param date   Sets the meeting date.
	 */
    public void setDate(String date) throws MeetingException {
    	//Checks date is valid
    	if (date.length() != 8) {
    		throw new MeetingException("Input date is invalid.");
    	}
    
    	//Checks month
    	if (Integer.parseInt(date.substring(0, 2)) > 12 || Integer.parseInt(date.substring(0, 2)) < 1) {
    		throw new MeetingException("Input date is invalid.");
    	}
    	
    	//Checks day
    	if (Integer.parseInt(date.substring(2, 4)) > 31 || Integer.parseInt(date.substring(2, 4)) < 1) {
    		throw new MeetingException("Input date is invalid.");
    	}
    	
    	int dateToCompare = Integer.parseInt(date.substring(4, 8) + date.substring(0,2) + date.substring(2,4));
    	int nowToCompare = Integer.parseInt(strNow);
    	
    	if (dateToCompare < nowToCompare) {
    		throw new MeetingException("Input date is past.");
    	} else {
    		if (dateToCompare > nowToCompare) {
    			isFuture = true;
    		}
    		this.pDate = dateToCompare;
    	}
	}
    
    /**
	 * @return   Returns the meeting start time.
	 */
    public int getStartTime() {
    	
		return pStartTime;
	}
    
    /**
	 * @param startTime   Sets the pStartTime.
	 */
    public void setStartTime(String startTime) throws MeetingException {
    	//check the input starttime
    	if (startTime.length() > 5 || !startTime.contains(":")) {
    		throw new MeetingException("Invalid start time.");
    	}
    	
    	String[] timeList = startTime.split(":");
    	String temp = "";
    	for (String s : timeList) {
    		temp += s;
    	}
    	
    	int timeToCompare = Integer.parseInt(temp);    	
    	int nowToCompare = Integer.parseInt(now.getHour() + "" + now.getMinute() + "");
    	if (!isFuture) {
    		if (timeToCompare <  nowToCompare) {
    			throw new MeetingException("Start time is past.");
    		} else {
    			this.pStartTime = timeToCompare;
    		}
    	} else {
    		this.pStartTime = timeToCompare;
    	}
	}
    
    /**
	 * @return   Returns the meeting end time.
	 */
    public int getEndTime() {
		return pEndTime;
	}
    
    /**
	 * @param endTime   Sets the pEndTime.
	 */
    public void setEndTime(String endTime) throws MeetingException {
    	//check the input endtime
    	if (endTime.length() > 5 || !endTime.contains(":")) {
    		throw new MeetingException("Invalid end time.");
    	}
    	
    	String[] timeList = endTime.split(":");
    	String temp = "";
    	for (String s : timeList) {
    		temp += s;
    	}
    	
    	int timeToCompare = Integer.parseInt(temp);
    	
    	//if end time less than start time, throws error
    	if (pStartTime > timeToCompare) {
    		throw new MeetingException("End time is less than start time.");
    	} else {
    		this.pEndTime = timeToCompare;
    	}
	}
    
    /**
	 * @return   Returns the meeting description.
	 */
    public String getDescription() {
		return pDescription;
	}
    
    /**
	 * @param desctiption   Sets the description.
	 */
    public void setDescription(String description) throws MeetingException {
    	if (description == "") {
    		throw new MeetingException("Description should not be null or empty.");
    	} else {
    		this.pDescription = description;
    	}
	}
    
    /**
	 * @return   Returns the attendee.
	 */
    public String getAttendee() {
		return pAttendees;
	}
    
    /**
	 * @param attendee   Sets the attendee.
	 */
    public void setAttendee(String attendees) throws MeetingException {
    	//check if the attendee is available by checking the vacation table
    	String[] attendeeLiset = attendees.split(",");
    	String attendRes = "";
//    	boolean available = true;
    	
    	//open databse
		Connection c = null;
	    Statement stmt = null;
	    ResultSet rs  = null;
	    
	    try {
	    	for (String a : attendeeLiset) {
	    		//check vacation table
	    		String sql = "select startDate, endDate from vacation where employeeid = '" + a  + "' and startdate <= " 
	    					+  pDate + " and enddate >= " + pDate + ";";
	    		Class.forName("org.sqlite.JDBC");
	    		c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
	    		stmt = c.createStatement();
	    		rs = stmt.executeQuery(sql);
	    		if (!rs.next()) {
	    			attendRes += a + ",";
	    		}
	    	}      
    		rs.close();
    		c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//    	      System.exit(0);
	    }
    	this.pAttendees = attendRes;	
	}
    

    
	/**
	 * @return  Returns the room id.
	 */
    public String getRoom() {
   		return pRoomId;
   	}
       
     /**
   	 * @param room   Sets the room id.
   	 */
    public void setRoom(String room) throws MeetingException {
    	//checks if room is available
    	boolean available = true;
    	String sql = "select starttime, endtime from MEETING where roomid = '" + room + "' and meetingdate = " + pDate + ";";
    	//open databse
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      while (rs.next()) {
	    	  int sTime = rs.getInt("startTime");
	    	  int eTime = rs.getInt("endtime");
	    	  if ((pStartTime >= sTime || pStartTime <= eTime) || (pEndTime >= sTime && pEndTime <= eTime)) {
	    		  available = false;
	    		  break;
	    	  }
	      }
	      
	      rs.close();
	      c.close();
	      
	      if (available) {
	    	  this.pRoomId = room;
	      } else {
	    	  throw new MeetingException("Room not available during given start/end time.");
	      }
	      
	      
	     
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//	      System.exit(0);
	    }
	    
       	
   	}
    
}


