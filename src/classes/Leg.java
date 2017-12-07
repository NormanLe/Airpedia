package classes;

import java.sql.Date;

public class Leg {
	Flight flight;
	Airline airline;
	private int legNo;
	private Date arrTime;
	private Date depTime;
	private boolean onTime; 
	private String depAirportId;
	private String arrAirportId;
	
	public Leg() {
		
	}

	public int getLegNo() {
		return legNo;
	}

	public void setLegNo(int legNo) {
		this.legNo = legNo;
	}

	public Date getArrTime() {
		return arrTime;
	}

	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}

	public Date getDepTime() {
		return depTime;
	}

	public void setDepTime(Date depTime) {
		this.depTime = depTime;
	}

	public boolean isOnTime() {
		return onTime;
	}

	public void setOnTime(boolean onTime) {
		this.onTime = onTime;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getDepAirportId() {
		return depAirportId;
	}

	public void setDepAirportId(String depAirportId) {
		this.depAirportId = depAirportId;
	}

	public String getArrAirportId() {
		return arrAirportId;
	}

	public void setArrAirportId(String arrAirportId) {
		this.arrAirportId = arrAirportId;
	}
}
