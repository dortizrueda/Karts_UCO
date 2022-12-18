<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<%@ page import="java.util.ArrayList, es.uco.pw.business.DTO.PistaDTO, es.uco.pw.business.ValueObject.Tipo" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
<style type="text/css">
body{
	text-align: center;
	background-image: url('css/images/background_5.png');
    background-repeat: no-repeat;
    background-attachment: fixed;  
    background-size: cover;
      font-family: Cursive;
    
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

.texto{
	font-size: 15px;
	color: black;
	margin-bottom: 15px;
}
</style>
</head>
<body>
<%if(customerBean.getTipo().equals(Tipo.no_admin)){
	ArrayList<PistaDTO>pista=(ArrayList<PistaDTO>)request.getAttribute("pista");
	if(pista!=null)
	{%>
		<h2>Lista de Pistas</h2>
		
		<%for(int i=0;i<pista.size();i++)
		{
			out.println("Nombre: "+pista.get(i).getNombre());%><br>
			<%out.println("Aforo: "+pista.get(i).getNumero_karts());%><br>
			<%out.println("Tipo: "+pista.get(i).getDificultad()); %><br>
			<%out.println("Disponibilidad: "+pista.get(i).isDisponibilidad()); %><br><br>
			
		<%}
	}%>
<a href="MVC/view/menuNoAdmin.jsp">Volver</a>
<%}else
{%>
<a href="MVC/view/menuNoAdmin.jsp">Volver</a>

<%} %>
</body>