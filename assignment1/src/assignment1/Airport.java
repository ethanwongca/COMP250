package assignment1;

public class Airport {
	private int xCoord;
	private int yCoord;
	private int fees;

	// constructor of each airport
	public Airport(int xCoord, int yCoord, int fees) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.fees = fees;
	}

	public int getFees() {
		return this.fees;
	}

	public static int getDistance(Airport a1, Airport a2) {
		double distance;
		double xCoordTotal = a1.xCoord - a2.xCoord;
		double yCoordTotal = a1.yCoord - a2.yCoord;
		distance = Math.sqrt(Math.pow(xCoordTotal, 2) + Math.pow(yCoordTotal, 2));
		//Type cast to return int, ceil also raises the values
		return (int) Math.ceil(distance);
	}

}

