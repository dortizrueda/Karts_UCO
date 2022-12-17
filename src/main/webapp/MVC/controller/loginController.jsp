<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.ValueObject.Tipo,es.uco.pw.business.DTO.UsuarioDTO,es.uco.pw.business.managers.UsuarioManager,es.uco.pw.data.DAO.UsuarioDAO,java.util.ArrayList,java.util.Properties,java.util.Date,es.uco.pw.display.javabean.CustomerBean" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
//String paramEncoding = application.getInitParameter("PARAMETER_ENCODING");
//request.setCharacterEncoding(paramEncoding);
String urlbd=application.getInitParameter("URL");
String userbd=application.getInitParameter("User");
String passwordbd=application.getInitParameter("Password");

Properties sqlProp= null;
UsuarioManager userManager= new UsuarioManager();
UsuarioDAO msu = new UsuarioDAO();
UsuarioManager p=UsuarioManager.getInstance();


if (customerBean == null || customerBean.getCorreo().equals("")) {
	String correo=request.getParameter("login-email");
	String password=request.getParameter("login-password");
	
	

    if (correo != null ) {
		UsuarioDAO msd = new UsuarioDAO();
		
		UsuarioDTO usuario=msd.iniciar_sesion(correo);
		String nombre=usuario.getNombre();
		String apellidos=usuario.getApellidos();
		Date fecha_inscr=usuario.getFecha_inscripcion();
		Date fecha_naci=usuario.getFecha_nacimiento();
		Tipo tipo=usuario.getTipo();
		if(usuario!=null && usuario.getPassword()!=null) {
			if(usuario.getPassword().equalsIgnoreCase(password)) {
				customerBean.setCorreo(correo);
				customerBean.setNombre(nombre);
				customerBean.setFecha_inscripcion(fecha_inscr);
				customerBean.setFecha_nacimiento(fecha_naci);
				customerBean.setPassword(password);
				customerBean.setTipo(tipo);
				customerBean.setApellidos(apellidos);
%>
            <jsp:setProperty property="correo" value="<%=customerBean.getCorreo()%>" name="customerBean"/>
            <jsp:setProperty property="tipo" value="<%=customerBean.getTipo()%>" name="customerBean"/>
<%			
			if(customerBean.getTipo().equals(Tipo.admin)){
				RequestDispatcher rd=request.getRequestDispatcher("../view/menuAdmin.jsp");
				rd.forward(request, response);
			}else{
				RequestDispatcher rd=request.getRequestDispatcher("../view/menuNoAdmin.jsp");
				rd.forward(request, response);
			}
			
        } else {
        	response.setContentType("text/html");
			out.println("Error en uno de los campos");
        	RequestDispatcher rd=request.getRequestDispatcher("../view/loginError.jsp");
			rd.forward(request, response);
        }

    } else {
    	response.setContentType("text/html");
		out.println("Usuario incorrecto");
    	RequestDispatcher rd=request.getRequestDispatcher("../view/loginError.jsp");
		rd.forward(request, response);
    }
}
}
%>
