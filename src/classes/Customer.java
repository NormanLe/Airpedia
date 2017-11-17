package classes;

import java.sql.Date;

public class Customer {
	Person person;
	int accountNo;
	String creditcardNo;
	String email;
	double phone;
	Date creationDate;
	int rating;
	
	public Customer() {
		
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getCreditcardNo() {
		return creditcardNo;
	}

	public void setCreditcardNo(String creditcardNo) {
		this.creditcardNo = creditcardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPhone() {
		return phone;
	}

	public void setPhone(double phone) {
		this.phone = phone;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
