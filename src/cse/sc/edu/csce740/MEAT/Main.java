package cse.sc.edu.csce740.MEAT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cse.sc.edu.csce740.MEAT.exception.HolidayException;
import cse.sc.edu.csce740.MEAT.exception.MeetingException;
import cse.sc.edu.csce740.MEAT.exception.VacationException;

public class Main {
	private static EventScheduler eventScheduler;
	private static int meetingId;
	private static ParseJSON parseJSON;
	private static EventViewer eventViewer;
	
	/**
	 * 
	 * @author Cizhen Wu / Wenzhi Cai
	 *
	 * Starts the console UI for MEAT
	 */
	private static void mainMenu() {
		//System.out.println("1. Choose file input");
        System.out.println("1. Choose mannual input");
        System.out.println("0. Exit");
        
        //Get user input
        try {
        	int userInput = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like MEAT to do."));
        	
        	if (userInput >= 0 && userInput <= 1) {
		        //if (userInput == 1) processFile();
		        if (userInput == 1) showMenu();
		        if (userInput == 0) System.exit(0);
        	} else {
        		System.out.println("Please enter a number from 0 - 2");
            	mainMenu();
        	}
        } catch (NumberFormatException e) {
        	System.out.println("Please enter a number from 0 - 2");
        	mainMenu();
        }
	}
	
	/**
     * Asks users to input a file path
     * @return void
     */
	private static void processFile(String userInput) {
		//Get user input
        try {
//        	String userInput = inputOutput("Please enter the JSON file you would like to process or press 0 to go back to main menu.");
//        	String output = "";
        	boolean isSuccess = false;
        	
        	if (userInput != null && userInput != "") {
        		if (userInput.equals("0")) {
        			mainMenu();
        			return;
        		} 
        		List<ParseObject> list = parseJSON.readFile(userInput);
        		for (ParseObject pObj : list) {
        			switch (pObj.name) {
        				case "add-meeting":
        					isSuccess = eventScheduler.addMeeting(pObj.aList);
        					if (isSuccess) {
        						System.out.println("Successfully add meeting.");
        					} else {
        						System.out.println("Add meeting failed.");
        					}
        					break;
        				case "edit-meeting-details":
        					isSuccess = eventScheduler.editMeeting(pObj.aList);
        					if (isSuccess) {
        						System.out.println("Successfully edit meeting.");
        					} else {
        						System.out.println("Edit meeting failed.");
        					}
        					break;
        				case "edit-meeting-add-attendees": 
        					isSuccess = eventScheduler.editMeetingAddAttendee(pObj.aList);
        					if (isSuccess) {
        						System.out.println("Successfully edit meeting add attendee.");
        					} else {
        						System.out.println("Edit meeting add attendee failed.");
        					}
        					break;
        				case "edit-meeting-remove-attendees ":
        					isSuccess = eventScheduler.editMeetingRemoveAttendee(pObj.aList);
        					if (isSuccess) {
        						System.out.println("Successfully edit meeting remove attendee.");
        					} else {
        						System.out.println("Remove attendee failed.");
        					}
        					break;
        				case "delete-meeting":
        					isSuccess = eventScheduler.deleteMeeting(pObj.aList);
        					if (isSuccess) {
        						System.out.println("Successfully delete meeting.");
        					} else {
        						System.out.println("Delete meeting failed.");
        					}
        					break;
        				case "add-vacation":
        					isSuccess = eventScheduler.addVacation(pObj.aList);
        					if (isSuccess) {
        						System.out.println("Successfully add vacation.");
        					} else {
        						System.out.println("Add vacation failed.");
        					}
        					break;
        				case "add-holiday":
        					isSuccess = eventScheduler.addHoliday(pObj.aList);
        					if (isSuccess) {
        						System.out.println("Successfully add holiday.");
        					} else {
        						System.out.println("Add holiday failed.");
        					}
        					break;
        				case "print-schedule-all":
        					isSuccess = eventViewer.printScheduleAll(pObj.aList);
        					break;
        				case "print-schedule-employee":
        					isSuccess = eventViewer.printScheduleEmployee(pObj.aList);
        					break;
        				case "print-schedule-room":
        					isSuccess = eventViewer.printScheduleRoom(pObj.aList);
        					break;	
        			}
        		}
        		mainMenu();
        	} else {
        		System.out.println("Please enter a valid file path.");
            	mainMenu();
        	}
        } catch (NumberFormatException e) {
        	System.out.println("Please enter a valid file path.");
//        	processFile();
        }
	}
	
