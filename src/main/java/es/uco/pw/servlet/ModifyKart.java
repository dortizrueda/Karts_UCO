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
import es.uco.pw.business.ValueObject.Estado_Kart;
import es.uco.pw.business.ValueObject.Tipo_Kart;
import es.uco.pw.data.DAO.PistaDAO;

/**
 * Servlet implementation class ModifyKart
 */
@WebServlet("/ModifyKart")
public class ModifyKart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyKart() {
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

		String id_kart=request.getParameter("id_kart");

		String estado_kart=request.getParameter("estado_kart");
		
		PistaDAO msd=new PistaDAO();
		if(id_kart!=null) {
			if(msd.check_kart(Integer.parseInt(id_kart))) {
				response.setContentType("text/html");
				out.println("Esta kart ya existe");
				RequestDispatcher rd=request.getRequestDispatcher("MVC/view/modifyError.jsp");
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
			
					int devuelto=msd.update_kart(p.getId_kart(), p.getEstado());
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/viewModify.jsp");
					rd.forward(request, response);			
			
		}
	}
	}

}
