package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBCon.DBCon;

import java.io.IOException;

@WebServlet("/create")
public class createServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/create.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession();
        System.out.println("Create~~~~~");
    }
 
}
