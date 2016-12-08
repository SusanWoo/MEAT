package cse.sc.edu.csce740.MEAT;

import java.util.ArrayList;
import java.util.List;

public class ParseObject {
	
	public String name;
	List<Arguments> aList;
	
	/**
	 * Constructs the parseObject with no value
	 */
	public ParseObject() {
		this.name = "";
		this.aList= new ArrayList<Arguments>();
	}
}
