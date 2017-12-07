package classes;
public class Auction {
	Flight flight;
	Customer customer;
	private String flightClass;
	private int accountNo;
	private String airlineId;
	private int flightNo;
	private String date;
	private double nyop;
	
	public Auction () {
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getNyop() {
		return nyop;
	}

	public void setNyop(double nyop) {
		this.nyop = nyop;
	}
}
