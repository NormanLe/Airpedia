package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import classes.Customer;
import classes.Employee;
import classes.Reservation;

public class DBUtils {
	private Connection con;

	public static Customer findCustomer(Connection conn, 
			String email, String password) throws SQLException {

		String sql = "Select * from Customer c" 
				+ " where c.email = ? and c.password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, email);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			int number = rs.getInt("AccountNo");
			String creditcardNo = rs.getString("CreditCardNo");
			double phone = rs.getDouble("Phone");
			Date creationDate = rs.getDate("CreationDate");
			int rating = rs.getInt("Rating");
			
			Customer customer = new Customer();
			customer.setAccountNo(number);
			customer.setEmail(email);
			customer.setPassword(password);
			customer.setCreditcardNo(creditcardNo);
			customer.setPhone(phone);
			customer.setCreationDate(creationDate);
			customer.setRating(rating);
			return customer;
		}
		return null;
	}

	public static Customer findCustomer(Connection conn, String email) throws SQLException {

		String sql = "Select * from Customer c"
				+ " where c.email = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, email);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String password = rs.getString("Password");
			int number = rs.getInt("AccountNo");
			String creditcardNo = rs.getString("CreditCardNo");
			double phone = rs.getDouble("Phone");
			Date creationDate = rs.getDate("CreationDate");
			int rating = rs.getInt("Rating");
			
			Customer customer = new Customer();
			customer.setAccountNo(number);
			customer.setEmail(email);
			customer.setPassword(password);
			customer.setCreditcardNo(creditcardNo);
			customer.setPhone(phone);
			customer.setCreationDate(creationDate);
			customer.setRating(rating);
			return customer;
		}
		return null;
	}
	
	public static Customer findCustomer(Connection conn, int accountNo) throws SQLException {

		String sql = "Select * from Customer"
				+ " where AccountNo = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, accountNo);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			String creditcardNo = rs.getString("CreditCardNo");
			double phone = rs.getDouble("Phone");
			Date creationDate = rs.getDate("CreationDate");
			int rating = rs.getInt("Rating");
			
			Customer customer = new Customer();
			customer.setAccountNo(accountNo);
			customer.setEmail(email);
			customer.setPassword(password);
			customer.setCreditcardNo(creditcardNo);
			customer.setPhone(phone);
			customer.setCreationDate(creationDate);
			customer.setRating(rating);
			return customer;
		}
		return null;
	}
	
	public static Employee findEmployee(Connection conn, int repSSN) throws SQLException {

		String sql = "Select * from Employee"
				+ " where SSN = ? ";
		
		// Do I need to query for the person entity related to employee a.k.a the parent class
		// If so, do we assign to the employee we create?
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, repSSN);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			int number = rs.getInt("SSN");
			Employee employee = new Employee();
			employee.setSsn(number);
			
			return employee;
		}
		return null;
	}

	public static List<Reservation> queryReservation(Connection conn) {
		String sql = "Select * from Reservation ";

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				int resrNo = rs.getInt("ResrNo");
				java.sql.Date sqlDate = rs.getDate("ResrDate");
				Date date = new Date(sqlDate.getTime());
				
				double fee = rs.getDouble("BookingFee");
				double fare = rs.getDouble("TotalFare");
				int accountNo = rs.getInt("AccountNo");
				int repSSN = rs.getInt("RepSSN");
				
				// Won't create the person references yet - not sure if necessary yet
				Customer customer = findCustomer(conn, accountNo);
				Employee employee = findEmployee(conn, repSSN);
				Reservation reservation = new Reservation(resrNo, date, fee, fare, customer, employee);
				list.add(reservation);
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in querying resrvations");
		}
		return list;
	}

	// TODO: Change these in future

	private static void addEmployee(Connection conn, Employee employee) {
		// try {
		// Statement stmt = conn.createStatement();
		// stmt.executeUpdate(String.format(
		// "UPDATE Employee(Id, SSN, IsManager, StartDate, HourlyRate) VALUES
		// (%d, %d, %b, %t, %f",
		// employee.getSsn(), employee.getemployee.getStartDate(),
		// isManager, startDate, hourlyRate, newRate));
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
	}

	// fix
	private void updateEmployee(int id, int ssn, boolean isManager, Date startDate, double hourlyRate, double newRate) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format(
					"INSERT INTO Employee(Id, SSN, IsManager, StartDate, HourlyRate) VALUES (%d, %d, %b, %t, %f", id,
					ssn, isManager, startDate, hourlyRate, newRate));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// fix
	private void deleteEmployee(int id, int ssn, boolean isManager, Date startDate, double hourlyRate, double newRate) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format(
					"DELETE Employee(Id, SSN, IsManager, StartDate, HourlyRate) VALUES (%d, %d, %b, %t, %f", id, ssn,
					isManager, startDate, hourlyRate, newRate));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ResultSet getSalesReportForMonth(int month) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery(String.format(
					"SELECT C.AccountNo, R.TotalFare, R.ResrDate FROM Customer C, Reservation R, Makes M WHERE MONTH(R.ResrDate) = %d AND M.ResrNo = R.ResrNo AND M.AccountNo = C.AccountNo;",
					month));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet listAllFlights() {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery("SELECT * FROM Flight");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet listReservationsByFlightNo(String s) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery(
					"SELECT * FROM Reservation R WHERE R.ResrNo IN (SELECT I.ResrNo FROM Includes I WHERE I.FlightNo = "
							+ s);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet listReservationsById(String s) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery(
					"SELECT * FROM Reservation R WHERE R.ResrNo IN (SELECT M.ResrNo FROM Makes M WHERE M.Id = " + s);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet getRepMostRevenue() {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery(
					"SELECT summed.RepSSN, MAX(SumRevenue) FROM (SELECT RepSSN, SUM(BookingFee) AS SumRevenue FROM Reservation GROUP BY RepSSN) summed GROUP BY summed.RepSSN ORDER BY MAX(SumRevenue) DESC LIMIT 1;");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ResultSet getCustomerMostRevenue() {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery(
					"SELECT summed.AccountNo, MAX(SumRevenue) FROM (SELECT C.AccountNo, SUM(R.BookingFee) AS SumRevenue FROM Reservation R, Customer C, Makes M WHERE M.ResrNo = R.ResrNo AND M.AccountNo = C.AccountNo GROUP BY  C.AccountNo) summed GROUP BY summed.AccountNo ORDER BY MAX(SumRevenue) DESC LIMIT 1;");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}