package utils;
import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import classes.Customer;
 
public class MyUtils {
 
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
 
    private static final String ATT_NAME_EMAIL = "ATTRIBUTE_FOR_STORE_EMAIL_IN_COOKIE";
 
    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 
    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, Customer customer) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedCustomer", customer);
    }
 
    // Get the user information stored in the session.
    public static Customer getLoginedCustomer(HttpSession session) {
        Customer loginedCustomer = (Customer) session.getAttribute("loginedCustomer");
        return loginedCustomer;
    }
 
    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, Customer customer) {
//        System.out.println("Store user cookie");
        Cookie cookieEmail = new Cookie(ATT_NAME_EMAIL, customer.getEmail());
        // 1 day (Converted to seconds)
        cookieEmail.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieEmail);
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
 
    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieEmail = new Cookie(ATT_NAME_EMAIL, null);
        // 0 seconds (This cookie will expire immediately)
        cookieEmail.setMaxAge(0);
        response.addCookie(cookieEmail);
    }
 
}