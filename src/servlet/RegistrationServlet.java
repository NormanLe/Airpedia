package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int id = (int)(Math.random() * 200000000);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		Connection conn = MyUtils.getStoredConnection(request);
		
		RequestDispatcher dispatcher 
		= this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userType = (String) request.getParameter("userType");
		//System.out.println(userType);
		String firstName = "";
		String lastName = "";
		String address = "";
		String city = "";
		String state = "";
		String zipCode = "";
		String email = "";
		String password = "";
		String phone = "";
		String creditCard = "";
		String ssn = "";
		String hourlyRate = "";
		Customer customer = new Customer();
		Employee employee = new Employee();
		
		if (userType.equals("customer")) {
			firstName = request.getParameter("firstname");
			lastName = request.getParameter("lastname");
			address = request.getParameter("address");
			city = request.getParameter("city");
			state = request.getParameter("state");
			zipCode = request.getParameter("zipcode");
			email = request.getParameter("email");
			password = request.getParameter("password");
			phone = request.getParameter("phone");
			creditCard = request.getParameter("creditcard");
			customer.setId(id);
			customer.setAccountNo(id);
			customer.getPerson().setFirstName(firstName);
			customer.getPerson().setLastName(lastName);
			customer.getPerson().setAddress(address);
			customer.getPerson().setCity(city);
			customer.getPerson().setId(id++);
			customer.getPerson().setState(state);
			customer.getPerson().setZipCode(zipCode);
			customer.setEmail(email);
			customer.setPassword(password);
			customer.setPhone(Double.parseDouble(phone));
			customer.setCreditcardNo(creditCard);
			
			customer.setCreationDate(new Date(System.currentTimeMillis()));
		} else {
			firstName = request.getParameter("firstname");
			lastName = request.getParameter("lastname");
			address = request.getParameter("address");
			city = request.getParameter("city");
			state = request.getParameter("state");
			zipCode = request.getParameter("zipcode");
			password = request.getParameter("password");
			ssn = request.getParameter("ssn");
			hourlyRate = request.getParameter("hourlyrate");
			employee.setId(id);
			employee.getPerson().setFirstName(firstName);
			employee.getPerson().setLastName(lastName);
			employee.getPerson().setFirstName(firstName);
			employee.getPerson().setLastName(lastName);
			employee.getPerson().setAddress(address);
			employee.getPerson().setCity(city);
			employee.getPerson().setId(id++);
			employee.getPerson().setState(state);
			employee.getPerson().setZipCode(zipCode);
			employee.setSsn(Integer.parseInt(ssn));
			employee.setHourlyRate(Double.parseDouble(hourlyRate));
			employee.setManager(userType.equals("manager"));
			employee.setStartDate(new Date(System.currentTimeMillis()));
			
		}
		boolean hasError = false;
		String errorString = null;

		if (userType.equals("customer")) {
			if (firstName.length() == 0 || lastName.length() == 0 || email.length() == 0 || password.length() == 0 || phone.length() == 0 || creditCard.length() == 0) {
				hasError = true;
				errorString = "Please fill in all fields.";
			}
		} else {
			if (firstName.length() == 0 || lastName.length() == 0 || password.length() == 0 || ssn.length() == 0 || hourlyRate.length() == 0) {
				hasError = true;
				errorString = "Please fill in all fields.";
			}
		}
		if (!hasError) {
			Connection conn = MyUtils.getStoredConnection(request);
			if (userType.equals("customer")) {
				DBUtils.addCustomer(conn, customer);
			} else {
				DBUtils.addEmployee(conn, employee);
			}
		}
		
		// If error, forward to /WEB-INF/views/login.jsp
		if (hasError) {
			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("customer", customer);

			// Forward to /WEB-INF/views/login.jsp
			RequestDispatcher dispatcher //
					= this.getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp");

			dispatcher.forward(request, response);
		}
		// If no error
		// Store user information in Session
		// And redirect to profile page.
		else {
			HttpSession session = request.getSession();
			// Redirect to userInfo page.
			response.sendRedirect(request.getContextPath());
		}
	}
}