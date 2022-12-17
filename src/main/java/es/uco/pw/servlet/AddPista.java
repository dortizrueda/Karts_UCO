package es.uco.pw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.business.ValueObject.Tipo;
import es.uco.pw.data.DAO.PistaDAO;

/**
 * Servlet implementation class AddPista
 */
public class AddPista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPista() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("MVC/view/menuAdmin.jsp");
		rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();

		PrintWriter out=response.getWriter();

		String nombre=request.getParameter("nombre");

		String dificultad=request.getParameter("dificultad");

		String disponibilidad=request.getParameter("disponibilidad");

		String capacity=request.getParameter("capacity");
		
		PistaDAO msd=new PistaDAO();
		if(nombre!=null) {
			if(!msd.check_pista(nombre)) {
				response.setContentType("text/html");
				out.println("Esta pista ya existe");
				RequestDispatcher rd=request.getRequestDispatcher("MVC/view/insertError.jsp");
				rd.include(request, response);
			}else {
				PistaDTO p=new PistaDTO();
				p.setNombre(nombre);
				p.setNumero_karts(Integer.parseInt(capacity));
				if(dificultad.compareTo("infantil")==0){
					p.setDificultad(Dificultad_Pista.infantil);
				}else if(dificultad.compareTo("familiar")==0){
					p.setDificultad(Dificultad_Pista.familiar);
				}else if(dificultad.compareTo("adultos")==0) {
					p.setDificultad(Dificultad_Pista.adultos);
				}
				if(disponibilidad.compareTo("disponible")==0){
					p.setDisponibilidad(Disponibilidad.disponible);
				}else if(disponibilidad.compareTo("no_disponible")==0){
					p.setDisponibilidad(Disponibilidad.no_disponible);
				}				

				int devuelto=msd.insertar1(p);
				RequestDispatcher rd = request.getRequestDispatcher("MVC/view/viewAdd.jsp");
				rd.forward(request, response);

			}
			
		}
	}

}
