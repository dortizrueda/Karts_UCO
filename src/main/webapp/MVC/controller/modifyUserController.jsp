<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="es.uco.pw.business.ValueObject.Tipo,es.uco.pw.business.DTO.UsuarioDTO,es.uco.pw.business.managers.UsuarioManager,es.uco.pw.data.DAO.UsuarioDAO,java.util.ArrayList,java.util.Properties,java.text.SimpleDateFormat" %>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%
String nextPage = "../../index.jsp";
String mensajeNextPage = "";
SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

UsuarioDAO msu = new UsuarioDAO();
String urlbd=application.getInitParameter("URL");
String userbd=application.getInitParameter("User");
String passwordbd=application.getInitParameter("Password");
Properties sqlProp= null;

UsuarioManager userManager = new UsuarioManager();
if (customerBean != null && !customerBean.getCorreo().equals("")) {
    String name = request.getParameter("nombre");
    String surname = request.getParameter("apellidos");
    String password = request.getParameter("password");
	String Fecha_nacimiento = request.getParameter("Fecha_nacimiento");


    if (name != null && surname != null && password != null && Fecha_nacimiento != null) {
    	if(UsuarioManager.getSqlProperties()==null)
		{
	String file=application.getInitParameter("Sql");
	java.io.InputStream str=application.getResourceAsStream(file);
	Properties prop=new Properties();
	prop.load(str);
	UsuarioManager.setSqlProperties(prop);
		}
    	
    	ArrayList<UsuarioDTO>users=msu.getAll(sqlProp,urlbd,userbd,passwordbd);

        UsuarioDTO user = UsuarioManager.buscarUser(customerBean.getCorreo(),users);
        user.setNombre(name);
        user.setApellidos(surname);
        user.setPassword(password);
        user.setFecha_nacimiento(sdf.parse(Fecha_nacimiento));

        UsuarioManager.modificarUsuario(user,sqlProp,urlbd,userbd,passwordbd);
        nextPage = "../../index.jsp";
        mensajeNextPage = "Cambios guardados correctamente";
    } else {
        nextPage = "../view/modifyUserView.jsp";
    }
}
%>
<jsp:forward page="<%=nextPage%>">
    <jsp:param value="<%=mensajeNextPage%>" name="message"/>
</jsp:forward>