	/**
     * Shows the main menu for mannual input
     * @return void
     */
	private static void showMenu() {
		System.out.println("1. Add a meeting");
        System.out.println("2. Edit an existing meeting");
        System.out.println("3. Add attendees to an existing meeting");
        System.out.println("4. Remove attendees to an existing meeting");
        System.out.println("5. Delete a meeting");
        System.out.println("6. Add a vacation");
        System.out.println("7. Add a holiday");
        System.out.println("8. Print all schedule");
        System.out.println("9. Print schedule of an employee");
        System.out.println("10. Print schedule of a room");
        System.out.println("0. Back to previous menu");
        
        //Get user input
        try {
        	int userInput = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like MEAT to do."));
        	
        	if (userInput >= 0 && userInput <= 10) {
		        if (userInput == 1) addMeeting();
		        if (userInput == 2) editMeeting();
		        if (userInput == 3) editMeetingAddAttendee();
		        if (userInput == 4) editMeetingRemoveAttendee();
		        if (userInput == 5) deleteMeeting();
		        if (userInput == 6) addVacation();
		        if (userInput == 7) addHoliday();
		        if (userInput == 8) printScheduleAll();
		        if (userInput == 9) printScheduleEmployee();
		        if (userInput == 10) printScheduleRoom();
		        if (userInput == 0) mainMenu();
        	} else {
        		System.out.println("Please enter a number from 0 - 10");
        		showMenu();
        	}
        } catch (NumberFormatException e) {
        	System.out.println("Please enter a number from 0 - 10");
        	showMenu();
        }
	}
	
	/**
     * Performs add meetings to the system
     * @return void
     */
	private static void addMeeting() {
		//Read in meeting date
		String pDate = inputOutput("\nPlease enter the date of meeting to add, formatted as MMDDYYYY: ");
		//Read in meeting start time
		String pStartTime = inputOutput("\nPlease enter the start time of meeting to add, formatted as HH:MM: ");
		//Read in meeting end time
		String pEndTime = inputOutput("\nPlease enter the end time of meeting to add, formatted as HH:MM: ");
		//Read in meeting room id
		String pRoomId = inputOutput("\nPlease enter the room id of meeting to add: ");
		//Read in meeting attendee
		String pAttendee = inputOutput("\nPlease enter the attendee of meeting to add, if there are more than one attendee, use comma for seperation: ");
		//Read in meeting description
		String pDescription = inputOutput("\nPlease enter the description of meeting to add: ");
//		
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("date", pDate);
		aList.add(a);
		a = new Arguments("start-time", pStartTime);
		aList.add(a);
		a = new Arguments("end-time", pEndTime);
		aList.add(a);
		a = new Arguments("room-id", pRoomId);
		aList.add(a);
		a = new Arguments("attendee", pAttendee);
		aList.add(a);
		a = new Arguments("description", pDescription);
		aList.add(a);
		
		boolean isAdded = eventScheduler.addMeeting(aList);
		if (isAdded) {
			System.out.print("Meeting successsfully added!\n");	
		} else {
			System.out.print("Meeting not added!\n");
		}
		mainMenu();
	}
	
