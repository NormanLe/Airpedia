package classes;

import java.sql.Date;

public class Includes {
	Reservation reservation;
	Leg leg;
	private int legNo;
	private int fromStopNo;
	private Date date;
	private String seatNo;
	private String flightClass;
	private String meal;
	
	public Includes() {
		
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
	
	
}
