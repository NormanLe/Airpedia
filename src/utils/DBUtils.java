package utils;

import java.sql.*;
import classes.Customer;
import classes.Employee;

public class DBUtils {
	private Connection con;

	public static Customer findCustomer(Connection conn, //
			String email, String password) throws SQLException {

		String sql = "Select c.email, c.Password from Customer c " //
				+ " where c.email = ? and c.password= ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, email);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			Customer user = new Customer();
			user.setEmail(email);
			user.setPassword(password);
			return user;
		}
		return null;
	}

	public static Customer findCustomer(Connection conn, String email) throws SQLException {

		String sql = "Select c.email, c.Password, Customer c"//
				+ " where c.email = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, email);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String password = rs.getString("Password");
			Customer customer = new Customer();
			customer.setEmail(email);
			customer.setPassword(password);
			return customer;
		}
		return null;
	}

	// TODO: Change these in future
	
	private static void addEmployee(Connection conn, Employee employee) {
//		try {
//			Statement stmt = conn.createStatement();
//			stmt.executeUpdate(String.format(
//					"UPDATE Employee(Id, SSN, IsManager, StartDate, HourlyRate) VALUES (%d, %d, %b, %t, %f", 
//					employee.getSsn(), employee.getemployee.getStartDate(),
//					isManager, startDate, hourlyRate, newRate));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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