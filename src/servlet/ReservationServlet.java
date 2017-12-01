package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Customer;
import classes.Reservation;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/reservationList" })
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReservationServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		 
        Customer loginedCustomer = MyUtils.getLoginedCustomer(session);
       
        if (loginedCustomer == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
      
		Connection conn = MyUtils.getStoredConnection(request);

		String errorString = null;
		List<Reservation> list = null;
		list = DBUtils.queryReservationByCustomer(conn, loginedCustomer.getAccountNo());
		request.setAttribute("errorString", errorString);
		request.setAttribute("reservationList", list);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/reservationListView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}