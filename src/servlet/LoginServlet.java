package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Customer;
import classes.Employee;
import classes.Person;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher //
				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userType = request.getParameter("userType");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Customer customer = null;
		Employee employee = null;
		
		boolean hasError = false;
		String errorString = null;

		if (username == null || password == null || username.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		} else {
			Connection conn = MyUtils.getStoredConnection(request);
			try {
				// Find the user in the DB.
				if (userType.equals("customer")) {
					customer = DBUtils.findCustomer(conn, username, password);
				} else {
					employee = DBUtils.findEmployee(conn, Integer.parseInt(password));
					if (employee != null) {
						Person p = DBUtils.findPersonById(conn, employee.getId());
						if (p != null && !username.toLowerCase().equals(p.getLastName().toLowerCase() + employee.getId())) {
							hasError = true;
							errorString = "Username or password invalid";
						}
					}
				}
		
				if (customer == null && employee == null) {
					hasError = true;
					errorString = "Username or password invalid";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}

		if (hasError) {
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			
			if (userType.equals("customer")) {
				MyUtils.storeLoginedCustomer(session, customer);
			} else {
				MyUtils.storeLoginedEmployee(session, employee);
			}
			response.sendRedirect(request.getContextPath());
		}
	}

}