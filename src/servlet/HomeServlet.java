package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Flight;
import utils.*;

@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = MyUtils.getStoredConnection(request);
		 
        String tripType = (String) request.getParameter("tripType");
        String tripFrom = (String) request.getParameter("tripFrom");
        String tripTo = (String) request.getParameter("tripTo");
        String tripClass = (String) request.getParameter("class");
        int numPeople = 0;
        
        String departDate = (String) request.getParameter("departTime");
        String returnDate = (String) request.getParameter("returnDate");

        
        try {
        	numPeople = Integer.parseInt(request.getParameter("numPeople"));
        } catch (Exception e) {
        }

		List<Flight> list = null;
		String errorString = null;
		try {
			list = DBUtils.queryFlights(
					conn, tripType, tripFrom, tripTo, departDate, returnDate, tripClass, numPeople);
			if (list == null) {
				errorString = "Invalid input";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		
		if (list == null || list.isEmpty()){
//			doGet(request, response);
			RequestDispatcher dispatcher //
			= this.getServletContext().getRequestDispatcher("/WEB-INF/views/error.jsp");
			
			dispatcher.forward(request, response);
		}
		else{
			request.setAttribute("errorString", errorString);
			request.setAttribute("flightList", list);
	//        response.sendRedirect(request.getContextPath() + "/flights");
			RequestDispatcher dispatcher //
			= this.getServletContext().getRequestDispatcher("/WEB-INF/views/flightsListView.jsp");
			
			dispatcher.forward(request, response);
		}
 
	}

}