	/**
     * Performs edit meeting details to the system
     * @return void
     */
	private static void editMeeting() {
		List<List<String>> meetings = eventScheduler.getMeetings();
		System.out.println("Existing meetings in meeting databse:");
		for (List<String> meeting : meetings) {
			System.out.println("Meeting ID: " + meeting.get(0));
			System.out.println("Meeting date: " + meeting.get(1));
			System.out.println("Start time: " + meeting.get(2));
			System.out.println("End time: " + meeting.get(3));
			System.out.println("Attendee(s): " + meeting.get(4));
			System.out.println("Description: " + meeting.get(5));
			System.out.println("Room Id: " + meeting.get(6));
			System.out.println("\n");
		}
		
		//Read in meeting id
		String pMeetingId = inputOutput("\nPlease enter the id of meeting to edit: ");
		//Read in meeting date
		String pDate = inputOutput("\nPlease enter the date of meeting to edit, formatted as MMDDYYYY: ");
		//Read in meeting start time
		String pStartTime = inputOutput("\nPlease enter the start time of meeting to add, formatted as HH:MM: ");
		//Read in meeting end time
		String pEndTime = inputOutput("\nPlease enter the end time of meeting to add, formatted as HH:MM: ");
		//Read in meeting room id
		String pRoomId = inputOutput("\nPlease enter the room id of meeting to add: ");
		//Read in meeting description
		String pDescription = inputOutput("\nPlease enter the description of meeting to add: ");
		
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("date", pDate);
		aList.add(a);
		a = new Arguments("meeting-id", pMeetingId);
		aList.add(a);
		a = new Arguments("start-time", pStartTime);
		aList.add(a);
		a = new Arguments("end-time", pEndTime);
		aList.add(a);
		a = new Arguments("room-id", pRoomId);
		aList.add(a);
		a = new Arguments("description", pDescription);
		aList.add(a);
		
		boolean isAdded = eventScheduler.editMeeting(aList);
		if (isAdded) {
			System.out.print("Meeting successsfully edited!\n");	
		} else {
			System.out.print("Meeting not edited!\n");
		}
		mainMenu();
	}
	
	
	/**
     * Performs edit meeting - add attendee to the system
     * @return void
     */
	private static void editMeetingAddAttendee() {
		List<List<String>> meetings = eventScheduler.getMeetings();
		System.out.println("Existing meetings in meeting databse:");
		for (List<String> meeting : meetings) {
			System.out.println("Meeting ID: " + meeting.get(0));
			System.out.println("Meeting date: " + meeting.get(1));
			System.out.println("Start time: " + meeting.get(2));
			System.out.println("End time: " + meeting.get(3));
			System.out.println("Attendee(s): " + meeting.get(4));
			System.out.println("Description: " + meeting.get(5));
			System.out.println("\n");
		}
		
		//Read in meeting id
		String pMeetingId = inputOutput("\nPlease enter the id of meeting to edit: ");
		//Read in attendee
		String pAttendee = inputOutput("\nPlease enter attendee edit: ");
		
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("meeting-id", pMeetingId);
		aList.add(a);
		a = new Arguments("attendee", pAttendee);
		aList.add(a);
		
		boolean isAdded = eventScheduler.editMeetingAddAttendee(aList);
		if (isAdded) {
			System.out.print("Attendee successsfully added!\n");	
		} else {
			System.out.print("Attendee not added!\n");
		}
		mainMenu();
	}
	
	/**
     * Performs edit meeting - remove attendee to the system
     * @return void
     */
	private static void editMeetingRemoveAttendee() {
		//show existing meetings
		List<List<String>> meetings = eventScheduler.getMeetings();
		System.out.println("Existing meetings in meeting databse:");
		for (List<String> meeting : meetings) {
			System.out.println("Meeting ID: " + meeting.get(0));
			System.out.println("Meeting date: " + meeting.get(1));
			System.out.println("Start time: " + meeting.get(2));
			System.out.println("End time: " + meeting.get(3));
			System.out.println("Attendee(s): " + meeting.get(4));
			System.out.println("Description: " + meeting.get(5));
			System.out.println("\n");
		}
		//Read in meeting id
		String pMeetingId = inputOutput("\nPlease enter the id of meeting to edit: ");
		//Read in attendee
		String pAttendee = inputOutput("\nPlease enter attendee remove: ");
		
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("meeting-id", pMeetingId);
		aList.add(a);
		a = new Arguments("attendee", pAttendee);
		aList.add(a);
		
		boolean isAdded = eventScheduler.editMeetingRemoveAttendee(aList);
		if (isAdded) {
			System.out.print("Attendee successsfully removed!\n");	
		} else {
			System.out.print("Attendee not removed!\n");
		}
		mainMenu();
	}
	
