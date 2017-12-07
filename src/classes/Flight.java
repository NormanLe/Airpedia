package classes;

public class Flight {
	Airline airline;
	private int flightNo;
	private int noOfSeats;
	private String daysOperating; 
	private int minLengthOfStay;
	private int maxLengthOfStay;
	private double hiddenFare;
	
	public Flight(){
		
	}

	public Flight(Airline airline, int flightNo, int numSeats, String daysOperating, int minStay, int maxStay, double hiddenFare) {
		this.airline = airline;
		this.flightNo = flightNo;
		this.noOfSeats = numSeats;
		this.daysOperating = daysOperating;
		this.minLengthOfStay = minStay;
		this.maxLengthOfStay = maxStay;
		this.hiddenFare = hiddenFare;
	}

	public Airline getAirline(){
		return airline;
	}
	
	public void setAirline(Airline airline){
		this.airline = airline;
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

	public double getHiddenFare() {
		return hiddenFare;
	}

	public void setHiddenFare(double hiddenFare) {
		this.hiddenFare = hiddenFare;
	}

	
}
