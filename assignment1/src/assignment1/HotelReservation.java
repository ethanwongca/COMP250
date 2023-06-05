package assignment1;

public class HotelReservation extends Reservation{

	private Hotel hotel;
	private String roomType;
	private int numberNight;
	private int priceOfRoom;

	public HotelReservation(String clientName, Hotel hotel, String roomType, int numberNight) {
		super(clientName);
		this.hotel = hotel;
		this.roomType = roomType;
		this.numberNight = numberNight;
		this.priceOfRoom = hotel.reserveRoom(roomType);
	}
	public int getNumOfNights() {
		return this.numberNight;
	}
	public int getCost() {
		return this.priceOfRoom * this.numberNight;
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof HotelReservation)) {
			return false;
		}

		HotelReservation example = (HotelReservation) obj;
		if(this.reservationName().equalsIgnoreCase(example.reservationName()) && this.hotel.equals(example.hotel) && this.roomType.equalsIgnoreCase(example.roomType) && this.getNumOfNights() == example.getNumOfNights() && this.getCost() == example.getCost()) {
			return true;
		} else {
			return false;
		}
	}

}
