package assignment1;

public class Room {
	private String roomType;
	private int price;
	private boolean roomAvailable;

	//Initialization Constructor
	public Room(String roomType) {

		if(!(roomType.equalsIgnoreCase("double")||roomType.equalsIgnoreCase("queen")||roomType.equalsIgnoreCase("king"))) {
			throw new IllegalArgumentException("No room of such a type can be created");
		}

		this.roomType = roomType;

		if(roomType.equalsIgnoreCase("double")) {
			this.price = 9000;
		} if(roomType.equalsIgnoreCase("queen")) {
			this.price = 11000;
		} if(roomType.equalsIgnoreCase("king")) {
			this.price = 15000;
		}
		this.roomAvailable = true;

	}

	// Copy Constructor
	public Room(Room room) {
		this.roomType = room.roomType;
		this.price = room.price;
		this.roomAvailable = room.roomAvailable;
	}
	// Getters
	public String getType() {
		return this.roomType;
	}
	public int getPrice() {
		return this.price;
	}
	// Setters
	public void changeAvailability() {
		if (roomAvailable){ //If TRUE
			this.roomAvailable = false;
		} else {
			this.roomAvailable = true;
		}
	}
	// Utility Methods
	public static Room findAvailableRoom(Room[] rooms, String type) {
		for(int i = 0; i < rooms.length; i++) {
			if(rooms[i] != null) {
				if (rooms[i].roomAvailable && rooms[i].roomType.equalsIgnoreCase(type)) {
					return rooms[i];
				}
			}
		}
		return null;
	}
	public static boolean makeRoomAvailable(Room[] rooms, String type) {
		for(int i = 0; i < rooms.length; i++) {
			if(rooms[i] != null) {
				if (!(rooms[i].roomAvailable) && rooms[i].roomType.equalsIgnoreCase(type)) {
					rooms[i].roomAvailable = true;
					return true;
				}
			}
		}
		return false;

	}

}
