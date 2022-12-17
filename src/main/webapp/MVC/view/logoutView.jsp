<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout</title>
<style type="text/css">
body{
	text-align: center;
}

h2{
	margin: 50px;
}

a{
	text-decoration: none;
	color: black;
	padding: 8px;
	padding-left: 15px;
	padding-right: 15px;
	margin-top: 20px;
	border: 1px solid black;
	font-weight: bold;
	font-size: 20px;
}

a:hover{
	color: white;
	background-color: black;
}
</style>
</head>
<body>
<%
String nextPage = "../controller/loginController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) messageNextPage = "";
if(messageNextPage.equals("loggedOut")){%>
	<h2>Procediendo a la desconexion...</h2>
<%
	nextPage="../../index.html";
%>

<% }else if (customerBean == null && customerBean.getCorreo().equals("")) {%>
	El usuario se encontraba desconectado..<br/>
<%
} %>
<a href="../../index.html">Volver</a>
</body>
</html>