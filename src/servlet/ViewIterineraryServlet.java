package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class ViewIterinerary
 */

@WebServlet(urlPatterns = { "/iterinerary" })
public class ViewIterineraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public ViewIterineraryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = MyUtils.getStoredConnection(request);

		int resrNo = Integer.parseInt(request.getParameter("code"));
		List<String[]> list = DBUtils.getItinerary(conn, resrNo);
		System.out.print(list.get(0)[0]);
		request.setAttribute("list", list);
		request.setAttribute("resrNo", resrNo);
		RequestDispatcher dispatcher
        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Iterinerary.jsp");
dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
