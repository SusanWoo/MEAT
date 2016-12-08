package cse.sc.edu.csce740.MEAT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;


public class ParseJSON {
	
	/**
	 * Constructs with initializing nothign..
	 */
	public ParseJSON() {
		//System.out.println("hello");
	}
	
	/**
     * Reads a file by a given file name
     * @param path
     * @return void
     */
	public List<ParseObject> readFile(String path) {
		BufferedReader br = null;
		JSONParser parser = new JSONParser();
		List<ParseObject> resList=  new ArrayList<ParseObject>();
		try {
			br = new BufferedReader(new FileReader(path));
			
			Object obj;
			
			try {
				obj = parser.parse(br);
				JSONArray topArray = (JSONArray) obj;
				JSONObject jObject = (JSONObject) topArray.get(0);
				JSONArray commandsArray = (JSONArray) jObject.get("commands");
				
				for (Object command : commandsArray) {
					if (command != null) {
						JSONObject commandsObj = (JSONObject) command;
						String name = (String) commandsObj.get("name");
						JSONArray argumentsArray = (JSONArray) commandsObj.get("arguments");
						
						ParseObject pObj = new ParseObject();
						pObj.name = name;
						
						List<Arguments> aList = new ArrayList<>();
						for (Object o : argumentsArray) {
							if (o != null) {
								JSONObject jObj = (JSONObject) o;
								aList.add(new Arguments((String) jObj.get("name"), (String) jObj.get("value")));
							}
						}
						
						pObj.aList = aList;
						resList.add(pObj);
					}
				}
				return resList;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error happens when parsing the file");
				e.printStackTrace();
				return resList;
			}
		} catch (IOException e) {
//			e.printStackTrace();
			System.out.println("Please enter a valid file path");
			return resList;
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
//				ex.printStackTrace();
				System.out.println("Please enter a valid file path.\n");
			}
		}
	}
}
