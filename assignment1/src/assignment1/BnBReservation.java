package assignment1;

public class BnBReservation extends HotelReservation{

	public BnBReservation(String clientName, Hotel hotel, String roomType, int numberNight) {
		super(clientName, hotel, roomType, numberNight);
	}

	public int getCost() {
		return super.getCost() + 1000 * this.getNumOfNights();
	}
}
