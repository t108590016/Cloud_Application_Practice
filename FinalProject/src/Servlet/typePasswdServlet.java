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

@WebServlet("/typePasswd")
public class typePasswdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/typePasswd.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession();
        System.out.println("Create~~~~~");
        String inputID = request.getParameter("inputID");
        //String inputPassword = DigestUtils.sha512Hex(request.getParameter("inputPassword"));  //嚙踝蕭�嚙踐�蕭��蕭��蕭嚙�
        String inputPassword = request.getParameter("inputPassword");
        String inputPassword2 = request.getParameter("inputPassword2");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        boolean createResult = false;
        //try{
        	DBCon dbcon = new DBCon("final", "root", "1234", "127.0.0.1");
        	//if(dbcon.checkLogin(inputID, inputPassword) == 1){
        	if(!inputPassword.equals(inputPassword2)) {
                System.out.println("Wrong Password!!!!!!!!!!!!!!!!!");
                System.out.println(inputPassword);
                request.setAttribute("error", "��嚙踐�嚙踝�蕭謢塗祗");
                //request.getRequestDispatcher("/WEB-INF/view/loginFailed.jsp").forward(request, response);
            	createResult = false;
            }
        	else if(dbcon.checkLogin(inputID, inputPassword) == 2){
        	//else if(!inputID.equals("test")) {
                System.out.println("This Account does not exist!!!!!!!!!!!!!!!!!");
                request.setAttribute("userID", inputID);
                request.setAttribute("passwd", inputPassword);
                //request.getRequestDispatcher("/WEB-INF/view/loginFailed.jsp").forward(request, response);
                createResult = dbcon.create(inputID, inputPassword, name, phone, email);
            }
            PrintWriter pw = response.getWriter();
            if(createResult) {
	        	 pw.println("<p> Create account success!!! </p>");
                 pw.println("<a href=\"/test87/login\">Login</a>");
            }
            else {
	        	 pw.println("<p> Create account failed QQ!!! </p>");
                 pw.println("<a href=\"/test87/create\">create</a>");

           }
    }
 
}
