package servlet;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.*;
import utils.*;

@WebServlet(urlPatterns = { "/makeReservation" })
public class MakeReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MakeReservationServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String s = request.getQueryString();
		String arrive = request.getParameter("arrive");
//		System.out.println(arrive);
//		System.out.println(s);
		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
		request.setAttribute("errorString", errorString);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/homeView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
//			request.setAttribute("errorString", errorString);
//			request.setAttribute("flightList", list);
	        response.sendRedirect(request.getContextPath() + "/checkout");
		
 
	}

}