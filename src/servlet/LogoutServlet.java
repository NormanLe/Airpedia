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

@WebServlet(urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        MyUtils.deleteUserCookie(response);
        
        session.removeAttribute(MyUtils.getUserNameInCookie(request));
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/login");
        
//		RequestDispatcher dispatcher 
//				= this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
//		
//		dispatcher.forward(request, response);
		
		

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
	}
	

}