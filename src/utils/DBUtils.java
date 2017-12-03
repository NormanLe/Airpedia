package utils;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.*;

public class DBUtils {
	private Connection con;

	public static Person findPersonById(Connection conn, int id) {
		String sql = "Select * from Person where id = " + id;
		Person person = null;
		try {
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		
			if (rs.next()) {
			String firstName = rs.getString("FirstName");
			String lastName = rs.getString("LastName");
			String address = rs.getString("Address");
			String city = rs.getString("City");
			String state = rs.getString("State");
			String zipCode = rs.getString("ZipCode");
			
			person = new Person();
			person.setId(id);
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setAddress(address);
			person.setCity(city);
			person.setState(state);
			person.setZipCode(zipCode);
			}
		} catch (Exception e) {
			System.out.println("error with finding person by id");
		}
		return person;
	}
	
	public static Person findPersonBySSN(Connection conn, int SSN) {
		try {
			return findPersonById(conn, findEmployee(conn, SSN).getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Customer findCustomer(Connection conn, String email, String password) throws SQLException {

		String sql = "Select * from Customer c" + " where c.email = ? and c.password = ?";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, email);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("Id");
			int number = rs.getInt("AccountNo");
			String creditcardNo = rs.getString("CreditCardNo");
			double phone = rs.getDouble("Phone");
			Date creationDate = rs.getDate("CreationDate");
			int rating = rs.getInt("Rating");

			Customer customer = new Customer();
			customer.setId(id);
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

		String sql = "Select * from Customer c" + " where c.email = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, email);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("Id");
			String password = rs.getString("Password");
			int number = rs.getInt("AccountNo");
			String creditcardNo = rs.getString("CreditCardNo");
			double phone = rs.getDouble("Phone");
			Date creationDate = rs.getDate("CreationDate");
			int rating = rs.getInt("Rating");

			Customer customer = new Customer();
			customer.setId(id);
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

		String sql = "Select * from Customer" + " where AccountNo = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, accountNo);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("Id");
			String email = rs.getString("Email");
			String password = rs.getString("Password");
			String creditcardNo = rs.getString("CreditCardNo");
			double phone = rs.getDouble("Phone");
			Date creationDate = rs.getDate("CreationDate");
			int rating = rs.getInt("Rating");

			Customer customer = new Customer();
			customer.setId(id);
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

		String sql = "Select * from Employee" + " where SSN = ? ";

		// Do I need to query for the person entity related to employee a.k.a
		// the parent class
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

	public static Airline findAirline(Connection conn, String airlineId) throws SQLException {
		String sql = "Select * from Airline" + " where Id = ? ";

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, airlineId);

		ResultSet rs = pstm.executeQuery();

		if (rs.next()) {
			String id = rs.getString("Id");
			String name = rs.getString("Name");
			Airline airline = new Airline(id, name);

			return airline;
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

				// Won't create the person references yet - not sure if
				// necessary yet
				Customer customer = findCustomer(conn, accountNo);
				Employee employee = findEmployee(conn, repSSN);
				Reservation reservation = new Reservation(resrNo, date, fee, fare, customer, employee);
				list.add(reservation);
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in querying reservations");
		}
		return list;
	}

	public static List<Reservation> queryReservationByCustomer(Connection conn, int accountNo) {
		String sql = "Select * from Reservation where AccountNo = " + accountNo;

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
				int repSSN = rs.getInt("RepSSN");

				// Won't create the person references yet - not sure if
				// necessary yet
				Customer customer = findCustomer(conn, accountNo);
				Employee employee = findEmployee(conn, repSSN);
				Reservation reservation = new Reservation(resrNo, date, fee, fare, customer, employee);
				list.add(reservation);
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in querying reservations");
		}
		return list;
	}

	public static List<FlightData> queryFlight(Connection conn) {
		String sql = "Select * from Flight ";
		System.out.println("what about here");
		List<FlightData> list = new ArrayList<>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			System.out.println("DOES IT GO HERE");
			while (rs.next()) {
				String airlineId = rs.getString("AirlineID");
				int flightNo = rs.getInt("FlightNo");
				list.add(getFlightDataFromAirlineFlight(conn, airlineId, flightNo));
				System.out.println("list now is " + list);
			}
		} catch (Exception e) {
			System.out.println("Something went wrong in querying resrvations");
		}
		return list;
	}

