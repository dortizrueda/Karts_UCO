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

	<h2>Se ha modificado los valores con exito</h2>
	
		<a href="MVC/view/menuNoAdmin.jsp">Volver</a>
	


</body>
</html>