package cse.sc.edu.csce740.MEAT;



public class Room {
	private String pRoomId;
	
	/**
     * Creates a default room for MEAT.
     */
	public Room() {
		pRoomId = "";
	}
	
	
	/**
	 * @return   Returns the room id.
	 */
    public String getRoomId() {
		return pRoomId;
	}
    
    /**
	 * @param roomId   Sets the room pRoomId.
	 */
    public void setRoomId(String roomId)  {
    	this.pRoomId = roomId;
	}
	
}
