<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="../error.jsp"%>
    <jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<title>Registro correcto</title>
</head>
<body>
    <%session.invalidate();%>
    <h2>Registro realizado correctamente</h2>
    <a href="../../index.html">Inicia sesion o Registrate</a>
</body>
</html>