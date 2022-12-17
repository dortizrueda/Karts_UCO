<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>

</head>
<body>
<%
String nextPage = "../controller/loginController.jsp";
String messageNextPage = request.getParameter("message");
if (messageNextPage == null) messageNextPage = "";

if (customerBean != null && !customerBean.getCorreo().equals("")) {
    nextPage = "../../index.jsp";
} else {
%>
<%= messageNextPage %><br/><br/>

<h1>Login</h1>
<form method="post" action="../controller/loginController.jsp">
    <label for="correo">Correo: </label>
    <input type="text" placeholder="Correo" name="correo" value=""><br/>
    <label for="password">Password: </label>
    <input type="text" placeholder="Password" name="password" value="">
    <br/>
    <input type="submit" value="Enviar" name="submit">
</form>
<%
}
%>

</body>
</html>