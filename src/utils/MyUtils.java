package utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Customer;
import classes.Employee;

public class MyUtils {

	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

	private static final String ATT_NAME_EMAIL = "ATTRIBUTE_FOR_STORE_EMAIL_IN_COOKIE";

	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}

	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}

	public static void storeLoginedCustomer(HttpSession session, Customer customer) {

		session.setAttribute("loginedCustomer", customer);
	}

	public static void storeLoginedEmployee(HttpSession session, Employee employee) {
		session.setAttribute("loginedEmployee", employee);
	}
	
	public static Customer getLoginedCustomer(HttpSession session) {
		Customer loginedCustomer = (Customer) session.getAttribute("loginedCustomer");
		return loginedCustomer;
	}
	
	public static Employee getLoginedEmployee(HttpSession session) {
		Employee loginedEmployee = (Employee) session.getAttribute("loginedEmployee");
		return loginedEmployee;
	}

	public static void storeUserCookie(HttpServletResponse response, Customer customer) {
		Cookie cookieEmail = new Cookie(ATT_NAME_EMAIL, customer.getEmail());
		cookieEmail.setMaxAge(24 * 60 * 60);
		response.addCookie(cookieEmail);
	}
	
	public static void storeEmployeeCookie(HttpServletResponse response, String username) {
		Cookie cookieUser = new Cookie(ATT_NAME_EMAIL, username);
		response.addCookie(cookieUser);
	}

	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ATT_NAME_EMAIL.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieEmail = new Cookie(ATT_NAME_EMAIL, null);
		cookieEmail.setMaxAge(0);
		response.addCookie(cookieEmail);
	}

}