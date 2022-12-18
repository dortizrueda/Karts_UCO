package es.uco.pw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.data.DAO.PistaDAO;

/**
 * Servlet implementation class SearchPista_Karts
 */
@WebServlet("/SearchPista_Karts")
public class SearchPista_Karts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPista_Karts() {
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
		PistaDAO msd = new PistaDAO();
		ArrayList<PistaDTO>pista=new ArrayList();
		int type=Integer.parseInt(request.getParameter("num_karts"));
		pista=msd.getAll4(type);
			
			
		RequestDispatcher rd=request.getRequestDispatcher("MVC/view/verPistas.jsp");		
		request.setAttribute("pista", pista);
		rd.forward(request, response);
	}

}
