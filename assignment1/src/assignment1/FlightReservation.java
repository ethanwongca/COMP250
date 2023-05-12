package assignment1;

public class FlightReservation extends Reservation {

	private Airport departure;
	private Airport arrival;

	public FlightReservation(String reservationName, Airport departure, Airport arrival) {
		super(reservationName);

		if(departure.equals(arrival)) {
			throw new IllegalArgumentException();
		}

		this.departure = departure;
		this.arrival = arrival;
	}
	public int getCost() {
		int taxes = 5375;
		int perGallonFuel = 124;
		double kilometersperGalon = 167.52;
		int fees = departure.getFees() + arrival.getFees();
		double fuelCost = taxes + fees + Airport.getDistance(this.arrival, this.departure) / kilometersperGalon * perGallonFuel;
		return (int) Math.ceil(fuelCost);
	}
	public boolean equals(Object obj) {
		if(!(obj instanceof FlightReservation)) {
			return false;
		}
		FlightReservation example = (FlightReservation) obj;
		if(this.reservationName().equalsIgnoreCase(example.reservationName()) && this.departure.equals(example.departure) && this.arrival.equals(example.arrival)) {
			return true;
		} else {
			return false;
		}
	}
}

