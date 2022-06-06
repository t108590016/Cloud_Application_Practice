package Servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import DBCon.DBCon;

@WebServlet("/login")
public class loginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        HttpSession session = request.getSession();
        String inputID = request.getParameter("inputID");
        //String inputPassword = DigestUtils.sha512Hex(request.getParameter("inputPassword"));  //嚙踝蕭�嚙踐�蕭��蕭��蕭嚙�
        String inputPassword = request.getParameter("inputPassword");
        //try{
        	DBCon dbcon = new DBCon("final", "root", "", "127.0.0.1");
        	if(dbcon.checkLogin(inputID, inputPassword) == 1){
        	//if(inputID.equals("test") && !inputPassword.equals("1234")) {
                System.out.println("Wrong Password!!!!!!!!!!!!!!!!!");
                System.out.println(inputPassword);
                request.setAttribute("error", "��嚙踐�嚙踝�蕭謢塗祗");
                //request.getRequestDispatcher("/WEB-INF/view/loginFailed.jsp").forward(request, response);
            	response.sendRedirect("loginFailed");

                return;
            }
        	else if(dbcon.checkLogin(inputID, inputPassword) == 2){
        	//else if(!inputID.equals("test")) {
                System.out.println("This Account does not exist!!!!!!!!!!!!!!!!!");
                request.setAttribute("error", "��嚙踐�嚙踝�蕭謢塗祗");
                //request.getRequestDispatcher("/WEB-INF/view/loginFailed.jsp").forward(request, response);
            	response.sendRedirect("loginFailed");

                return;
            }
        	else {
        	//else if(inputID.equals("test") && inputPassword.equals("1234")){
                System.out.println("Login Suceesfullty嚚�������");
                request.setAttribute("name", inputID);
                request.setAttribute("passwd", inputPassword);

                //request.getRequestDispatcher("/WEB-INF/view/loginSuceesfully.jsp").forward(request, response);
            	response.sendRedirect("loginSuceesfully");

                return;
            }
        /*}catch (SQLException e){
            System.out.println("��嚙踐��蕭��甇對蕭����嚙踐乾嚙踝嚙踝蕭�嚙�");
            request.setAttribute("error", "��嚙踐��蕭��甇對蕭����嚙踐乾嚙踝嚙踝蕭�嚙�");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            return;
        }*/
        //response.sendRedirect("login");
    }
}

