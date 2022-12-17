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

import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.data.DAO.PistaDAO;

/**
 * Servlet implementation class ModifyPista
 */
@WebServlet("/ModifyPista")
public class ModifyPista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyPista() {
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
		HttpSession session=request.getSession();

		PrintWriter out=response.getWriter();

		String nombre=request.getParameter("nombre");


		String disponibilidad=request.getParameter("disponibilidad");

		
		PistaDAO msd=new PistaDAO();
		if(nombre!=null) {
			if(msd.check_pista(nombre)) {
				response.setContentType("text/html");
				out.println("Esta pista ya existe");
				RequestDispatcher rd=request.getRequestDispatcher("MVC/view/modifyError.jsp");
				rd.include(request, response);
			}else {
				PistaDTO p=new PistaDTO();
				p.setNombre(nombre);
				
				if(disponibilidad.compareTo("disponible")==0){
					p.setDisponibilidad(Disponibilidad.disponible);
				}else if(disponibilidad.compareTo("no_disponible")==0){
					p.setDisponibilidad(Disponibilidad.no_disponible);
				}				

				int devuelto=msd.modify_disponibilidad(p.getNombre(),p.isDisponibilidad());
				RequestDispatcher rd = request.getRequestDispatcher("MVC/view/viewAdd.jsp");
				rd.forward(request, response);

			}
		}
	}
}


