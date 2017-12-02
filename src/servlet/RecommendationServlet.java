package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Customer;
import classes.Flight;
import classes.FlightData;
import utils.*;

@WebServlet(urlPatterns = { "/recommendations" })
public class RecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecommendationServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		 
        Customer loginedCustomer = MyUtils.getLoginedCustomer(session);
       
        if (loginedCustomer == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
       
        request.setAttribute("customer", loginedCustomer);
        

		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
		Flight bestSeller = null;
		FlightData best = null;
		List <Flight> personalizedFlights = null;
		try {
			bestSeller = DBUtils.bestSeller(conn);
			best= DBUtils.getFlightDataFromAirlineFlight(conn, bestSeller.getAirline().getId(), bestSeller.getFlightNo());
			System.out.print(best);
			personalizedFlights = DBUtils.personalizedFlights(conn, loginedCustomer.getAccountNo());
//			for (int i = 0; i < personalizedFlights.size(); i++DBUtils.getFlightDataFromAirlineFlight
		} catch (SQLException e) {
			System.out.println("No best seller");
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("bestSeller", best);
		request.setAttribute("personalizedFlights", personalizedFlights);
		
		
			
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/recommendationsView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		}

}