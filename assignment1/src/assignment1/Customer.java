package assignment1;

public class Customer {
	private String name;
	private int balance;
	private Basket basket;

	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.basket = new Basket();

	}
	public String getName() {
		return this.name;
	}
	public int getBalance() {
		return this.balance;
	}
	public Basket getBasket() {return this.basket;}

	public int addFunds(int addedFunds) {
		if (addedFunds < 0) {
			throw new IllegalArgumentException("Input proper funds please");
		}
		this.balance += addedFunds;
		return this.balance;
	}

	public int addToBasket(Reservation reservation) {
		if(!(reservation.reservationName().equalsIgnoreCase(this.name))) {
			throw new IllegalArgumentException("Wrong customer name");
		}
		this.basket.add(reservation);
		return this.basket.getNumOfReservations();

	}

	public int addToBasket(Hotel hotel, String roomType, int numNights, boolean breakfast) {
		if(breakfast) {
			Reservation hotelRes = new BnBReservation(this.name, hotel, roomType, numNights);
			this.basket.add(hotelRes);
		} else {
			Reservation hotelRes = new HotelReservation(this.name, hotel, roomType, numNights);
			this.basket.add(hotelRes);
		}
		return this.basket.getNumOfReservations();
	}

	public int addToBasket(Airport arrival, Airport departure) {
		try {
			Reservation airportRes = new FlightReservation(this.name, arrival, departure);
			this.basket.add(airportRes);
		} catch (Exception e){
			return this.basket.getNumOfReservations();
		}
		return this.basket.getNumOfReservations();
	}

	public boolean removeFromBasket(Reservation reservation) {
		boolean possibleRemove = this.basket.remove(reservation);
		return possibleRemove;
	}

	public int checkOut() {
		if(balance - this.basket.getTotalCost() < 0) {
			throw new  IllegalStateException("Not enough balance");
		}
		balance = balance - this.basket.getTotalCost();
		this.basket.clear();
		return balance;
	}


}