	public static List<FlightData> queryFlights(Connection conn, String tripType, String tripFrom, String tripTo,
			String departDate, String returnDate, String tripClass, int numPeople) throws SQLException {
		// String sql = "Select * " +
		// "From Flight Fl " +
		// "Where Fl.AirlineID IN " +
		// "(SELECT L.AirlineID " +
		// "From Leg L " +
		// "Where L.AirlineID IN " +
		// "(SELECT F.AirlineID " +
		// "From Fare F, Airport A1, Airport A2 " +
		// "Where F.AirlineID = L.AirlineID " +
		// "AND A1.City = ? " +
		// "AND A2.City = ? " +
		// "AND F.FareType = ? " +
		// "AND F.Class = ? " +
		// "AND Fl.NoOfSeats > ? " +
		// "AND L.DepAirportID = A1.Id " +
		// "AND L.ArrAirportID = A2.Id " +
		// "))";

		String sql = "Select * " + 
				"From Flight Fl " + 
				"Where Fl.AirlineID IN " + 
				"(SELECT L.AirlineID "+ 
				"From Leg L, Fare F, Airport A1, Airport A2 " + 
				"Where F.AirlineID = L.AirlineID "+ 
				"AND F.FareType = ? " + 
				"AND F.Class = ? " + 
				"AND Fl.NoOfSeats >= ? ";
		
		if (!tripFrom.isEmpty() || !tripTo.isEmpty()) {
			if (!tripFrom.isEmpty()) {
				sql += "AND A1.City = ? ";
				sql += "AND L.DepAirportID = A1.Id ";
			}
			if (!tripTo.isEmpty()) {
				sql += "AND A2.City = ? ";
				sql += "AND L.ArrAirportID = A2.Id ";
			}
		}

		 String pattern =
		 "^\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
		 Pattern r = Pattern.compile(pattern);
		 Matcher mDepart = null, mReturn = null;
		
		 if (departDate != null) {
			 mDepart = r.matcher(departDate);
			 if (mDepart.find()) {
				 sql += "AND L.DepTime LIKE ? ";
				 mDepart = r.matcher(departDate);
			 }
		 }
		 if (returnDate != null) {
			 mReturn = r.matcher(returnDate);
			 if (mReturn.find()) {
				 sql += "AND L.ArrTime LIKE ? ";

				 mReturn = r.matcher(returnDate);
			 }
		 }
		sql += ")";
		int numArgs = 1;
		// Guaranteed strings are: fareType (roundway/one trip), class
		// (business/economy/first), numPeople
		// Empty strings are: tripFrom, tripTo, departDate, returnDate
		// prepare statements at the end, maybe add the strings to a list and
		// append em all at the end
		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, tripType);
		pstm.setString(++numArgs, tripClass);
		pstm.setInt(++numArgs, numPeople);

		if (!tripFrom.isEmpty()) {
			pstm.setString(++numArgs, tripFrom);
		}

		if (!tripTo.isEmpty()) {
			pstm.setString(++numArgs, tripTo);
		}
		
		 if (mDepart != null && mDepart.find()) {		
			 String temp = "%" + departDate + "%";
			 pstm.setString(++numArgs, temp);
		 }
		 if (mReturn != null && mReturn.find()) {
			 String temp = "%" + returnDate + "%";
			 pstm.setString(++numArgs, temp);
		 }

