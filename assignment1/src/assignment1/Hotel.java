package assignment1;
//not done
public class Hotel {
	private String hotelName;
	private Room[] rooms;

	public Hotel(String hotelName, Room[] rooms) {
		this.hotelName = hotelName;
		// deep copy
		this.rooms = new Room[rooms.length];
		for(int i = 0; i < rooms.length; i++) {
			this.rooms[i] = new Room(rooms[i]);
		}
	}
	public int reserveRoom(String inputString) {
		if(Room.findAvailableRoom(this.rooms, inputString) == null) {
			throw new IllegalArgumentException("Room is not available");
		} else {
			Room example = Room.findAvailableRoom(this.rooms, inputString);
			example.changeAvailability();
			return example.getPrice();
		}
	}
	public boolean cancelRoom(String inputString) {
		return Room.makeRoomAvailable(this.rooms, inputString);
	}

}
