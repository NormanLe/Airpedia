package classes;

import java.sql.Date;

public class Includes {
	private Reservation reservation;
	private Leg leg;
	String airlineId;
	private int legNo;
	private int flightNo;
	private int fromStopNo;
	private String date;
	private String seatNo;
	private String flightClass;
	private String meal;
	
	public Includes() {
		
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Leg getLeg() {
		return leg;
	}

	public void setLeg(Leg leg) {
		this.leg = leg;
	}

	public int getLegNo() {
		return legNo;
	}

	public void setLegNo(int legNo) {
		this.legNo = legNo;
	}

	public int getFromStopNo() {
		return fromStopNo;
	}

	public void setFromStopNo(int fromStopNo) {
		this.fromStopNo = fromStopNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(String airline){
		this.airlineId = airline;
	}
	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	
}
