package classes;

import java.sql.Date;

public class Reservation {
	private int resrNo;
	private Date resrDate;
	private double bookingFee;
	private double totalFare;
	Customer cusomer;
	Employee employee;
	
	public Reservation() {
		
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
}