		List<FlightData> list = new ArrayList<>();
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String airlineId = rs.getString("AirlineID");
			int flightNo = rs.getInt("FlightNo");
			int numSeats = rs.getInt("NoOfSeats");
			String daysOperating = rs.getString("DaysOperating");
			int minStay = rs.getInt("MinLengthOfStay");
			int maxStay = rs.getInt("MaxLengthOfStay");
			Airline airline = findAirline(conn, airlineId);
			FlightData flight =getFlightDataFromAirlineFlight(conn, airlineId, flightNo);
			//new Flight(airline, flightNo, numSeats, daysOperating, minStay, maxStay);
			list.add(flight);
		}
		return list;
	}

	public static FlightData bestSeller(Connection conn) throws SQLException {
		String sql = "SELECT I.ResrNo, COUNT(F.FlightNo) AS NumFlights" + " FROM Flight F, Includes I"
				+ " WHERE F.FlightNo = I.FlightNo" + " AND F.AirlineId = I.AirlineId" + " GROUP BY I.ResrNo"
				+ " ORDER BY NumFlights DESC LIMIT 1";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		FlightData flight = null;
		if (rs.next()) {
			String resrNo = rs.getString("ResrNo");
			System.out.println(resrNo);

			String newSql = "select * from includes i , flight f where f.flightno = i.flightno and f.airlineid = i.airlineid and i.resrno = "
					+ resrNo + " LIMIT 1";
			PreparedStatement p = conn.prepareStatement(newSql);
			ResultSet r = p.executeQuery();

			if (r.next()) {
				String airlineId = r.getString("AirlineID");
				int flightNo = r.getInt("FlightNo");
				flight = getFlightDataFromAirlineFlight(conn, airlineId, flightNo);
			}

		}
		return flight;
	}

	public static List<FlightData> personalizedFlights(Connection conn, int accountNo) throws SQLException {
		String sql = "SELECT * FROM FLIGHT F" + " WHERE F.FlightNo NOT IN" + " (SELECT I.FlightNo"
				+ " FROM Makes M, Includes I" + " WHERE M.AccountNo = " + accountNo + " AND I.ResrNo = M.ResrNo)";

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<FlightData> flights = new ArrayList<>();

		while (rs.next()) {
			String airlineId = rs.getString("AirlineId");
			int flightNo = rs.getInt("FlightNo");
			flights.add(getFlightDataFromAirlineFlight(conn, airlineId, flightNo));
		}
		return flights;
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

	public static List<Flight> getAllFlights(Connection conn) {
		String sql = "SELECT * FROM Flight;";

		List<Flight> list = new ArrayList<Flight>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Flight f = new Flight();
				f.setFlightNo(rs.getInt(2));
				f.setNoOfSeats(rs.getInt(3));
				f.setDaysOperating(rs.getString(4));
				f.setMinLengthOfStay(rs.getInt(5));
				f.setMaxLengthOfStay(rs.getInt(6));
				list.add(f);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Reservation> getReservationByFlightNumber(Connection conn, int flightNo) {
		String sql = String.format(
				"SELECT * FROM Reservation R WHERE R.ResrNo IN (SELECT I.ResrNo FROM Includes I WHERE I.FlightNo = %d);",
				flightNo);

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation();
				r.setBookingFee(rs.getDouble("BookingFee"));
				r.setTotalFare(rs.getDouble("TotalFare"));
				r.setResrDate(rs.getDate("ResrDate"));
				r.setResrNo(rs.getInt("ResrNo"));
				r.setEmployee(DBUtils.findEmployee(conn, rs.getInt("RepSSN")));
				r.setCustomer(DBUtils.findCustomer(conn, rs.getInt("AccountNo")));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Reservation> getReservationByCustomerNumber(Connection conn, int id) {
		String sql = String.format(
				"SELECT * FROM Reservation R WHERE R.ResrNo IN (SELECT M.ResrNo FROM Makes M WHERE M.Id = :Id);", id);

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation();
				r.setBookingFee(rs.getDouble("BookingFee"));
				r.setTotalFare(rs.getDouble("TotalFare"));
				r.setResrDate(rs.getDate("ResrDate"));
				r.setResrNo(rs.getInt("ResrNo"));
				r.setEmployee(DBUtils.findEmployee(conn, rs.getInt("RepSSN")));
				r.setCustomer(DBUtils.findCustomer(conn, rs.getInt("AccountNo")));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Reservation> getRevenueByFlight(Connection conn, int flightNo) {
		String sql = String.format(
				"SELECT R.ResrNo, R.BookingFee FROM Reservation R WHERE R.ResrNo IN (SELECT I.ResrNo FROM Includes I, Leg L WHERE L.FlightNo = %d AND L.FlightNo = I.FlightNo)",
				flightNo);

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation();
				r.setBookingFee(rs.getDouble("BookingFee"));
				r.setResrNo(rs.getInt("ResrNo"));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Reservation> getRevenueByDestination(Connection conn, String city) {
		String sql = String.format(
				"SELECT DISTINCT I.ResrNo, R.BookingFee, A.City FROM Includes I, Leg L, Airport A, Reservation R WHERE L.ArrAirportID = A.Id AND A.City = %s AND R.ResrNo = I.ResrNo AND L.AirlineId = I.AirlineId AND L.FlightNo = I.FlightNo;",
				city);

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation();
				r.setBookingFee(rs.getDouble("BookingFee"));
				r.setResrNo(rs.getInt("ResrNo"));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Reservation> getRevenueByCustomer(Connection conn, int accountNo) {
		String sql = String.format(
				"SELECT R.ResrNo, R.BookingFee FROM Reservation R WHERE R.ResrNo IN (SELECT M.ResrNo FROM Makes M WHERE M.AccountNo = %d)",
				accountNo);

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation();
				r.setBookingFee(rs.getDouble("BookingFee"));
				r.setResrNo(rs.getInt("ResrNo"));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	// [accountNo, totalRevenue]
	public static int[] getCustomerMostRevenue(Connection conn) {
		String sql = "SELECT summed.AccountNo, MAX(SumRevenue) FROM (SELECT C.AccountNo, SUM(R.BookingFee) AS SumRevenue FROM Reservation R, Customer C, Makes M WHERE M.ResrNo = R.ResrNo AND M.AccountNo = C.AccountNo GROUP BY  C.AccountNo) summed GROUP BY summed.AccountNo ORDER BY MAX(SumRevenue) DESC LIMIT 1;";

		int[] arr = new int[2];
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				arr[0] = rs.getInt("AccountNo");
				arr[1] = rs.getInt("SumRevenue");
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return arr;
	}

	public static int[] getRepMostRevenue(Connection conn) {
		String sql = "SELECT summed.RepSSN, MAX(SumRevenue) FROM (SELECT RepSSN, SUM(BookingFee) AS SumRevenue FROM Reservation GROUP BY RepSSN) summed GROUP BY summed.RepSSN ORDER BY MAX(SumRevenue) DESC LIMIT 1;";

		int[] arr = new int[2];
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				arr[0] = rs.getInt("RepSSN");
				arr[1] = rs.getInt("SumRevenue");
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return arr;
	}

	// [flightNo, numflights]
	public static int[] getMostActiveFlight(Connection conn) {
		String sql = "SELECT counted.FlightNo, MAX(NumFlights)FROM (SELECT F.FlightNo, COUNT(F.FlightNo) AS NumFlights FROM Flight F GROUP BY  F.FlightNo) counted GROUP BY counted.FlightNo ORDER BY MAX(NumFlights) DESC LIMIT 1;";

		int[] arr = new int[2];
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				arr[0] = rs.getInt("FlightNo");
				arr[1] = rs.getInt("NumFlights");
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return arr;
	}

	public static List<Customer> getCustomersOnFlight(Connection conn, int flightNo) {
		String sql = String.format(
				"SELECT * FROM Customer C WHERE C.AccountNo IN (SELECT RP.AccountNo FROM ReservationPassenger RP, Includes I WHERE RP.ResrNo = I.ResrNo AND I.FlightNo = %d);",
				flightNo);

		List<Customer> list = new ArrayList<Customer>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Customer c = new Customer();
				c.setAccountNo(rs.getInt("AccountNo"));
				c.setCreditcardNo(rs.getString("CreditCardNo"));
				c.setEmail(rs.getString("Email"));
				c.setPassword(rs.getString("Password"));
				c.setPhone(rs.getDouble("Phone"));
				c.setCreationDate(rs.getDate("CreationDate"));
				c.setRating(rs.getInt("Rating"));
				list.add(c);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Flight> getFlightsForAirport(Connection conn, String airportId) {
		String sql = String.format(
				"SELECT * FROM Flight F WHERE F.FlightNo IN (SELECT L.FlightNo FROM Leg L, Airport A, Flight F WHERE (L.DepAirportID = A.Id OR L.ArrAirportID = A.Id) AND A.Id = %s AND F.AirlineId = L.AirlineId);",
				airportId);

		List<Flight> list = new ArrayList<Flight>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Flight f = new Flight();
				f.setFlightNo(rs.getInt("FlightNo"));
				f.setNoOfSeats(rs.getInt("NoOfSeats"));
				f.setDaysOperating(rs.getString(rs.getString("DaysOperating")));
				f.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				f.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				list.add(f);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Flight> getDelayedFlights(Connection conn) {
		String sql = "SELECT * FROM Flight F WHERE F.FlightNo IN (SELECT F1.FlightNo, COUNT(F1.AirlineID) AS num_flights, SUM(F1.AirlineID) AS num_onTime FROM Flight F1 GROUP BY F1.FlightNo HAVING num_flights = num_onTime);";

		List<Flight> list = new ArrayList<Flight>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Flight f = new Flight();
				f.setFlightNo(rs.getInt("FlightNo"));
				f.setNoOfSeats(rs.getInt("NoOfSeats"));
				f.setDaysOperating(rs.getString(rs.getString("DaysOperating")));
				f.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				f.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				list.add(f);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static void recordReservation(Connection conn, Reservation r) {
		try {
			String sql = "INSERT INTO Reservation(ResrNo, ResrDate, BookingFee, TotalFare, RepSSN, AccountNo) VALUES (?, ?, ?, ?, ?, ?);";

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, r.getResrNo());
			pstm.setDate(2, new Date(System.currentTimeMillis()));
			pstm.setDouble(3, r.getBookingFee());
			pstm.setDouble(4, r.getTotalFare());
			pstm.setInt(5, r.getEmployee().getSsn());
			pstm.setInt(6, r.getCustomer().getAccountNo());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void addCustomer(Connection conn, Customer c) { try {
	 * String sql =
	 * "INSERT INTO Customer(Id, AccountNo, CreditCardNo, Email, CreationDate, Rating) VALUES (?, ?, ?, ?, ?, ?);"
	 * ;
	 * 
	 * PreparedStatement pstm = conn.prepareStatement(sql); pstm.setInt(1,
	 * c.get); pstm.setDate(2, new Date(System.currentTimeMillis()));
	 * pstm.setDouble(3, r.getBookingFee()); pstm.setDouble(4,
	 * r.getTotalFare()); pstm.setInt(5, r.getEmployee().getSsn());
	 * pstm.setInt(6, r.getCustomer().getAccountNo()); pstm.executeUpdate(); }
	 * catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	public static List<String> getMailingList(Connection conn) {
		String sql = String.format("SELECT Email FROM CUSTOMER");

		List<String> list = new ArrayList<String>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("Email"));
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}

	public static List<Reservation> getReservationsForCustomer(Connection conn, int accountNo) {
		String sql = String.format(
				"SELECT * FROM Reservation R WHERE R.ResrNo IN (SELECT PR.ResrNo FROM ReservationPassenger PR, Customer C WHERE PR.AccountNo = %d) AND R.ResrDate = DATE(NOW());",
				accountNo);

		List<Reservation> list = new ArrayList<Reservation>();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation();
				r.setBookingFee(rs.getDouble("BookingFee"));
				r.setTotalFare(rs.getDouble("TotalFare"));
				r.setResrDate(rs.getDate("ResrDate"));
				r.setResrNo(rs.getInt("ResrNo"));
				r.setEmployee(DBUtils.findEmployee(conn, rs.getInt("RepSSN")));
				r.setCustomer(DBUtils.findCustomer(conn, rs.getInt("AccountNo")));
				list.add(r);
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return list;
	}
	
	public static double getCustomersBid(Connection conn, int accountNo, String airlineId, int flightNo, String classType, Date date) {
		String sql = String.format("SELECT A.NYOP FROM Auctions A WHERE A.AccountNo = %d AND A.AirlineID = '%s' AND A.FlightNo = %d AND A.Class = '%s' AND A.Date = %t ORDER BY Date DESC LIMIT 1;", accountNo, airlineId, flightNo, classType, date);
		
		double nyop = 0;
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				nyop = rs.getInt("NYOP");
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return nyop;
	}
	//fix
	public static Auction getBidHistory(Connection conn, int accountNo, String airlineId, int flightNo, String classType) {
		String sql = String.format("SELECT * FROM Leg WHERE AirlineID = '%s' AND FlightNo = %d;", airlineId, flightNo);
		String sql2 = String.format("SELECT * FROM Fare WHERE AirlineID = '%s' AND FlightNo = %d;", airlineId, flightNo);
		
		FlightData fd = new FlightData();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			PreparedStatement pstm2 = conn.prepareStatement(sql2);
			ResultSet rs = pstm.executeQuery();
			ResultSet rs2 = pstm2.executeQuery();
			while (rs.next()) {
				fd.setDepartAirport(rs.getString("DepAirportID"));
				fd.setArrivalAirport(rs.getString("ArrAirportID"));
				fd.setDepartDate(rs.getTimestamp("DepTime"));
				fd.setArrivalDate(rs.getTimestamp("ArrTime"));
				fd.setFare(rs2.getDouble("Fare"));
			}
		} catch (Exception e) {
			System.out.println("SQL Error.");
		}
		return null;
	}

	public static FlightData getFlightDataFromAirlineFlight(Connection conn, String airlineId, int flightNo) {
		String sql = String.format("SELECT * FROM Leg WHERE AirlineID = '%s' AND FlightNo = %d;", airlineId, flightNo);
		String sql2 = String.format("SELECT * FROM Fare WHERE AirlineID = '%s' AND FlightNo = %d;", airlineId, flightNo);
		
		FlightData fd = new FlightData();
		try {
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				fd.setDepartAirport(rs.getString("DepAirportID"));
				fd.setAirlineId(rs.getString("AirlineID"));
				ResultSet ap1 = conn.prepareStatement(String.format("SELECT * FROM Airport WHERE Id = '%s'", fd.getDepartAirport())).executeQuery();
				if (ap1.next()) {
					fd.setDepAirportName(ap1.getString("Name"));
					fd.setDepCity(ap1.getString("City"));
					fd.setDepCountry(ap1.getString("Country"));
				}
				fd.setArrivalAirport(rs.getString("ArrAirportID"));
				ResultSet ap2 = conn.prepareStatement(String.format("SELECT * FROM Airport WHERE Id = '%s'", fd.getArrivalAirport())).executeQuery();
				if (ap2.next()) {
					fd.setArrAirportName(ap2.getString("Name"));
					fd.setArrCity(ap2.getString("City"));
					fd.setArrCountry(ap2.getString("Country"));
				}

				fd.setDepartDate(rs.getTimestamp("DepTime"));
				fd.setArrivalDate(rs.getTimestamp("ArrTime"));
				fd.setLegNo(rs.getInt("LegNo"));
				fd.setFlightNo(rs.getInt("FlightNo"));
			}
			
			PreparedStatement pstm2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pstm2.executeQuery();
			if (rs2.next()) {
				fd.setFare(rs2.getDouble("Fare"));
				fd.setClassType(rs2.getString("Class"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fd;
	}
	
}