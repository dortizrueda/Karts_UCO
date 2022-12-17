package es.uco.pw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.DTO.UsuarioDTO;
import es.uco.pw.business.ValueObject.Tipo;
import es.uco.pw.business.ValueObject.Tipo_Reserva;
import es.uco.pw.data.DAO.UsuarioDAO;
import es.uco.pw.display.javabean.CustomerBean;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		PrintWriter out=response.getWriter();
		if(customerBean!= null || customerBean.getCorreo().equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/index.html");
			rd.forward(request, response);
		}else {

			response.setContentType("text/html");
			out.println("Ya se encuentra logueado");
			RequestDispatcher rd = request.getRequestDispatcher("/registerView.jsp");
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		PrintWriter out=response.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String nombre=request.getParameter("register-name");

		String apellido=request.getParameter("register-surname");

		String correo=request.getParameter("register-mail");

		String password=request.getParameter("register-password");

		String fech_naci=request.getParameter("register-fecha_nacimiento");
		String tipo = request.getParameter("register-type");


		UsuarioDAO msd=new UsuarioDAO();
		if(correo!=null) {

			if(!msd.check_mail(correo)) {
				response.setContentType("text/html");
				out.println("Este usuario ya existe");
				RequestDispatcher rd=request.getRequestDispatcher("/index.html");
				rd.include(request, response);
			}else {

				UsuarioDTO p=new UsuarioDTO();
				p.setNombre(nombre);
				p.setApellidos(apellido);
				p.setCorreo(correo);
				try {
					p.setFecha_nacimiento(sdf.parse(fech_naci));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				java.util.Date fecha=(new java.util.Date());
				p.setFecha_inscripcion(fecha);
				if(tipo.compareTo("admin")==0){
					p.setTipo(Tipo.admin);
				}else if(tipo.compareTo("no_admin")==0){
					p.setTipo(Tipo.no_admin);
				}
				p.setPassword(password);
				

				int devuelto=msd.insertar1(p);
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.include(request, response);


			}
		}
	}

}
