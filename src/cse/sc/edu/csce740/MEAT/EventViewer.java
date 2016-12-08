package cse.sc.edu.csce740.MEAT;

import java.util.List;


public class EventViewer {
	private Events events;
	
	/**
	 * Constructs the event viewer
	 */
	public EventViewer() {
		events = new Events();
	}
	
	/**
	 * Prints the universal schedule
	 * @param List<Arguments>
	 * @return boolean
	 */
	public boolean printScheduleAll(List<Arguments> aList) {
		int startDate = 0;
		int endDate = 0;
		String outputFile = "";
		
		for (Arguments a : aList) {
			if (a.value.equals("")) {
				System.out.println("Invalid input occurred.");
				return false;
			}
			switch (a.name) {
				case "start-date":
					startDate = Integer.parseInt(a.value.substring(4, 8) + a.value.substring(0, 2) + a.value.substring(2, 4));
					break;
				case "end-date":
					endDate = Integer.parseInt(a.value.substring(4, 8) + a.value.substring(0, 2) + a.value.substring(2, 4));
					break;
				case "output-file":
					outputFile = a.value;
					break;
			}
		}
		return events.printScheduleAll(startDate, endDate, outputFile);
	}
	
	/**
	 * Prints the employee schedule
	 * @param List<Arguments>
	 * @return boolean
	 */
	public boolean printScheduleEmployee(List<Arguments> aList) {
		String employeeId = "";
		int startDate = 0;
		int endDate = 0;
		String outputFile = "";
		
		for (Arguments a : aList) {
			if (a.value.equals("")) {
				System.out.println("Invalid input occurred.");
				return false;
			}
			switch (a.name) {
				case "employee-id":
					employeeId = a.value;
					break;
				case "start-date":
					startDate = Integer.parseInt(a.value.substring(4, 8) + a.value.substring(0, 2) + a.value.substring(2, 4));
					break;
				case "end-date":
					endDate = Integer.parseInt(a.value.substring(4, 8) + a.value.substring(0, 2) + a.value.substring(2, 4));
					break;
				case "output-file":
					outputFile = a.value;
					break;
			}
		}
		
		return events.printScheduleEmployee(employeeId, startDate, endDate, outputFile);
	}
	
	
	/**
	 * Prints the room schedule
	 * @param List<Arguments>
	 * @return boolean
	 */
	public boolean printScheduleRoom(List<Arguments> aList) {
		String roomId = "";
		int startDate = 0;
		int endDate = 0;
		String outputFile = "";

		for (Arguments a : aList) {
			if (a.value.equals("")) {
				System.out.println("Invalid input occurred.");
				return false;
			}
			switch (a.name) {
				case "room-id":
					roomId = a.value;
					break;
				case "start-date":
					startDate = Integer.parseInt(a.value.substring(4, 8) + a.value.substring(0, 2) + a.value.substring(2, 4));	
					break;
				case "end-date":
					endDate = Integer.parseInt(a.value.substring(4, 8) + a.value.substring(0, 2) + a.value.substring(2, 4));	
					break;
				case "output-file":
					outputFile = a.value;
					break;
			}
		}
		return events.printScheduleRoom(roomId, startDate, endDate, outputFile);
	}
}
