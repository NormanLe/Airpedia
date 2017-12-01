package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import classes.Customer;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/profile" })
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ProfileServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
 
        // Check User has logged on
        Customer loginedCustomer = MyUtils.getLoginedCustomer(session);
       
        // Not logged in
        if (loginedCustomer == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
       
        // Store info to the request attribute before forwarding.
        request.setAttribute("customer", loginedCustomer);
 
        // If the user has logged in, then forward to the page
        // /WEB-INF/views/profileView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
