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
 
        Customer loginedCustomer = MyUtils.getLoginedCustomer(session);
        Employee logedinEmployee = MyUtils.getLoginedEmployee(session);
        if (loginedCustomer == null && logedinEmployee == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        Connection conn = MyUtils.getStoredConnection(request);
        Person p = null;
		if (loginedCustomer != null) {
			p = DBUtils.findPersonById(conn, loginedCustomer.getId());
			request.setAttribute("customer", loginedCustomer);
		} 
		if (logedinEmployee != null) {
			p = DBUtils.findPersonBySSN(conn, logedinEmployee.getSsn());
			request.setAttribute("employee", logedinEmployee);
			if (logedinEmployee.isManager()) 
				request.setAttribute("manager", "Manager");
			else 
					request.setAttribute("manager", "Employee");
			
			
		}
		
		request.setAttribute("person", p);
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/profileView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
