package cse.sc.edu.csce740.MEAT;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 
 * @author Cizhen WU, Wenzhi Cai
 *
 */

public class RoomTest {
	Room room = new Room();
	
	/**
	 * Tests setRoomId() method
	 */
	@Test
	public void testSetRoomId(){
		try {
			room.setRoomId("3D15");
		} catch (Exception e) {
			fail();
		}
		assertEquals("3D15", room.getRoomId());
	}

}
