package cse.sc.edu.csce740.MEAT;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cse.sc.edu.csce740.MEAT.exception.MeetingException;

public class Events {
	
	/**
	 * Default constructor for an event.
	 */
	public Events() {
		
	}
	
	
	/**
	 * Returns the meeting list in database.
	 * @return List<List<String>> 
	 */
	public List<List<String>> getMeetings() {
		List<List<String>> meetings = new ArrayList<List<String>>();
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
	      stmt = c.createStatement();
	      String sql = "select * from meeting";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	    	  List<String> meeting = new ArrayList<String>();
	    	  meeting.add(String.valueOf(rs.getInt("meetingid")));
	    	  meeting.add(String.valueOf(rs.getInt("meetingdate")));
	    	  meeting.add(String.valueOf(rs.getInt("starttime")));
	    	  meeting.add(String.valueOf(rs.getInt("endtime")));
	    	  meeting.add(rs.getString("attendee"));
	    	  meeting.add(rs.getString("description"));
	    	  meeting.add(rs.getString("roomid"));
	    	  meetings.add(meeting);
	      }
	      
	      stmt.close();
	      rs.close();
	      c.close();
	      
	    } catch (Exception e) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    
	    return meetings;
	}
	
	/**
	 * Returns the vacartion list in database.
	 * @return List<List<String>> 
	 */
	public List<List<String>> getVacations() {
		List<List<String>> vacations = new ArrayList<List<String>>();
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
	      stmt = c.createStatement();
	      String sql = "select * from vacation";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	    	  List<String> vacation = new ArrayList<String>();
	    	  vacation.add(rs.getString("employeeid"));
	    	  vacation.add(String.valueOf(rs.getInt("startdate")));
	    	  vacation.add(String.valueOf(rs.getInt("enddate")));
	    	  vacation.add(rs.getString("description"));
	    	  vacations.add(vacation);
	      }
	      
	      stmt.close();
	      rs.close();
	      c.close();
	      
	    } catch (Exception e) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    
	    return vacations;
	}
	
	
	/**
	 * Returns the holiday list in database.
	 * @return List<List<String>> 
	 */
	public List<List<String>> getHolidays() {
		List<List<String>> holidays = new ArrayList<List<String>>();
		
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
	      stmt = c.createStatement();
	      String sql = "select * from holiday";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      while (rs.next()) {
	    	  List<String> holiday = new ArrayList<String>();
	    	  holiday.add(String.valueOf(rs.getInt("startdate")));
	    	  holiday.add(String.valueOf(rs.getInt("enddate")));
	    	  holiday.add(rs.getString("description"));
	    	  holidays.add(holiday);
	      }
	      
	      stmt.close();
	      rs.close();
	      c.close();
	      
	    } catch (Exception e) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
	    
	    return holidays;
	}
	
	/**
	 * Adds the meeting to Meeting table in database.
	 * @param aList
	 * @return boolean
	 */
	public synchronized boolean addMeeting(Meeting m) {
		//Adds meeting to database
		//open databse
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");

	      stmt = c.createStatement();
	      String sql = "insert into meeting (meetingdate, starttime, endtime, roomid, attendee, description) values (" + 
	    		  		m.getDate() + "," + 
	    		  		m.getStartTime() + "," +
	    		  		m.getEndTime() + "," + 
	    		  		"'" + m.getRoom() + "'," +
	    		  		"'" + m.getAttendee() + "'," +
	    		  		"'" + m.getDescription() + "')";
	      stmt.executeUpdate(sql);
	      stmt.close();
//	      c.commit();
	      c.close();
	      return true;
	     
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      
	      return false;
//	      System.exit(0);
	    }
	}
	
	/**
	 * Adds the holiday to the holiday database.
	 * @param Holiday
	 * @return void
	 */
	public synchronized boolean addHoliday(Holiday h) {
		//open databse
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");

	      stmt = c.createStatement();
	      String sql = "insert into holiday (startdate, enddate, description) values (" + 
	    		  		h.getStartDate() + "," + 
	    		  		h.getEndDate() + "," +
	    		  		"'" + h.getDescription() + "')";
	      stmt.executeUpdate(sql);
	      stmt.close();
//	      c.commit();
	      c.close();
	      return true;
	     
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
	
	/**
	 * Adds the vacation to the vacation database.
	 * @param Vacation
	 * @return void
	 */
	public synchronized boolean addVacation(Vacation v) {
		//open databse
		Connection c = null;
	    Statement stmt = null;
	    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
		
		      stmt = c.createStatement();
		      String sql = "insert into vacation (employeeid, startdate, enddate, description) values (" + 
		    		  		"'" + v.getEmployee() + "'," +
		    		  		v.getStartDate() + "," + 
		    		  		v.getEndDate() + "," +
		    		  		"'" + v.getDescription() + "')";
		      stmt.executeUpdate(sql);
		      stmt.close();
//		      c.commit();
		      c.close();
		      return true;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
	
	
	/**
	 * Returns the name of the meeting deleted given a meeting id
	 * @param meetingToDelete
	 * @return String
	 */
	public synchronized boolean deleteMeeting(String id) {
		//open database
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	
		    Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
		
	        stmt = c.createStatement();
	        String sql = "delete from meeting where meetingid = " + id +";";
	        stmt.executeUpdate(sql);
	        stmt.close();
//	        c.commit();
	        c.close();
	        return true;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
	
	/**
	 * Returns the name of the meeting edited at the position specified
	 * and null if the meeting does not exist.
	 * @param Meeting
	 * @return boolean
	 */
	public synchronized boolean editMeeting(Meeting m) throws MeetingException {
		//search for existing meeting given meeting id;
		int id = m.getId();
		//open database
		Connection c = null;
	    Statement stmt = null;
	    try {
		    Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
		
	        stmt = c.createStatement();
	        String sql = "select * from meeting where meetingid = " + id +";";
	        ResultSet rs = stmt.executeQuery(sql);
	        if (!rs.next()) {
	        	System.out.println("The meeting id is not in the database.");
	        	return false;
	        } else {
	        	sql = "UPDATE meeting set ";
	        	boolean isAdded = false;
	        	if (m.getDate() != 0) {
	        		sql += "meetingdate = " + m.getDate() + ",";
	        		isAdded = true;
	        	}
	        	if (m.getStartTime() != 0) {
	        		sql += "starttime = " + m.getStartTime() +",";
	        		isAdded = true;
	        	}
	        	if (m.getEndTime() != 0) {
	        		sql += "endtime = " + m.getEndTime() + ",";
	        		isAdded = true;
	        	}
	        	if (m.getRoom() != "") {
	        		sql += "room = '" + m.getRoom() + "',";
	        		isAdded = true;
	        	}
	        	if (m.getDescription() != "") {
	        		sql += "description = '" + m.getDescription() + "',";
	        		isAdded = true;
	        	}
	        	if (isAdded) {
	        		sql = sql.substring(0, sql.length() - 1);
	        		sql += " where meetingid = " + m.getId() + ";";
	        		
	        		//execute sql
	        		stmt.executeUpdate(sql);
//	        		c.commit();
	        	} else {
	        		throw new MeetingException("No meeting information provided.");
	        	}
	        }
	        
	        rs.close();
	        stmt.close();
	        c.close();
	        return true;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
	
	/**
	 * Returns the id of the meeting edited at the position specified
	 * and null if the attendees cannot be added.
	 * @param meetingToEdit
	 * @return boolean
	 */
	public synchronized boolean editMeetingAddAttendee(Meeting meetingToEdit) {
		int id = meetingToEdit.getId();
		String attendee = meetingToEdit.getAttendee();
		
		//open database
		Connection c = null;
	    Statement stmt = null;
	    try {
		    Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
		
	        stmt = c.createStatement();
	        //check meeting exist
	        String sql = "select attendee, meetingdate from meeting where meetingid = " + id + ";";
	        ResultSet rs = stmt.executeQuery(sql);
	        if (!rs.next()) {
	        	throw new MeetingException("Meeting is not in database.");
	        } else {
	        	String attendees = rs.getString("attendee");
	        	if (attendees.contains(attendee.substring(0, attendee.length() - 1))) {
	        		System.out.println("Attendee is already in the attendee list of the meeting.");
	        		rs.close();
	    	        stmt.close();
	    	        c.close();
	    	        return false;
	        	} else {
		        	int meetingDate = rs.getInt("meetingdate");
		        	//check attendee available
		        	sql = "select startdate, enddate from vacation where employeeid = '" + attendee.substring(0, attendee.length() - 1) + "'";
		        	rs = stmt.executeQuery(sql);
		        	boolean isAvailable = true;
		        	if (rs.next()) {
		        		while (rs.next()) {
		        			int startDate = rs.getInt("startdate");
		        			int endDate = rs.getInt("enddate");
		        			if (startDate <= meetingDate && meetingDate <= endDate) {
		        				isAvailable = false;
		        				break;
		        			}	
		        		}
		        	} 
		        	if (isAvailable) {
		        		attendees += attendee;
		        		sql = "update meeting set attendee = '" + attendees + "' where meetingid = " + id + ";";
		        		stmt.executeUpdate(sql);
		        	}	
	        	}
	        }
	        rs.close();
	        stmt.close();
	        c.close();
	        return true;
	    } catch (Exception e) {
	    	//System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    return false;
	    }   
	}
	
	/**
	 * Returns the id of the meeting edited at the position specified
	 * and null if the attendees cannot be removed.
	 * @param meetingToEdit
	 * @return boolean
	 */
	public synchronized boolean editMeetingRemoveAttendee(Meeting meetingToEdit) throws MeetingException{
		int id = meetingToEdit.getId();
		String attendee = meetingToEdit.getAttendee();
		
		//open database
		Connection c = null;
	    Statement stmt = null;
	    try {
		    Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
		
	        stmt = c.createStatement();
	        String sql = "select attendee from meeting where meetingid = " + id +";";
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        //if meeting does not exist, throw error msg
	        if (!rs.next()) {
	        	throw new MeetingException("Meeting is not in database.");
	        	//System.out.println("Meeting does not exist.");
//	        	rs.close();
//		        stmt.close();
//		        c.close();
//		        return false;
	        } else {
	        	String attendees = rs.getString("attendee");
	        	String[] attendeeArray = attendees.split(",");
	        	String attendeeToUpdate = "";
	        	for (String s : attendeeArray) {
	        		if (!s.equals(attendee.substring(0, attendee.length() - 1))) {
	        			attendeeToUpdate += s + ",";
	        		}
	        	}
	        	sql = "UPDATE meeting set attendee = '" + attendeeToUpdate + "' where meetingid = " + id + ";";
	        	stmt.executeUpdate(sql);
//	        	c.commit();
	        }
	        
	        rs.close();
	        stmt.close();
	        c.close();
	        return true;
	    } catch (Exception e) {
	    	//System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    return false;
	    }
	}
	
	
	/**
	 * Prints the universal schedule given start date and end date
	 * @param startdate, enddate, outputfile
	 * @return String
	 */
	public synchronized boolean printScheduleAll(int startDate, int endDate, String outputFile) {
//		int start = Integer.parseInt(startDate.substring(4, 8) + startDate.substring(0, 2) + startDate.substring(2, 4));
//		int end = Integer.parseInt(endDate.substring(4, 8) + endDate.substring(0, 2) + endDate.substring(2, 4));
//open database
		Connection c = null;
	    Statement stmt = null;
	    try {
//	    	
		    Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
//		
	        stmt = c.createStatement();
	        
	        JSONArray outerArray = new JSONArray();
	        JSONObject outerObject = new JSONObject();
	        JSONArray innerArray = new JSONArray();
	        
	        String sql = "select * from meeting where meetingdate >= " + startDate + " and  meetingdate <= " + endDate + ";";
	        
  	      	ResultSet rs = stmt.executeQuery(sql);
  	      	while (rs.next()) {
  	      		JSONObject obj = new JSONObject();
  	      		obj.put("meeting-id", Integer.toString(rs.getInt("meetingid")));
  	      		obj.put("date", Integer.toString(rs.getInt("meetingdate")));
  	      		obj.put("start-time", Integer.toString(rs.getInt("starttime")));
  	      		obj.put("end-time", Integer.toString(rs.getInt("endtime")));
  	      		obj.put("room-id", rs.getString("roomid"));
  	      		obj.put("description", rs.getString("description"));
  	      		JSONArray attendArray = new JSONArray();
  	      		String[] split = rs.getString("attendee").split(",");
  	      		for (String s : split) {
  	      			JSONObject attObj = new JSONObject();
  	      			attObj.put("name", s);
  	      			attendArray.add(attObj);
  	      		}
  	      		obj.put("attendees", attendArray);
  	      		innerArray.add(obj);
  	      	}
  	      	
	        sql = "select * from vacation where startdate >= " + startDate + " and enddate <= " + endDate + ";";
	        rs = stmt.executeQuery(sql);
  	      	while (rs.next()) {
  	      		JSONObject obj = new JSONObject();
	      		obj.put("employee-id", rs.getString("employeeid"));
	      		obj.put("start-date", Integer.toString(rs.getInt("startdate")));
	      		obj.put("end-date", Integer.toString(rs.getInt("enddate")));
	      		obj.put("description", rs.getString("description"));
	      		innerArray.add(obj);
  	      	}
  	      	
  	      	sql = "select * from holiday where startdate >= " + startDate + " and enddate <= " + endDate + ";";
	        rs = stmt.executeQuery(sql);
	      	while (rs.next()) {
	      		JSONObject obj = new JSONObject();
	      		obj.put("start-date", Integer.toString(rs.getInt("startdate")));
	      		obj.put("end-date", Integer.toString(rs.getInt("enddate")));
	      		obj.put("description", rs.getString("description"));
	      		innerArray.add(obj);
	      	}
	      	
	      	outerObject.put("events", innerArray);
	      	outerArray.add(outerObject);
	        
	      	Gson gSon = new GsonBuilder().setPrettyPrinting().create();
	      	String json = gSon.toJson(outerArray);
//	      	System.out.print(json);
	      	
	      	rs.close();
	        stmt.close();
	        c.close();
	        
	        try {
	        	String path = System.getProperty("user.dir") + "/" + outputFile;
	    		FileWriter file = new FileWriter(path);
	    		file.write(json);
	    		file.flush();
	    		file.close();
	    		System.out.println("Universal schedule successfully printed to file :'" + outputFile + "' under current project folder.");
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}

//	    	System.out.print(outerArray);

	        return true;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
	
	/**
	 * Prints the employee schedule given start date and end date and employeeid
	 * @param employeeid, startdate, enddate, outputfile
	 * @return boolean
	 */
	public synchronized boolean printScheduleEmployee(String employeeId, int startDate, int endDate, String outputFile) {
		//open database
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	
		    Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
		
	        stmt = c.createStatement();
	        
	        JSONArray outerArray = new JSONArray();
	        JSONObject outerObject = new JSONObject();
	        JSONArray innerArray = new JSONArray();
	        
	        
	        //extract data from meeting database
	        //date is between start and end date, attendee contains employeeid
	        String sql = "select * from meeting where meetingdate >= " + startDate + " and meetingdate <= " + endDate 
	        				+ " and attendee like '%" + employeeId + "%';";
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	        	//add to result list
	        	JSONObject obj = new JSONObject();
  	      		obj.put("meeting-id", Integer.toString(rs.getInt("meetingid")));
  	      		obj.put("date", Integer.toString(rs.getInt("meetingdate")));
  	      		obj.put("start-time", Integer.toString(rs.getInt("starttime")));
  	      		obj.put("end-time", Integer.toString(rs.getInt("endtime")));
  	      		obj.put("room-id", rs.getString("roomid"));
  	      		obj.put("description", rs.getString("description"));
  	      		JSONArray attendArray = new JSONArray();
  	      		String[] split = rs.getString("attendee").split(",");
  	      		for (String s : split) {
  	      			JSONObject attObj = new JSONObject();
  	      			attObj.put("name", s);
  	      			attendArray.add(attObj);
  	      		}
  	      		obj.put("attendees", attendArray);
  	      		innerArray.add(obj);
	        }
	        
	        //extract from vacation database
	        //data in between and attendee is employeeid
	        
	        sql = "select * from vacation where employeeid = '" + employeeId + "' and startdate >= " + startDate 
	        		+ " and enddate <= " + endDate + ";";
	        while (rs.next()) {
	        	//add to result list
	        	JSONObject obj = new JSONObject();
	      		obj.put("employee-id", rs.getString("employeeid"));
	      		obj.put("start-date", Integer.toString(rs.getInt("startdate")));
	      		obj.put("end-date", Integer.toString(rs.getInt("enddate")));
	      		obj.put("description", rs.getString("description"));
	      		innerArray.add(obj);
	        }
	        
	        rs.close();
	        stmt.close();
	        c.close();
	        
	        outerObject.put("events", innerArray);
	      	outerArray.add(outerObject);
	      	
	        Gson gSon = new GsonBuilder().setPrettyPrinting().create();
	      	String json = gSon.toJson(outerArray);
//	      	System.out.print(json);
	      	
	      	try {
	      		String path = System.getProperty("user.dir") + "/" + outputFile;
	    		FileWriter file = new FileWriter(path);
	    		file.write(json);
	    		file.flush();
	    		file.close();
	    		System.out.println("Employee schedule successfully printed to file :'" + outputFile + "' under current project folder.");
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	        return true;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
	
	/**
	 * Prints the room schedule given start date and end date and room id
	 * @param roomid, startdate, enddate, outputfile
	 * @return boolean
	 */
	public synchronized boolean printScheduleRoom(String roomId, int startDate, int endDate, String outputFile) {
		//open database
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	
		    Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite::resource:MEAT.db");
		
	        stmt = c.createStatement();
	        
	        JSONArray outerArray = new JSONArray();
	        JSONObject outerObject = new JSONObject();
	        JSONArray innerArray = new JSONArray();
	        
	        //only search the meeting database
	        String sql = "select * from meeting where roomid = '" + roomId + "' and meetingdate >= " + startDate + " and meetingdate <= " + endDate +";";
	        ResultSet rs = stmt.executeQuery(sql);
	        while (rs.next()) {
	        	//store data and make list here
	        	JSONObject obj = new JSONObject();
  	      		obj.put("meeting-id", Integer.toString(rs.getInt("meetingid")));
  	      		obj.put("date", Integer.toString(rs.getInt("meetingdate")));
  	      		obj.put("start-time", Integer.toString(rs.getInt("starttime")));
  	      		obj.put("end-time", Integer.toString(rs.getInt("endtime")));
  	      		obj.put("room-id", rs.getString("roomid"));
  	      		obj.put("description", rs.getString("description"));
  	      		JSONArray attendArray = new JSONArray();
  	      		String[] split = rs.getString("attendee").split(",");
  	      		for (String s : split) {
  	      			JSONObject attObj = new JSONObject();
  	      			attObj.put("name", s);
  	      			attendArray.add(attObj);
  	      		}
  	      		obj.put("attendees", attendArray);
  	      		innerArray.add(obj);
	        }
	        
	        rs.close();
	        stmt.close();
	        c.close();
	        
	        outerObject.put("events", innerArray);
	      	outerArray.add(outerObject);
	      	
	        Gson gSon = new GsonBuilder().setPrettyPrinting().create();
	      	String json = gSon.toJson(outerArray);
//	      	System.out.print(json);
	      	
	      	try {
	    		FileWriter file = new FileWriter(System.getProperty("user.dir") + "/" + outputFile);
	    		file.write(json);
	    		file.flush();
	    		file.close();
	    		System.out.println("Room schedule successfully printed to file :'" + outputFile + "' under current project folder.");

	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	        
	        return true;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
}
