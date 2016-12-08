package cse.sc.edu.csce740.MEAT;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Cizhen Wu, Wenzhi Cai
 *
 */
public class ParseJSONTest {
	ParseJSON pj;
	String path1 = "";
	String path2 = "";
	String path3 = "";

	public ParseJSONTest() {
		pj = new ParseJSON();

		// generate the legal path
		path1 = System.getProperty("user.dir") + "/resource/test.json";
		// generate the illegal path
		path2 = System.getProperty("user.dir") + "/test.json";
		// generate the mal-formed file
		path3 = System.getProperty("user.dir") + "/resource/wrong.json";
	}

	/*
	 * Tests readFile() method
	 */
	@Test
	public void testReadFile() {
		// test the legal path
		try {
			pj.readFile(path1);
		} catch (Exception e) {
			fail();
		}

		// test the illegal path
		try {
			pj.readFile(path2);
		} catch (Exception e) {
			fail();
		}
		assertFalse(false);
		
		// test the illegal path
		try {
			pj.readFile(path3);
		} catch (Exception e) {
			fail();
		}
		assertFalse(false);
	}
}
