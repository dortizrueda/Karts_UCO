package es.uco.pw.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.data.DAO.ReservaDAO;

/**
 * Servlet implementation class DeleteReserva2
 */
@WebServlet("/DeleteReserva2")
public class DeleteReserva2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReserva2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		
		int id_reserva= Integer.parseInt(request.getParameter("id_reserva"));		
		
		ReservaDAO msd=new ReservaDAO();
		int devuelto=msd.delete(id_reserva);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("MVC/view/deleteSuccess1.jsp");
		rd.forward(request, response);
	}

}
