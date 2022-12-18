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

import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.DTO.ReservaDTO;
import es.uco.pw.data.DAO.PistaDAO;
import es.uco.pw.data.DAO.ReservaDAO;

/**
 * Servlet implementation class SearchPista_Reserva
 */
@WebServlet("/SearchPista_Reserva")
public class SearchPista_Reserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchPista_Reserva() {
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
		ReservaDAO msu = new ReservaDAO();
		ArrayList<PistaDTO>pista1=new ArrayList();
		ArrayList<PistaDTO>pista=new ArrayList();
		ArrayList<ReservaDTO>reserva=new ArrayList();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		String date=request.getParameter("date");
		String time=request.getParameter("time");
		date=date+" "+time;
		Date fecha=null;
		try {
			fecha=sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		pista1=msd.getAll1();
		reserva=msu.getAll2(fecha);
		for(int i=0;i<pista1.size();i++) {
			for(int j=0;j<reserva.size();j++) {
				if(pista1.get(i).getNombre().equals(reserva.get(j).getId_pista())) {
					PistaDTO p=new PistaDTO();	
					if(i==0) {
							p=pista1.get(i);
							pista.add(p);
						}else {
							if(!pista.get(i).equals(p)) {
								p=pista1.get(i);
								pista.add(p);
							}
						}
						
				}
				
			}
			
		}
		RequestDispatcher rd=request.getRequestDispatcher("MVC/view/verPistas.jsp");		
		request.setAttribute("pista", pista);
		rd.forward(request, response);
	}

}
