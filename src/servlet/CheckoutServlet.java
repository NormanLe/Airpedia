package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.*;
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
		
//        String queryString = request.getQueryString();
//      
//        System.out.println("-------------------" + queryString);
        
		String seatNum = request.getParameter("seatNum");
		String airline = request.getParameter("airline");
		String flight = request.getParameter("flight");				
		String fare = request.getParameter("fare");
		double totalFare = Double.parseDouble(fare);
		String food = request.getParameter("food");
		String tripType = request.getParameter("tripType");
		String classType = request.getParameter("classType");
		String departLegNo = request.getParameter("depLegNo");
		String arriveLegNo = request.getParameter("arrLegNo");
		
		Connection conn = MyUtils.getStoredConnection(request);
		HttpSession session = request.getSession();
		Reservation r = new Reservation();
		r.setBookingFee(totalFare * .1);
		r.setTotalFare(totalFare);
		r.setResrNo((int)(Math.random() * 2000000000));
		r.setEmployee(new Employee());
		r.setCustomer(MyUtils.getLoginedCustomer(session));
		
		
//		System.out.printf("helloCheckout \n airline: %s, flight: %s, departLegNo: %s, arriveLegNo: %s, seatNum: %s, fare: %s, food: %s, tripType: %s, classType: %s",
//				airline, flight, departLegNo, arriveLegNo, seatNum, fare, food, tripType, classType);
		
		
		Includes inc = new Includes();
		inc.setFlightClass("Economy");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("EST"));
		long time = cal.getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date(time);
		inc.setDate(sdf.format(date));
		inc.setAirlineId(airline);
		inc.setFlightNo(Integer.parseInt(flight));
		inc.setReservation(r);
		inc.setFlightClass(classType);
		inc.setLegNo(Integer.parseInt(departLegNo));
		inc.setMeal(food);
		inc.setSeatNo(seatNum);
		inc.setFromStopNo(Integer.parseInt(departLegNo));
		Leg l = new Leg();
		l.setLegNo(Integer.parseInt(departLegNo));
		inc.setFromStopNo(Integer.parseInt(departLegNo));
		inc.setLeg(l);
		Makes m = new Makes();
		m.setReservation(r);
		m.setCustomer(MyUtils.getLoginedCustomer(session));
		DBUtils.addReservation(conn, r, inc, m);
		
		
		
        response.sendRedirect(request.getContextPath() + "/reservationList");
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request, response);
    }
 
}
