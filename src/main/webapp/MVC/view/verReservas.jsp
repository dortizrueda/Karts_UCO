<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="java.util.ArrayList, es.uco.pw.business.DTO.ReservaDTO, es.uco.pw.business.ValueObject.Tipo" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
<style type="text/css">
body{
	text-align: center;
	background-image: url('../../css/images/background_5.png');
    background-repeat: no-repeat;
    background-attachment: fixed;  
    background-size: cover;
}


h2{
	margin: 50px;
	font-size: 30px;
	color: black;
}

a{
    width: 100%;
    margin: auto;
    text-align: center;
    background-color: #7209B7;
    padding: 10px;
    font-size: 20px;
    width: 50px;
    height: 30px;
    font-weight: bold;
    text-decoration: none;
    color: white;
}

a:hover{
    background-color: #560BAD;
    cursor: pointer;
}
</style>
</head>
<body>
<%if(customerBean.getTipo().equals(Tipo.no_admin)){
	ArrayList<ReservaDTO>reservas=(ArrayList<ReservaDTO>)request.getAttribute("reservas");
	if(reservas!=null)
	{%>
		<h2>Lista de Reservas</h2>
		<%for(int i=0;i<reservas.size();i++)
		{
			out.println("Id_reserva: "+reservas.get(i).getId_reserva());%><br>
			<%out.println("Id_pista: "+reservas.get(i).getId_pista());%><br>
			<%out.println("Id_usuario: "+reservas.get(i).getId_usuario()); %><br>
			<%out.println("Fecha_hora: "+reservas.get(i).getFecha_hora()); %><br><br>
			
		<%}
	}%>
<a href="MVC/view/menuNoAdmin.jsp">Volver</a>
<%}else
{%>
<a href="MVC/view/menuNoAdmin.jsp">Volver</a>

<%} %>
</body>