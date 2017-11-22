package classes;

import java.sql.Date;

public class Reservation {
	private int resrNo;
	private Date resrDate;
	private double bookingFee;
	private double totalFare;
	Customer customer;
	Employee employee;
	
	public Reservation() {
		
	}

	public Reservation(int resrNo, Date resrDate, double bookingFee, double totalFare,
			Customer customer, Employee employee){
		this.resrNo = resrNo;
		this.resrDate = resrDate;
		this.bookingFee = bookingFee;
		this.totalFare = totalFare;
		this.customer = customer;
		this.employee = employee;
	}
	
	public int getResrNo() {
		return resrNo;
	}

	public void setResrNo(int resrNo) {
		this.resrNo = resrNo;
	}

	public Date getResrDate() {
		return resrDate;
	}

	public void setResrDate(Date resrDate) {
		this.resrDate = resrDate;
	}

	public double getBookingFee() {
		return bookingFee;
	}

	public void setBookingFee(double bookingFee) {
		this.bookingFee = bookingFee;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
