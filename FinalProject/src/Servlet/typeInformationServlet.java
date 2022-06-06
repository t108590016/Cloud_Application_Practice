package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBCon.DBCon;

import java.io.IOException;

@WebServlet("/typeInformation")
public class typeInformationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/information.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession();
        System.out.println("Create~~~~~");
        String userID = request.getParameter("userID");
        String passwd = request.getParameter("passwd");

        String name = request.getParameter("name");
        //String inputPassword = DigestUtils.sha512Hex(request.getParameter("inputPassword"));  //嚙踝蕭�嚙踐�蕭��蕭��蕭嚙�
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
        //try{
        DBCon dbcon = new DBCon("final", "root", "1234", "127.0.0.1");
        	//if(dbcon.checkLogin(inputID, inputPassword) == 1){
        dbcon.create(userID, passwd, name, phone, email);
    }
 
}
