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
		Connection conn = MyUtils.getStoredConnection(request);
		 
        Customer loginedCustomer = MyUtils.getLoginedCustomer(session);
        Employee loginedEmployee = MyUtils.getLoginedEmployee(session);
        if (loginedEmployee == null && loginedCustomer == null) {
        	response.sendRedirect(request.getContextPath() + "/login");
            return;
        } else if (loginedCustomer != null || (loginedEmployee != null && !loginedEmployee.isManager())) {
    		request.setAttribute("NoPermission", "Sorry, you do not have permission to view this!");
        }
       
		String [] customerRevenue = DBUtils.getCustomerMostRevenue(conn);
		String [] repRevenue = null;
		if (loginedEmployee != null && loginedEmployee.isManager())
			repRevenue = DBUtils.getRepMostRevenue(conn);
		
		request.setAttribute("customerRevenue", customerRevenue);
		if (loginedEmployee != null && loginedEmployee.isManager())
			request.setAttribute("repRevenue", repRevenue);
		
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ManagerReport.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = MyUtils.getStoredConnection(request);

		String [] customerRevenue = DBUtils.getCustomerMostRevenue(conn);
		String [] repRevenue = DBUtils.getRepMostRevenue(conn);
		request.setAttribute("customerRevenue", customerRevenue);
		request.setAttribute("repRevenue", repRevenue);
		
		String mmyyyy = (String) request.getParameter("monthyear");
		if (mmyyyy != "") {
			List<String[]> salesReportByMonth = DBUtils.generateSalesReportByMonth(conn, mmyyyy);
			request.setAttribute("salesReportByMonth", salesReportByMonth);
		}
		List<String [] > summaryListing = null;
		if (request.getParameter("airlineflight") != "") {
			summaryListing = DBUtils.getSummaryListing(conn, request.getParameter("airlineflight"), "flight");
		} else if ( request.getParameter("destinationCity") != "") {
			summaryListing = DBUtils.getSummaryListing(conn, request.getParameter("destinationCity"), "city");
		} else if (request.getParameter("customer") != "") {
			summaryListing = DBUtils.getSummaryListing(conn, request.getParameter("customer"), "customer");
		}
		request.setAttribute("summaryListing", summaryListing);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ManagerReport.jsp");
		dispatcher.forward(request, response);
		
		}

}