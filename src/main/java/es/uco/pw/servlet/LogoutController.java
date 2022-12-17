package es.uco.pw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/LogoutController")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		
		if(customerBean == null || customerBean.getCorreo().equalsIgnoreCase("")){
		    response.setContentType("text/html");
			out.println("Tienes que registrarte");
			RequestDispatcher disp = request.getRequestDispatcher("/logoutViewFailure.jsp");
			disp.include(request, response);
		} 
		else{
		response.setContentType("text/html");
		RequestDispatcher disp = request.getRequestDispatcher("/logoutView.jsp");
		disp.include(request, response);
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
