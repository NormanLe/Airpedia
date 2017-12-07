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
 
import classes.Reservation;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/checkout" })
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CheckoutServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/checkoutView.jsp");
//        String queryString = request.getQueryString();
//		
//		String airline = request.getParameter("airline");
//		String flight = request.getParameter("flight");
//		String departAirport = request.getParameter("depart");
//		String arriveAirport = request.getParameter("arrive");
//		String numSeats = request.getParameter("numSeats");
//		String seat = request.getParameter("seat");
//		
//		System.out.printf("hello \n airline: %s, flight: %s, depart: %s, arrive: %s, numSeats: %s, seat: %s",
//				airline, flight, departAirport, arriveAirport, numSeats, seat);
		
        String queryString = request.getQueryString();
      
        System.out.println("-------------------" + queryString);
        
		String seatNum = request.getParameter("seatNum");
		String airline = request.getParameter("airline");
		String flight = request.getParameter("flight");				
		String fare = request.getParameter("fare");
		String food = request.getParameter("food");
		String tripType = request.getParameter("tripType");
		String classType = request.getParameter("classType");
		String departLegNo = request.getParameter("depLegNo");
		String arriveLegNo = request.getParameter("arrLegNo");
		
		System.out.printf("helloCheckout \n airline: %s, flight: %s, departLegNo: %s, arriveLegNo: %s, seatNum: %s, fare: %s, food: %s, tripType: %s, classType: %s",
				airline, flight, departLegNo, arriveLegNo, seatNum, fare, food, tripType, classType);
		request.setAttribute("seatNum", seatNum);
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        // add reservation
       
    }
 
}
