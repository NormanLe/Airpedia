package classes;

import java.sql.Timestamp;

public class FlightData {
	private String departAirport;
	private String arrivalAirport;
	private Timestamp departDate;
	private Timestamp arrivalDate;
	private int flightNo;
	private int legNo;
	private String classType;
	private String arrCity;
	private String arrAirportName;
	private String arrCountry;
	private String depCity;
	private String depAirportName;
	private String depCountry;
	private double fare;
	private String airlineId;

	public String getDepartAirport() {
		return departAirport;
	}
	public void setDepartAirport(String departAirport) {
		this.departAirport = departAirport;
	}
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public Timestamp getDepartDate() {
		return departDate;
	}
	public void setDepartDate(Timestamp departDate) {
		this.departDate = departDate;
	}
	public Timestamp getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Timestamp arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public int getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}
	public int getLegNo() {
		return legNo;
	}
	public void setLegNo(int legNo) {
		this.legNo = legNo;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getArrCity() {
		return arrCity;
	}
	public void setArrCity(String arrCity) {
		this.arrCity = arrCity;
	}
	public String getArrAirportName() {
		return arrAirportName;
	}
	public void setArrAirportName(String arrAirportName) {
		this.arrAirportName = arrAirportName;
	}
	public String getArrCountry() {
		return arrCountry;
	}
	public void setArrCountry(String arrCountry) {
		this.arrCountry = arrCountry;
	}
	public String getDepCity() {
		return depCity;
	}
	public void setDepCity(String depCity) {
		this.depCity = depCity;
	}
	public String getDepAirportName() {
		return depAirportName;
	}
	public void setDepAirportName(String depAirportName) {
		this.depAirportName = depAirportName;
	}
	public String getDepCountry() {
		return depCountry;
	}
	public void setDepCountry(String depCountry) {
		this.depCountry = depCountry;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}
}