<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage ="error.jsp"%>
    <%@page import= "java.util.*" %>
    <%@page import= "es.uco.pw.business.DTO.UsuarioDTO,es.uco.pw.data.DAO.UsuarioDAO,es.uco.pw.business.DTO.ReservaDTO,es.uco.pw.data.DAO.ReservaDAO,es.uco.pw.data.DAO.PistaDAO,es.uco.pw.business.DTO.ReservaDTO,es.uco.pw.business.DTO.KartDTO,es.uco.pw.business.DTO.ReservaDTO,es.uco.pw.business.ValueObject.Tipo,es.uco.pw.business.managers.ReservaManager" %>
    <jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
    <%@ page import ="java.text.SimpleDateFormat" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/admin.css">
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    
    <script src="https://kit.fontawesome.com/d846ae1254.js" crossorigin="anonymous"></script>
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Menu Admin</title>
</head>
<body>
    <div class="logo">
        <img src="../../css/images/logo2r.png" alt="Circo Loco Karts Logo" style="float: left">
    </div>

<%if(customerBean.getTipo().equals(Tipo.admin)){	%>


	<div buttons>
	<h1>Bienvenido <jsp:getProperty property="correo" name="customerBean"/> !!</h1>
	<li>
		<ul>
			<a href="../../ModifyUserController"><li><i class="fas fa-user-edit"></i>Modificar Datos</li></a>
			<a href="../controller/logoutController.jsp"><i class="fas fa-sign-out-alt"></i>Cerrar Sesion</li></a>
			<div class="add-function">
		    <a href="#add-modal"><button>Insertar nueva pista</button></a>
			<a href="#addk-modal"><button>Insertar y asociar kart</button></a>
			<a href="#edit-modal"><button>Cambiar estado pista</button></a>
			<a href="#editk-modal"><button>Cambiar estado kart</button></a>
			<a href="#delete-modal"><button>Info de Usuarios</button></a>
			</div>
		
		</li>
		</ul>
	</div>
	
	<div id="add-modal" class="modal">
	<a href="#" class="close"></a>
        <div class="modal-content">
            <h2>Insertar Pista</h2>
            <a href="#" class="cross"><i class="fas fa-times"></i></a>
            
                <form action="../../AddP" method="POST">
                	<select name="dificultad">
								<option value="infantil">Infantil</option>
								<option value="familiar">Familiar</option>
								<option value="adultos">Adultos</option>
					</select>
                
                
                    <input type="text" id="nombre" placeholder="Nombre" name="nombre" required>

							<select name="disponibilidad">
								<option value="disponible">Disponible</option>
								<option value="no_disponible">No Disponible</option>
							</select>
                    <input type="text" id="capacity" placeholder="Aforo" name="capacity" required>
                    
                    <input type="submit" value="INSERTAR" class="submit">
                </form>
         </div>
         </div>
        
    <div id="addk-modal" class="modal">
	<a href="#" class="close"></a>
        <div class="modal-content">
            <h2>Insertar Kart</h2>
            <a href="#" class="cross"><i class="fas fa-times"></i></a>
            
                <form action="../../AddKart" method="POST">
                
                	<input type="text" id="id_kart" placeholder="ID Kart" name="id_kart" required>
                
							<select name="tipok">
								<option value="adultos">Adultos</option>
								<option value="children">Childrens</option>
								
							</select>
							<select name="estado_kart">
								<option value="disponible">Disponible</option>
								<option value="mantenimiento">No Disponible</option>
								<option value="reservado">Reservado</option>
								
							</select>
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>

                    
                    <input type="submit" value="INSERTAR" class="submit">
                </form>
            </div>


        </div>
    <div id="edit-modal" class="modal">
	<a href="#" class="close"></a>
        <div class="modal-content">
            <h2>Modificar Estado Pista</h2>
            <a href="#" class="cross"><i class="fas fa-times"></i></a>
            
                <form action="../../ModifyPista" method="POST">
                    <input type="text" id="nombre" placeholder="Nombre" name="nombre" required>

							<select name="disponibilidad">
								<option value="disponible">Disponible</option>
								<option value="no_disponible">No Disponible</option>
							</select>
                    
                    <input type="submit" value="MODIFICAR" class="submit">
                </form>
         </div>
         </div>
    <div id="editk-modal" class="modal">
	<a href="#" class="close"></a>
        <div class="modal-content">
            <h2>Modificar Estado Kart</h2>
            <a href="#" class="cross"><i class="fas fa-times"></i></a>
            
                <form action="../../ModifyKart" method="POST">
                
                	<input type="text" id="id_kart" placeholder="ID Kart" name="id_kart" required>
                
							<select name="estado_kart">
								<option value="disponible">Disponible</option>
								<option value="mantenimiento">No Disponible</option>
								<option value="reservado">Reservado</option>
								
							</select>
                    <input type="submit" value="MODIFICAR" class="submit">
                </form>
            </div>


        </div>
      
           <div id="delete-modal" class="modal">
			<a href="#" class="close"></a>
			<div class="modal-dialog" style="overflow-y: scroll;max-height:95%; margin-top:50px; margin-bottom:50px;">
        		<div class="modal-content">
            		<h2>Info Usuarios</h2>
            		<div class="modal-body">
             <%
                	
                    	UsuarioDAO msu = new UsuarioDAO();
         	ReservaDAO msd = new ReservaDAO();

        				ArrayList<UsuarioDTO> users = new ArrayList<UsuarioDTO>();
        				ArrayList<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
        				users = msu.getAll1();
        				reservas = msd.getAll1();
                    	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        				for(int i = 0; i<users.size(); i++){ 
        					UsuarioDTO usuario = new UsuarioDTO();
        					ReservaManager ms=new ReservaManager();
        					usuario = users.get(i);
        %>
            <a href="#" class="cross"><i class="fas fa-times"></i></a>
            
            	<div class="spectacles">
                        <h3>Id : <%= usuario.getCorreo() %></h3>
                        <p><span class="gray">Nombre: </span><%= usuario.getNombre() %></p>                                             
                        <p><span class="gray">Apellidos: </span><%= usuario.getApellidos() %></p>    
                        <p><span class="gray">Fecha registro: </span><%= sdf.format(usuario.getFecha_inscripcion()) %></p>                   
                        <p><span class="gray">Rol: </span><%= usuario.getTipo().toString() %></p>
                        <p><span class="gray">Antiguedad: </span><%= usuario.calcularAntiguedad(usuario.getFecha_inscripcion())/365 %><span class="gray"> years</span></p>                     
              			<p><span class="gray">Numero de reservas: </span><%= ms.numero_Reservas(usuario.getCorreo(),reservas)  %></p>
           
               			
                    </div>
                    <%}%>
                </div>
                </div>

			        </div>
			
        </div>
        <div class="spectacles">
            <h2>Reservas</h2>
            <div class="spectacles-content">
                
                <div class="spectacles-result">
                
                	<%
                		String pru;
                	
                    	ReservaDAO msd1 = new ReservaDAO();
        				ArrayList<ReservaDTO> reservas1 = new ArrayList<ReservaDTO>();
        				reservas1 = msd1.getAll1();
                    	SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        				for(int i = 0; i<reservas1.size(); i++){ 
        					ReservaDTO reserva = new ReservaDTO();
        					reserva = reservas1.get(i);
                    %>
                    <div class="spectacle">
                        <h3><%= reserva.getId_reserva() %></h3>
                        <p><span class="gray">Usuario: </span><%= reserva.getId_usuario() %></p>                                             
                        <p><span class="gray">Pista: </span><%= reserva.getId_pista() %></p>                       
                        <p><span class="gray">Minutos: </span><%= reserva.getMinutos() %></p>
                        <p><span class="gray">Descuento: </span><%=reserva.getDescuento()%></p>
                        <p><span class="gray">Tipo: </span><%=reserva.getReserva()%></p>
                        <p><span class="gray">Fecha Hora: </span><%=sdf1.format(reserva.getFecha_hora())%></p>                     
                        <p><span class="gray">Numero participantes (adultos): </span><%=reserva.getNum_adults()%></p>
                        <p><span class="gray">Numero participantes (infantiles): </span><%=reserva.getNum_children()%></p>
                        <p><span class="gray">Tipo de Reserva:  </span><%= reserva.getBono()%></p>               			
               			<ul class="spectacle-options">
               				<li onclick="document.getElementById('delete-<%=reserva.getId_reserva()%>').submit()" style="cursor:pointer"><i class="fas fa-trash-alt"></i></li>
               				<form name="form" id="delete-<%=reserva.getId_reserva()%>"action="../../DeleteReserva" style="display:none" method="POST">
               					<input type="hidden" name="id_reserva" value="<%=reserva.getId_reserva()%>">				
               				</form>
               			</ul>
               			
                    </div>
                    <%}%>
                
            </div>
        </div>
    </div>
		<script>
		const filtrar = function([...categorias], buscador) {
	        categorias.forEach( e => {
	                if(buscador.value === ""){
	                    e.style.display = "block";
	                }
	                else if(e.childNodes[3].getElementsByTagName("span")[1].innerText !== buscador.value){

	                    if(e.firstElementChild.innerText !== buscador.value){

	                        e.style.display = "inline";
	                        e.style.display = "none";
	                    }
	                }
	        }); 
	    }

	    const activar = function([...categorias]) {
	        categorias.forEach( e => {

	            e.style.display = "block";

	        }); 
	    }

	    function mostrar(e) {

	        let spectacle = document.getElementsByClassName("spectacle");
	        let buscador = document.getElementById("buscador");

	        if(e.keyCode === 13){
	            activar(spectacle);
	            filtrar(spectacle, buscador);
	        }

	    }
		</script>
</body>
</html>
<%}else{ %>
	<h1>No eres administrador</h1>
<%} %>