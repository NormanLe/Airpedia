package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Airline;
import classes.Customer;
import classes.Employee;
import classes.Flight;
import classes.FlightData;
import classes.Includes;
import classes.Leg;
import classes.Makes;
import classes.Person;
import classes.Reservation;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(urlPatterns = {"/auctions"})
public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int id = (int)(Math.random() * 200000000);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 
        Customer loginedCustomer = MyUtils.getLoginedCustomer(session);
        Employee loginedEmployee = MyUtils.getLoginedEmployee(session);
        if (loginedCustomer == null && loginedEmployee == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
       
        request.setAttribute("customer", loginedCustomer);
        
		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
		List<FlightData> auctionFlights = new ArrayList<FlightData>();
		List<Flight> flights = null;
		flights = DBUtils.getAllFlights(conn);
		for (int i = 0; i < flights.size(); i++) {
			List<Leg> legs = DBUtils.getLegsForFlight(conn, flights.get(i).getAirline().getId(), flights.get(i).getFlightNo());
			for (Leg l : legs) {
				auctionFlights.add(DBUtils.getFlightDataFromAirlineFlight(conn, l.getAirline().getId(), l.getFlight().getFlightNo(), DBUtils.findCityFromAirport(conn, l.getDepAirportId()), DBUtils.findCityFromAirport(conn, l.getArrAirportId())));
			}
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("auctionFlights", auctionFlights);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/auctions.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bidAmounts[] = request.getParameterValues("bidAmount");
		String flights[] = request.getParameterValues("data");
		Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session = request.getSession();
		boolean hasError = true;
		boolean success = false;
		String errorString = "No bids made.";
		
	    for (int i = 0; i < bidAmounts.length; i++) {
	    	String bids = bidAmounts[i];
	    	if (!bids.isEmpty()) {
	    		hasError = false;
	    		errorString = "Bid is too low.";
	    		double bid = Double.parseDouble(bids);
	    		String[] data = flights[i].split(",");
	    		double hiddenFare = Double.parseDouble(data[4]);
	    		if (bid >= hiddenFare) {
	    			Reservation r = new Reservation();
	    			r.setBookingFee(bid * .1);
	    			r.setTotalFare(bid * 1.1);
	    			r.setResrNo((int)(Math.random() * 2000000000));
	    			r.setEmployee(new Employee());
	    			r.setCustomer(MyUtils.getLoginedCustomer(session));
	    			
	    			Includes inc = new Includes();
	    			inc.setFlightClass("Economy");
	    			//${flight.airlineId},${flight.flightNo},${flight.departAirport},${flight.arrivalAirport},${flight.hiddenFare}
	    			FlightData fd = DBUtils.getFlightDataFromAirlineFlight(conn, data[0], Integer.parseInt(data[1]), DBUtils.findCityFromAirport(conn, data[2]), DBUtils.findCityFromAirport(conn, data[3]));
	    			inc.setDate(new Date(fd.getDepartDate().getTime()));
	    			inc.setReservation(r);
	    			inc.setFlightClass("Economy");
	    			inc.setLegNo(fd.getDepartLegNo());
	    			inc.setMeal("Sushi");
	    			inc.setSeatNo(DBUtils.generateSeatNumber(conn, data[0], Integer.parseInt(data[1])));
	    			inc.setFromStopNo(fd.getDepartLegNo());
	    			Leg l = DBUtils.getLegFromData(conn, data[0], Integer.parseInt(data[1]), data[2], data[3]);
	    			

	    			Makes m = new Makes();
	    			m.setReservation(r);
	    			m.setCustomer(MyUtils.getLoginedCustomer(session));
	    			DBUtils.addReservation(conn, r, inc, m);
	    			success = true;
	    			errorString = "Bid successful. Reservation created.";
	    		}
	    	}
	    }
		request.setAttribute("errorString", errorString);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/auctions.jsp");
		dispatcher.forward(request, response);

	}
}