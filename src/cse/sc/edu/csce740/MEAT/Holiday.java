package cse.sc.edu.csce740.MEAT;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cse.sc.edu.csce740.MEAT.exception.HolidayException;

public class Holiday {
	private int pStartDate;
	private int pEndDate;
	private String pDescription;
	
	//today's date
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
	LocalDateTime now = LocalDateTime.now();
	String strNow = dtf.format(now);
	
	/**
     * Creates a default Holiday for MEAT.
     */
    public Holiday() {
    	this.pStartDate = 0;
    	this.pEndDate = 0;
    	this.pDescription = "";
    }
    
    /**
	 * @return   Returns the holiday start date.
	 */
    public int getStartDate() {
		return pStartDate;
	}
    
    /**
	 * @param startDate   Sets the holiday start date.
	 */
    public void setStartDate(String startDate) throws HolidayException {
    	//Checks date is valid
    	if (startDate.length() != 8) {
    		throw new HolidayException("Input date is invalid.");
    	}
    
    	//Checks month
    	if (Integer.parseInt(startDate.substring(0, 2)) > 12 || Integer.parseInt(startDate.substring(0, 2)) < 1) {
    		throw new HolidayException("Input date is invalid.");
    	}
    	
    	//Checks day
    	if (Integer.parseInt(startDate.substring(2, 4)) > 31 || Integer.parseInt(startDate.substring(2, 4)) < 1) {
    		throw new HolidayException("Input date is invalid.");
    	}
    	
    	int dateToCompare = Integer.parseInt(startDate.substring(4, 8) + startDate.substring(0,2) 
    							+ startDate.substring(2,4));
    	int nowToCompare = Integer.parseInt(strNow.substring(4, 8) + strNow.substring(0, 2) 
    							+ strNow.substring(2, 4));
    	
    	if (dateToCompare < nowToCompare) {
    		throw new HolidayException("Input date is past.");
    	} else {
    		this.pStartDate = dateToCompare;
    	}
	}
    
    /**
	 * @return   Returns the holiday end date.
	 */
    public int getEndDate() {
		return pEndDate;
	}
    
    /**
	 * @param endDate   Sets the holiday end date.
	 */
    public void setEndDate(String endDate) throws HolidayException {
    	//Checks date is valid
    	if (endDate.length() != 8) {
    		throw new HolidayException("Input date is invalid.");
    	}
    
    	//Checks month
    	if (Integer.parseInt(endDate.substring(0, 2)) > 12 || Integer.parseInt(endDate.substring(0, 2)) < 1) {
    		throw new HolidayException("Input date is invalid.");
    	}
    	
    	//Checks day
    	if (Integer.parseInt(endDate.substring(2, 4)) > 31 || Integer.parseInt(endDate.substring(2, 4)) < 1) {
    		throw new HolidayException("Input date is invalid.");
    	}
    	
    	int dateToCompare = Integer.parseInt(endDate.substring(4, 8) + endDate.substring(0,2) 
    							+ endDate.substring(2,4));
    	
    	if (dateToCompare < pStartDate) {
    		throw new HolidayException("Invalid end date.");
    	} else {
    		this.pEndDate = dateToCompare;
    	}
	}
    
    /**
	 * @return   Returns the holiday description.
	 */
    public String getDescription() {
		return pDescription;
	}
    
    /**
	 * @param description   Sets the holiday description.
	 */
    public void setDescription(String description) {
    	this.pDescription = description;
	}
}
