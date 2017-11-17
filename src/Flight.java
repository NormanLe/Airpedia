
public class Flight {
	private int NoOfSeats;
	public int getNoOfSeats() {
		return NoOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		NoOfSeats = noOfSeats;
	}

	public int getMinLengthOfStay() {
		return MinLengthOfStay;
	}

	public void setMinLengthOfStay(int minLengthOfStay) {
		MinLengthOfStay = minLengthOfStay;
	}

	public int getMaxLengthOfStay() {
		return MaxLengthOfStay;
	}

	public void setMaxLengthOfStay(int maxLengthOfStay) {
		MaxLengthOfStay = maxLengthOfStay;
	}

	private String DaysOperating; 
	private int MinLengthOfStay;
	private int MaxLengthOfStay;
	
	public Flight(){
		
	}
}
