package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBCon.DBCon;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reservationInformation")
public class reservationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        request.setAttribute("inputID", request.getParameter("inputID"));
        request.setAttribute("passwd", request.getParameter("passwd"));
        request.getRequestDispatcher("/Reservation.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession(true);
        String userID = request.getParameter("userID");
        String passwd = request.getParameter("passwd");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        int number = Integer.valueOf(request.getParameter("number"));
    	DBCon dbcon = new DBCon("final", "root", "1234", "127.0.0.1");
    	boolean reservationResult = dbcon.reservation(userID, passwd, date, time, number);
        System.out.println("Reservation~~~");
        PrintWriter pw = response.getWriter();
        if(reservationResult) {
        	 pw.println("<p> Reservation success!!! </p>");
             pw.println("<a href=\"/test87/loginSuceesfully\">Go to menu</a>");
        }
        else {
        	 pw.println("<p> Reservation failed QQ!!! </p>");
             pw.println("<a href=\"/test87/loginSuceesfully\">Go to menu</a>");
       }
        
    }
 
}
