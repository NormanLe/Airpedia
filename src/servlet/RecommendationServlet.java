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

import classes.Flight;
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
		

		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
		Flight bestSeller = null;
		try {
			bestSeller = DBUtils.bestSeller(conn);
		} catch (SQLException e) {
			System.out.println("No best seller");
		}
		request.setAttribute("errorString", errorString);
		request.setAttribute("bestSeller", bestSeller);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/recommendationsView.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		}

}