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
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/CheckResult")
public class checkServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/check.jsp").forward(request, response);

    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession(true);
        System.out.println("Check~~~");
    	DBCon dbcon = new DBCon("final", "root", "1234", "127.0.0.1");
        String userID = request.getParameter("userID");
        String passwd = request.getParameter("passwd");
        ResultSet rs =  dbcon.checkReservation(userID, passwd);
        String date, time;
        PrintWriter pw = response.getWriter();
        try {
       	 	pw.println("<p> Check Record!!! </p>");
	        while(rs.next()) {
	        	 date = rs.getString("date");
	        	 time = rs.getString("time");
	        	 pw.println("<p>" + date + " " + time + "</p>");
	        	 System.out.println(date + " " + time);
	        }
       	 	pw.println("<p> Check complete!!! </p>");
            pw.println("<a href=\"/test87/loginSuceesfully\">Go to menu</a>");

        }catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
        
    }
    
}