	/**
     * Deletes a meeting from the system
     * @return void
     */
	private static void deleteMeeting() {
		//show existing meetings
		List<List<String>> meetings = eventScheduler.getMeetings();
		if (meetings.size() != 0) {
			System.out.println("Existing meetings in meeting databse:");
			for (List<String> meeting : meetings) {
				System.out.println("Meeting ID: " + meeting.get(0));
				System.out.println("Meeting date: " + meeting.get(1));
				System.out.println("Start time: " + meeting.get(2));
				System.out.println("End time: " + meeting.get(3));
				System.out.println("Attendee(s): " + meeting.get(4));
				System.out.println("Description: " + meeting.get(5));
				System.out.println("\n");
			}
		}
		
		//Read in meeting id
		String pMeetingId = inputOutput("\nPlease enter the id of meeting to delete: ");
		
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("meeting-id", pMeetingId);
		aList.add(a);
	
		boolean isAdded = eventScheduler.deleteMeeting(aList);
		if (isAdded) {
			System.out.print("Meeting successsfully deleted!\n");	
		} else {
			System.out.print("Meeting not deleted!\n");
		}
		mainMenu();
	}
	
	/**
     * Adds a vacation to the system
     * @return void
     */
	private static void addVacation() {
		//display vacations in the vacation database
		List<List<String>> vacations = eventScheduler.getVacations();
		if (vacations.size() != 0) {
			System.out.println("Existing vacations in vacation databse:");
			for (List<String> vacation : vacations) {
				System.out.println("Employee id: " + vacation.get(0));
				System.out.println("Start time: " + vacation.get(1));
				System.out.println("End time: " + vacation.get(2));
				System.out.println("Description: " + vacation.get(3));
				System.out.println("\n");
			}
		}
	
		//Read in the employee id
		String pEmployeeId = inputOutput("\nPlease enter the employee id: ");
		//Read in vacation start date
		String pStartDate = inputOutput("\nPlease enter the start date of vacation to add, formatted as MMDDYYYY: ");
		//Read in vacation start date
		String pEndDate = inputOutput("\nPlease enter the end date of vacation to add, formatted as MMDDYYYY: ");
		//Read in vacation description
		String pDescription = inputOutput("\nPlease enter the description of vacation to add: ");
		
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("employee-id", pEmployeeId);
		aList.add(a);
		a = new Arguments("start-date", pStartDate);
		aList.add(a);
		a = new Arguments("end-date", pEndDate);
		aList.add(a);
		a = new Arguments("description", pDescription);
		aList.add(a);
		
		boolean isAdded = eventScheduler.addVacation(aList);
		if (isAdded) {
			System.out.print("Vacation successsfully added!\n");	
		} else {
			System.out.print("Vacation not added!\n");
		}
		mainMenu();
	}
	
	/**
     * Adds a holiday to the system
     * @return void
     */
	private static void addHoliday() {
		//display vacations in the vacation database
		List<List<String>> holidays = eventScheduler.getHolidays();
		if (holidays.size() != 0) {
			System.out.println("Existing holidays in holiday databse:");
			for (List<String> holiday : holidays) {
				System.out.println("Start date: " + holiday.get(0));
				System.out.println("End date: " + holiday.get(1));
				System.out.println("Description: " + holiday.get(2));
				System.out.println("\n");
			}
		}

		//Read in holiday start date
		String pStartDate = inputOutput("\nPlease enter the start date of holiday to add, formatted as MMDDYYYY: ");
		//Read in holiday start date
		String pEndDate = inputOutput("\nPlease enter the end date of holiday to add, formatted as MMDDYYYY: ");
		//Read in holiday description
		String pDescription = inputOutput("\nPlease enter the description of holiday to add: ");
		
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("start-date", pStartDate);
		aList.add(a);
		a = new Arguments("end-date", pEndDate);
		aList.add(a);
		a = new Arguments("description", pDescription);
		aList.add(a);
		
		boolean isAdded = eventScheduler.addHoliday(aList);
		if (isAdded) {
			System.out.print("Holiday successsfully added!\n");	
		} else {
			System.out.print("Holiday not added!\n");
		}
		mainMenu();
	}
	
