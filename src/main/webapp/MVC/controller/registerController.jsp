<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="es.uco.pw.business.ValueObject.Tipo,es.uco.pw.business.DTO.UsuarioDTO,es.uco.pw.business.managers.UsuarioManager,es.uco.pw.data.DAO.UsuarioDAO,java.util.ArrayList,java.util.Properties,java.text.SimpleDateFormat" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
String urlbd=application.getInitParameter("URL");
String userbd=application.getInitParameter("User");
String passwordbd=application.getInitParameter("Password");

Properties sqlProp= null;

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

UsuarioManager userManager = UsuarioManager.getInstance();
if (customerBean == null || customerBean.getCorreo().equals("")) {

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
		RequestDispatcher rd=request.getRequestDispatcher("../../index.html");
		rd.include(request, response);
	}else {

		UsuarioDTO p=new UsuarioDTO();
		p.setNombre(nombre);
		p.setApellidos(apellido);
		p.setCorreo(correo);
			p.setFecha_nacimiento(sdf.parse(fech_naci));
		
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
		RequestDispatcher rd = request.getRequestDispatcher("/MVC/view/registerView.jsp");
		rd.include(request, response);

	}
	}else{
		RequestDispatcher rd = request.getRequestDispatcher("../../index.html");
		rd.include(request, response);
	}
}else{
	RequestDispatcher rd = request.getRequestDispatcher("../../index.html");
	rd.include(request, response);
}


%>
