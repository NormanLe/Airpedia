package classes;

public class Flight {
	private int numSeats;
	private String daysOperating; 
	private int minLengthOfStay;
	private int maxLengthOfStay;
	private String airline;
	
	public String getDaysOperating() {
		return daysOperating;
	}

	public void setDaysOperating(String daysOperating) {
		this.daysOperating = daysOperating;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNoOfSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public int getMinLengthOfStay() {
		return minLengthOfStay;
	}

	public void setMinLengthOfStay(int minLengthOfStay) {
		this.minLengthOfStay = minLengthOfStay;
	}

	public int getMaxLengthOfStay() {
		return maxLengthOfStay;
	}

	public void setMaxLengthOfStay(int maxLengthOfStay) {
		this.maxLengthOfStay = maxLengthOfStay;
	}

	public Flight(){
		
	}
}
