package classes;

public class Flight {
	Airline airlineID;
	private int flightNo;
	private int noOfSeats;
	private String daysOperating; 
	private int minLengthOfStay;
	private int maxLengthOfStay;
	
	public Flight(){
		
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	public String getDaysOperating() {
		return daysOperating;
	}

	public void setDaysOperating(String daysOperating) {
		this.daysOperating = daysOperating;
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

	
}
