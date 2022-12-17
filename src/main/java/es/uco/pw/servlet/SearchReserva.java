package es.uco.pw.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.DTO.ReservaDTO;
import es.uco.pw.data.DAO.ReservaDAO;

/**
 * Servlet implementation class SearchReserva
 */
@WebServlet("/SearchReserva")
public class SearchReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchReserva() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher =request.getRequestDispatcher("MVC/view/searchReservaView.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String fecha=request.getParameter("date");
		String fecha2=request.getParameter("date2");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date fech = null,fech2 = null;
		try {
			fech=sdf.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 fech2=sdf.parse(fecha2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReservaDAO msd = new ReservaDAO();
		ArrayList<ReservaDTO>reservas=new ArrayList<ReservaDTO>();
		reservas=msd.getListaFechas(fech,fech2);
		RequestDispatcher rd=request.getRequestDispatcher("MVC/view/verReservas.jsp");		
		request.setAttribute("reservas", reservas);
		rd.forward(request, response);

	}

}
