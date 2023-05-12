package assignment1;
//not
public abstract class Reservation {
	private String clientName;

	public Reservation(String clientName) {
		this.clientName = clientName;
	}
	public final String reservationName() {
		return this.clientName;
	}
	public abstract int getCost();

	public abstract boolean equals(Object obj);

}