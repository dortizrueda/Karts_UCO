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

import es.uco.pw.business.DTO.KartDTO;
import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.business.ValueObject.Estado_Kart;
import es.uco.pw.business.ValueObject.Tipo_Kart;
import es.uco.pw.data.DAO.PistaDAO;

/**
 * Servlet implementation class AddKart
 */
@WebServlet("/AddKart")
public class AddKart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddKart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("../../menuAdmin.jsp");
		rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();

		PrintWriter out=response.getWriter();

		String id_kart=request.getParameter("id_kart");

		String tipo=request.getParameter("tipok");

		String estado_kart=request.getParameter("estado_kart");

		String pista=request.getParameter("pista");
		
		PistaDAO msd=new PistaDAO();
		if(id_kart!=null) {
			if(!msd.check_kart(Integer.parseInt(id_kart))) {
				response.setContentType("text/html");
				out.println("Esta kart ya existe");
				RequestDispatcher rd=request.getRequestDispatcher("MVC/view/insertError.jsp");
				rd.include(request, response);
			}else {
				KartDTO p=new KartDTO();
				p.setId_kart(Integer.parseInt(id_kart));
				if(estado_kart.compareTo("disponible")==0){
					p.setEstado(Estado_Kart.disponible);
				}else if(estado_kart.compareTo("mantenimiento")==0){
					p.setEstado(Estado_Kart.mantenimiento);
				}else if(estado_kart.compareTo("reservado")==0) {
					p.setEstado(Estado_Kart.reservado);
				}
				if(tipo.compareTo("adultos")==0){
					p.setTipo(Tipo_Kart.adultos);
				}else if(tipo.compareTo("children")==0){
					p.setTipo(Tipo_Kart.children);
				}	
				p.setPista(pista);
				if(msd.check_asociarkart(pista,tipo)) {
					int devuelto=msd.insertar2(p);
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/viewAdd.jsp");
					rd.forward(request, response);
				}else {
					response.setContentType("text/html");
					out.println("ERROR!! Esta pista no pertenece a la misma categoria, o no existe");
					RequestDispatcher rd=request.getRequestDispatcher("MVC/view/insertError.jsp");
					rd.include(request, response);
				

			}
			
		}
	}
	}

}