	/**
     * Prints the universal schedule
     * @return void
     */
	private static void printScheduleAll() {
		//Read in schecdule start date
		String pStartDate = inputOutput("\nPlease enter the start date of schedule to print, formatted as MMDDYYYY: ");
		//Read in schedule start date
		String pEndDate = inputOutput("\nPlease enter the end date of schedule to print, formatted as MMDDYYYY: ");
		//Read in output file name
		String pOutputFile = inputOutput("\nPlease enter the output file name, formatted as filename.json: ");

		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("start-date", pStartDate);
		aList.add(a);
		a = new Arguments("end-date", pEndDate);
		aList.add(a);
		a = new Arguments("output-file", pOutputFile);
		aList.add(a);
		
		boolean isAdded = eventViewer.printScheduleAll(aList);
	
		mainMenu();
		
	}
	
	/**
     * Prints the schedule of a room
     * @return void
     */
	private static void printScheduleRoom() {
		//Read in schecdule start date
		String pStartDate = inputOutput("\nPlease enter the start date of room schedule to print, formatted as MMDDYYYY: ");
		//Read in schedule start date
		String pEndDate = inputOutput("\nPlease enter the end date of room schedule to print, formatted as MMDDYYYY: ");
		//Read in room id
		String pRoom = inputOutput("\nPlease enter the room id to print: ");
	
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("start-date", pStartDate);
		aList.add(a);
		a = new Arguments("end-date", pEndDate);
		aList.add(a);
		a = new Arguments("room-id", pRoom);
		aList.add(a);
		
		boolean isAdded = eventViewer.printScheduleRoom(aList);
	
		mainMenu();
	}
	
	/**
     * Prints the schedule of an employee
     * @return void
     */
	private static void printScheduleEmployee() {
		//Read in schecdule start date
		String pStartDate = inputOutput("\nPlease enter the start date of employee schedule to print, formatted as MMDDYYYY: ");
		//Read in schedule start date
		String pEndDate = inputOutput("\nPlease enter the end date of employee schedule to print, formatted as MMDDYYYY: ");
		//Read in room id
		String pEmployeeId = inputOutput("\nPlease enter the employee id to print: ");
	
		List<Arguments> aList = new ArrayList<Arguments>();
		Arguments a = new Arguments("start-date", pStartDate);
		aList.add(a);
		a = new Arguments("end-date", pEndDate);
		aList.add(a);
		a = new Arguments("employee-id", pEmployeeId);
		aList.add(a);
		
		boolean isAdded = eventViewer.printScheduleEmployee(aList);
	
		mainMenu();
	}
	
	/**
     * Passes a prompt to the user and returns the user specified 
     * string.
     * @param message
     * @return String
     */
    private static String inputOutput(String message) {
        System.out.println(message);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String returnString = "";
	    try {
	        returnString = br.readLine();
	    }
	    catch (IOException e){
	        System.out.println("Error reading in value");
	        mainMenu();
	    }
	    return returnString;
    }
	
    public static void main(String[] args) {
    	eventScheduler = new EventScheduler();
    	meetingId = 0;
    	parseJSON = new ParseJSON();
    	eventViewer = new EventViewer();
    	
    	if (args.length == 0) {
    		mainMenu();
    	} else {
    		processFile(args[0]);
    	}
    	//mainMenu();
    	
    }
}