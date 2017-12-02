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
		Flight tempBestSeller = null;
		FlightData bestSeller = null;
		List <Flight> personalizedFlights = null;
		List <FlightData> formattedFlights = new ArrayList<>();
		try {
			tempBestSeller = DBUtils.bestSeller(conn);
			bestSeller= DBUtils.getFlightDataFromAirlineFlight(conn, tempBestSeller.getAirline().getId(), tempBestSeller.getFlightNo());

			personalizedFlights = DBUtils.personalizedFlights(conn, loginedCustomer.getAccountNo());
			for (int i = 0; i < personalizedFlights.size(); i++) {
				formattedFlights.add(DBUtils.getFlightDataFromAirlineFlight(conn, personalizedFlights.get(i).getAirline().getId(),personalizedFlights.get(i).getFlightNo()));
				System.out.print("formatted flight is " + formattedFlights.get(i));
			}
		} catch (SQLException e) {
			System.out.println("No best seller");
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("bestSeller", bestSeller);
		request.setAttribute("personalizedFlights", formattedFlights);
		
		
			
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/recommendationsView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		}

}