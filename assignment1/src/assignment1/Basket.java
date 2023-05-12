package assignment1;

public class Basket {
	//arrayList as we are adding or removing basket items
	//inspired by the arrayList seen in class
	private Reservation[] basket;
	private int place;

	public Basket() {
		this.basket = new Reservation[100];
		this.place = 0;
	}
	public Reservation[] getProducts() {
		return this.basket;
	}
	public int add(Reservation reservation) {
		this.basket[place] = reservation;
		place++;
		return place;
	}
	public boolean remove(Reservation reservation) {
		for(int i = 0; i < place; i++) {
			if(basket[i].equals(reservation)) {
				// could be wrong check the placement afterwards
				if(place == 1) {
					place = 0;
					this.basket = new Reservation[100];
					return true;
				}
				//Something is wrong will debug later, logic does not make sense
				while(i < place - 1) {
					basket[i] = basket[i+1];
					i++;
				}
				//error here it indicates -1 will be wrong
				place -= 1;
				return true;
			}
		}
		return false;

	}
	public void clear() {
		place = 0;
		this.basket = new Reservation[100];
	}
	public int getNumOfReservations() { //Check this math
		return place;
	}
	public int getTotalCost() {
		int totalCost = 0;
		for(int i = 0; i < place; i++) {
			totalCost += basket[i].getCost();
		}
		return totalCost;
	}


}
