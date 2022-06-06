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

@WebServlet("/loginSuceesfully")
public class successServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("inputID", request.getParameter("inputID"));
        request.setAttribute("passwd", request.getParameter("passwd"));
    	request.getRequestDispatcher("/loginSuceesfully.jsp").forward(request, response);

    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession();
        System.out.println("Check~~~");
        PrintWriter pw = response.getWriter();
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");
        System.out.println(name + " " + passwd);
        request.setAttribute("inputID", name);
        request.setAttribute("inputPassword", passwd);
    }
}
