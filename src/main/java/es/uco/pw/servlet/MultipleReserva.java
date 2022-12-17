package es.uco.pw.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class MultipleReserva
 */
@WebServlet("/MultipleReserva")
public class MultipleReserva extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultipleReserva() {
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
		ReservaDTO [] reservas = new ReservaDTO[5];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date [] fechas=new Date[5];

		PistaDAO msu=new PistaDAO();
		ArrayList<PistaDTO>pistas=new ArrayList<PistaDTO>();
		ArrayList<UsuarioDTO>users=new ArrayList<UsuarioDTO>();

		pistas=msu.getAll1();
		users=msd1.getAll1();
		PrintWriter out=response.getWriter();
		
		String type[]=request.getParameterValues("type");
		String id_user[]=request.getParameterValues("id_user");
		String pista[]=request.getParameterValues("pista");
		String num_adultos1[]=request.getParameterValues("adultos");
		int[]num_adultos=new int[num_adultos1.length];
		for(int i=0;i<num_adultos.length;i++) {
			num_adultos[i]=Integer.parseInt(num_adultos1[i]);
		}
		String num_children1[]=request.getParameterValues("children");
		int[]num_children=new int[num_children1.length];
		for(int i=0;i<num_adultos.length;i++) {
			num_children[i]=Integer.parseInt(num_children1[i]);
		}
		String minutos1[]=request.getParameterValues("minutos");
		int[]minutos=new int[minutos1.length];
		float precio=0;
		for(int i=0;i<minutos1.length;i++) {
			minutos[i]=Integer.parseInt(minutos1[i]);
		}
		String date[]=request.getParameterValues("date");
		String time[]=request.getParameterValues("time");
		
		for(int i=0;i<date.length;i++) {
			date[i]=date[i]+" "+time[i];
		}
		for(int i=0;i<date.length;i++) {
			try {
				fechas[i]=sdf.parse(date[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(type[0].equals("familiar")) {
			for(int i=0;i<5;i++) {
				reservas[i] = new ReservaDTO();
				reservas[i].setReserva(Tipo_Reserva.familiar);
				reservas[i].setId_usuario(id_user[i]);
				reservas[i].setFecha_hora(fechas[i]);
				reservas[i].setId_pista(pista[i]);
				reservas[i].setDescuento(5);
				if(ReservaManager.check_pista(reservas[0].getReserva(), reservas[i].getId_pista(), pistas)) {
					if(minutos[i]==60) {
						reservas[i].setMinutos(minutos[i]);
						 precio=19;
						 reservas[i].setPrecio_reserva(precio);
						 
						}else if(minutos[i]==90) {
						reservas[i].setMinutos(minutos[i]);
						 precio=(float) 28.5;
						 reservas[i].setPrecio_reserva(precio);
					}else if(minutos[i]==120) {
						reservas[i].setMinutos(minutos[i]);
						 precio=38;
						 reservas[i].setPrecio_reserva(precio);
					}
					reservas[i].setNum_adults(num_adultos[i]);
					reservas[i].setNum_children(num_children[i]);
					reservas[i].setBono(Tipo_Bono.bono);
					int suma=num_adultos[i]+num_children[i];
					if(ReservaManager.check_aforo(suma,reservas[i].getId_pista(), pistas)==false) {
						response.setContentType("text/html");
						out.println("El aforo ha sido superado...");
						RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
						rd.include(request, response);
					}else{
						System.out.println("inserté");
						msd.insertar1(reservas[i]);
					}
				}else {
					response.setContentType("text/html");
					out.println("La pista no pertenece a la misma categoria, no existe o no se encuentra disponible");
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
					rd.include(request, response);
				}
				
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaSuccess.jsp");
			rd.forward(request, response);
		}else if(type[0].equals("adultos")) {
			for(int i=0;i<5;i++) {
				reservas[i] = new ReservaDTO();
				reservas[i].setReserva(Tipo_Reserva.adultos);
				reservas[i].setId_usuario(id_user[i]);
				reservas[i].setFecha_hora(fechas[i]);
				reservas[i].setId_pista(pista[i]);
				reservas[i].setDescuento(5);
				if(ReservaManager.check_pista(reservas[0].getReserva(), reservas[i].getId_pista(), pistas)) {
					if(minutos[i]==60) {
						reservas[i].setMinutos(minutos[i]);
						 precio=19;
						 reservas[i].setPrecio_reserva(precio);
						 
						}else if(minutos[i]==90) {
						reservas[i].setMinutos(minutos[i]);
						 precio=(float) 28.5;
						 reservas[i].setPrecio_reserva(precio);
					}else if(minutos[i]==120) {
						reservas[i].setMinutos(minutos[i]);
						 precio=38;
						 reservas[i].setPrecio_reserva(precio);
					}
					reservas[i].setNum_adults(num_adultos[i]);
					reservas[i].setNum_children(0);
					reservas[i].setBono(Tipo_Bono.bono);
					int suma=reservas[i].getNum_adults()+reservas[i].getNum_children();
					if(ReservaManager.check_aforo(suma,reservas[i].getId_pista(), pistas)==false) {
						response.setContentType("text/html");
						out.println("El aforo ha sido superado...");
						RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
						rd.include(request, response);
					}else{
						msd.insertar1(reservas[i]);
					}
				}else {
					response.setContentType("text/html");
					out.println("La pista no pertenece a la misma categoria, no existe o no se encuentra disponible");
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
					rd.include(request, response);
				}
				
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaSuccess.jsp");
			rd.forward(request, response);
			
		}else if(type[0].equals("infantil")) {
			for(int i=0;i<5;i++) {
				reservas[i] = new ReservaDTO();
				reservas[i].setReserva(Tipo_Reserva.infantil);
				reservas[i].setId_usuario(id_user[i]);
				reservas[i].setFecha_hora(fechas[i]);
				reservas[i].setId_pista(pista[i]);
				reservas[i].setDescuento(5);
				
				
				if(ReservaManager.check_pista(reservas[0].getReserva(), reservas[i].getId_pista(), pistas)) {
					if(minutos[i]==60) {
						reservas[i].setMinutos(minutos[i]);
						 precio=19;
						 reservas[i].setPrecio_reserva(precio);
						 
						}else if(minutos[i]==90) {
						reservas[i].setMinutos(minutos[i]);
						 precio=(float) 28.5;
						 reservas[i].setPrecio_reserva(precio);
					}else if(minutos[i]==120) {
						reservas[i].setMinutos(minutos[i]);
						 precio=38;
						 reservas[i].setPrecio_reserva(precio);
					}
					reservas[i].setNum_adults(0);
					reservas[i].setNum_children(num_children[i]);
					reservas[i].setBono(Tipo_Bono.bono);
					int suma=reservas[i].getNum_adults()+reservas[i].getNum_children();
					if(ReservaManager.check_aforo(suma,reservas[i].getId_pista(), pistas)==false) {
						response.setContentType("text/html");
						out.println("El aforo ha sido superado...");
						RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
						rd.include(request, response);
					}else{
						msd.insertar1(reservas[i]);
					}
				}else {
					response.setContentType("text/html");
					out.println("La pista no pertenece a la misma categoria, no existe o no se encuentra disponible");
					RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaError.jsp");
					rd.include(request, response);
				}
				
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("MVC/view/reservaSuccess.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
