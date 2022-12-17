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
<title>Registro correcto</title>
</head>
<body>
    <%session.invalidate();%>
    <h2>Registro realizado correctamente</h2>
    <a href="../../index.html">Inicia sesion o Registrate</a>
</body>
</html>