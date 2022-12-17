package es.uco.pw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.DTO.ReservaDTO;
import es.uco.pw.business.DTO.UsuarioDTO;
import es.uco.pw.business.ValueObject.Tipo_Bono;
import es.uco.pw.business.ValueObject.Tipo_Reserva;
import es.uco.pw.business.managers.ReservaManager;
import es.uco.pw.data.DAO.PistaDAO;
import es.uco.pw.data.DAO.ReservaDAO;
import es.uco.pw.data.DAO.UsuarioDAO;

/**
 * Servlet implementation class IndividualReserva
 */
@WebServlet("/IndividualReserva")
public class IndividualReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndividualReserva() {
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
		ReservaManager manager=new ReservaManager();
		ReservaDAO msd=new ReservaDAO();
		UsuarioDAO msd1=new UsuarioDAO();
		ReservaDTO p=new ReservaDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


		PistaDAO msu=new PistaDAO();
		ArrayList<PistaDTO>pistas=new ArrayList<PistaDTO>();
		ArrayList<UsuarioDTO>users=new ArrayList<UsuarioDTO>();

		pistas=msu.getAll1();
		users=msd1.getAll1();
		PrintWriter out=response.getWriter();
		int type=Integer.parseInt(request.getParameter("type"));
		float descuento=1;
		
		if(type==1) {
			String id_user=request.getParameter("id_user");
			p.setId_usuario(id_user);
			if(ReservaManager.antiguedad_user(id_user, users)>730) {
				descuento=(float) 0.90;
				p.setDescuento(10);				
			}else {
				p.setDescuento(0);
			}
			String fecha=request.getParameter("date");
			String hora=request.getParameter("time");
			fecha=fecha+" "+hora;
			try {
				p.setFecha_hora(sdf.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tipo_Reserva tipo=Tipo_Reserva.familiar;
			p.setReserva(tipo);
			String pista=request.getParameter("pista");
			p.setId_pista(pista);
			int tiempo=Integer.parseInt(request.getParameter("minutos"));
			p.setMinutos(tiempo);
			if(ReservaManager.check_pista(tipo, pista, pistas)==true) {
				if(tiempo==60) {
					float precio=20*descuento;
					p.setPrecio_reserva(precio);
				}else if(tiempo==90) {					
					float precio=30*descuento;
					p.setPrecio_reserva(precio);
				}else if(tiempo==120){					
					float precio=40*descuento;
					p.setPrecio_reserva(precio);
				}
				int num_adultos=Integer.parseInt(request.getParameter("adultos"));
				p.setNum_adults(num_adultos);
				int num_childrens=Integer.parseInt(request.getParameter("children"));
				p.setNum_children(num_childrens);
				int suma=num_adultos+num_childrens;
				p.setBono(Tipo_Bono.individual);
				if(ReservaManager.check_aforo(suma, pista, pistas)==false) {
					response.setContentType("text/html");
					out.println("El aforo ha sido superado...");
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
					rd.include(request, response);
				}else {
					int devuelto =msd.insertar1(p);
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaSuccess.jsp");
					rd.forward(request, response);
				}
			}else {
				response.setContentType("text/html");
				out.println("La pista no pertenece a la misma categoria, no existe o no se encuentra disponible");
				RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
				rd.include(request, response);
			}
			
		}else if(type==2) {
			String id_user=request.getParameter("id_user");
						
			p.setId_usuario(id_user);
			if(ReservaManager.antiguedad_user(id_user, users)>730) {
				descuento=(float) 0.90;
				p.setDescuento(10);				
			}else {
				p.setDescuento(0);
			}
			String fecha=request.getParameter("date");
			String hora=request.getParameter("time");
			fecha=fecha+" "+hora;
			try {
				p.setFecha_hora(sdf.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tipo_Reserva tipo=Tipo_Reserva.adultos;
			p.setReserva(tipo);
			String pista=request.getParameter("pista");
			p.setId_pista(pista);
			int tiempo=Integer.parseInt(request.getParameter("minutos"));
			p.setMinutos(tiempo);
			if(ReservaManager.check_pista(tipo, pista, pistas)==true) {
				if(tiempo==60) {
					float precio=20*descuento;
					p.setPrecio_reserva(precio);
				}else if(tiempo==90) {					
					float precio=30*descuento;
					p.setPrecio_reserva(precio);
				}else if(tiempo==120){					
					float precio=40*descuento;
					p.setPrecio_reserva(precio);
				}
				int num_adultos=Integer.parseInt(request.getParameter("adultos"));
				p.setNum_adults(num_adultos);
				int num_childrens=Integer.parseInt(request.getParameter("children"));
				p.setNum_children(0);
				int suma=num_adultos+num_childrens;
				p.setBono(Tipo_Bono.individual);
				if(ReservaManager.check_aforo(suma, pista, pistas)==false) {
					response.setContentType("text/html");
					out.println("El aforo ha sido superado...");
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
					rd.include(request, response);
				}else {
					int devuelto = msd.insertar1(p);
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaSuccess.jsp");
					rd.forward(request, response);
				}
			}else {
				response.setContentType("text/html");
				out.println("La pista no pertenece a la misma categoria, no existe o no se encuentra disponible");
				RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
				rd.include(request, response);
			}
			
		}else if(type==3){
String id_user=request.getParameter("id_user");
p.setId_usuario(id_user);

			if(ReservaManager.antiguedad_user(id_user, users)>730) {
				descuento=(float) 0.90;
				p.setDescuento(10);				
			}else {
				p.setDescuento(0);
			}
			String fecha=request.getParameter("date");
			String hora=request.getParameter("time");
			fecha=fecha+" "+hora;
			try {
				p.setFecha_hora(sdf.parse(fecha));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Tipo_Reserva tipo=Tipo_Reserva.infantil;
			p.setReserva(tipo);
			String pista=request.getParameter("pista");
			p.setId_pista(pista);
			int tiempo=Integer.parseInt(request.getParameter("minutos"));
			p.setMinutos(tiempo);
			if(ReservaManager.check_pista(tipo, pista, pistas)==true) {
				if(tiempo==60) {
					float precio=20*descuento;
					p.setPrecio_reserva(precio);
				}else if(tiempo==90) {					
					float precio=30*descuento;
					p.setPrecio_reserva(precio);
				}else if(tiempo==120){					
					float precio=40*descuento;
					p.setPrecio_reserva(precio);
				}
				int num_adultos=Integer.parseInt(request.getParameter("adultos"));
				p.setNum_adults(0);
				int num_childrens=Integer.parseInt(request.getParameter("children"));
				p.setNum_children(num_childrens);
				int suma=num_adultos+num_childrens;
				p.setBono(Tipo_Bono.individual);
				if(ReservaManager.check_aforo(suma, pista, pistas)==false) {
					response.setContentType("text/html");
					out.println("El aforo ha sido superado...");
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
					rd.include(request, response);
				}else {
					int devuelto =msd.insertar1(p);
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaSuccess.jsp");
					rd.forward(request, response);
				}
			}else {
				response.setContentType("text/html");
				out.println("La pista no pertenece a la misma categoria, no existe o no se encuentra disponible");
				RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
				rd.include(request, response);
			}
			
		}
	}

}
