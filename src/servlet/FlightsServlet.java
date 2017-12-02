package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.*;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class Flights
 */
@WebServlet(urlPatterns = { "/flights" })
public class FlightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FlightsServlet() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
//		List<FlightData> list = DBUtils.queryFlight(conn);
		request.setAttribute("errorString", errorString);
//		request.setAttribute("flightList", list);
//		System.out.println("huh" + list);
		RequestDispatcher dispatcher //
		= this.getServletContext().getRequestDispatcher("/WEB-INF/views/flightsListView.jsp");

		dispatcher.forward(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
