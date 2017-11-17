package utils;

import java.sql.*;

class Database {
	private Connection con;
	
	public Database() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airpedia", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/airpedia", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  "
						+ rs.getString(3));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void addEmployee(int id, int ssn, boolean isManager, Date startDate, double hourlyRate, double newRate) {
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO Employee(Id, SSN, IsManager, StartDate, HourlyRate) VALUES (%d, %d, %b, %t, %f", id, ssn, isManager, startDate, hourlyRate, newRate));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet listReservationsByFlightNo(String s) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery("SELECT * FROM Reservation R WHERE R.ResrNo IN (SELECT I.ResrNo FROM Includes I WHERE I.FlightNo = " + s);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private ResultSet listReservationsById(String s) {
		try {
			Statement stmt = con.createStatement();
			return stmt.executeQuery("SELECT * FROM Reservation R WHERE R.ResrNo IN (SELECT M.ResrNo FROM Makes M WHERE M.Id = " + s);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}