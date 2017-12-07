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
import classes.Employee;
import classes.Flight;
import classes.FlightData;
import utils.*;

@WebServlet(urlPatterns = { "/ManagerReport" })
public class ManagerReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ManagerReportServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		 
        Customer loginedCustomer = MyUtils.getLoginedCustomer(session);
        Employee loginedEmployee = MyUtils.getLoginedEmployee(session);
        if (loginedCustomer == null && loginedEmployee == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
       
		Connection conn = MyUtils.getStoredConnection(request);

		String [] customerRevenue = DBUtils.getCustomerMostRevenue(conn);
		String [] repRevenue = DBUtils.getRepMostRevenue(conn);
		request.setAttribute("customerRevenue", customerRevenue);
		request.setAttribute("repRevenue", repRevenue);
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ManagerReport.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		}

}