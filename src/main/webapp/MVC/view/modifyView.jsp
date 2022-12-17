<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
    <jsp:useBean id="customerBean" scope ="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/update.css">
    <script src="https://kit.fontawesome.com/d846ae1254.js"></script>
    <title>Modificar mis datos</title>
</head>
<body>
<%
if(customerBean == null || !customerBean.getCorreo().equals("")){ %>
    <div class="all">
        <div class="options">
            <div class="data">
                <h2>Mis datos</h2>
                <form action="ModifyUserController" method="POST" id="form">
                    <input type="text" placeholder="Nombre" name="name" value="<jsp:getProperty name="customerBean" property="nombre"/>">
                    <input type="text" placeholder="Apellidos" name="surname" value="<jsp:getProperty name="customerBean" property="apellidos"/>">
                    <input type="text" placeholder="Correo electrónico" name="mail" value="<jsp:getProperty name="customerBean" property="correo"/>" readonly>
                    <p id="password-error">La contraseña debe tener de 4 a 16 digitos.</p>
                    <input type="password" placeholder="Password" name="password" value= "<jsp:getProperty name="customerBean" property="password"/>">
                    
                    <p id="incomplete-form-error" style="display:none">Debe completar todos los campos</p>
                    <input type="button" value="Modificar datos" onclick="submitform()">
                </form>
            </div>
        </div>
    </div>
    
    <script>
    
        const formulario = document.getElementById('form');
        const inputs = document.querySelectorAll('#form input');

        const expresiones = {
            nombre: /^[a-zA-ZÀ-ÿ\s]{2,30}$/, // Letras y espacios, pueden llevar acentos.
            password: /^.{4,16}$/ // 4 a 12 digitos.
        }

        var name = true;
        var pass = true;

        const validarFormulario = (e) => {
            switch (e.target.name) {
                case "name":
                
                    if(expresiones.nombre.test(e.target.value)){
                    	document.getElementById('name-error').style.display='none';
                        name = true;
                    } else {
                    	document.getElementById('name-error').style.display='block';
                        name = false;
                    }
                    
                break;

                case "password":
                    
                    if(expresiones.password.test(e.target.value)){
                    	document.getElementById('password-error').style.display='none';
                        pass = true;
                    } else {
                    	document.getElementById('password-error').style.display='block';
                        pass = false;
                    }
                break;
            } 
        }

        inputs.forEach((input) => {
            input.addEventListener('keyup', validarFormulario);
            input.addEventListener('blur', validarFormulario);
        });

        function submitform() {

            if(name && pass){
                formulario.submit();
            }
            else{
                document.getElementById('incomplete-form-error').style.display='block';
            }
        }
        
    </script>
    
</body>
</html>
<%}else{%>
	<p style="padding:10px">Usted no tiene autorización.</p>
<%}%>