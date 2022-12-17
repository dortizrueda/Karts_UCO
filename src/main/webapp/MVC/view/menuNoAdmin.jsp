<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage ="error.jsp"%>
    <%@page import= "java.util.*" %>
    <%@page import= "java.text.SimpleDateFormat,es.uco.pw.business.DTO.UsuarioDTO,es.uco.pw.business.DTO.ReservaDTO,es.uco.pw.data.common.Conexion,es.uco.pw.business.managers.UsuarioManager,es.uco.pw.business.managers.ReservaManager,es.uco.pw.data.DAO.UsuarioDAO,es.uco.pw.data.DAO.ReservaDAO,java.util.ArrayList,java.util.Properties,es.uco.pw.business.ValueObject.Tipo" %>
    <jsp:useBean  id="customerBean" scope="session" class="es.uco.pw.display.javabean.CustomerBean"></jsp:useBean>
    <%@ page import ="java.text.SimpleDateFormat" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/admin.css">
    <script src="https://kit.fontawesome.com/d846ae1254.js" crossorigin="anonymous"></script>
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <title>Menú No Admin</title>
</head>
	
<body>
<%if(customerBean.getTipo().equals(Tipo.no_admin)){	%>
	
	<div >
	<li> <h1>Bienvenido <jsp:getProperty property="correo" name="customerBean"/> !!</h1> </li>
		<%
        UsuarioDAO msd=new UsuarioDAO();
        ReservaDAO msd1=new ReservaDAO();
        ArrayList<UsuarioDTO> users = msd.getAll1();
		ArrayList<ReservaDTO> reservas = msd1.getAll1();
		ArrayList<ReservaDTO> reservas1 = new ArrayList<ReservaDTO>();
		reservas1=ReservaManager.nextReserva(reservas);
		ReservaDTO n=new ReservaDTO();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm");
		n=msd1.getFecha_Recent1(customerBean.getCorreo());
		if(n.getFecha_hora()==null){
			n.setFecha_hora(sdf.parse("0001-01-01 00:00:00"));
		}
		UsuarioDTO user = UsuarioManager.buscarUser(customerBean.getCorreo(),users);
        					
                    %>
		<p>	Fecha y Hora: <%= sdf.format(new java.util.Date())%> </p>
		<p> Fecha de Registro: <%=sdf.format(user.getFecha_inscripcion()) %></p>
		<p> Fecha proxima reserva: <%= sdf.format(n.getFecha_hora()) %></p>
	<li>
		<ul >
			<a href="../../ModifyUserController"><li><i class="fas fa-user-edit"></i>Modificar Datos</li></a>
			<a href="../controller/logoutController.jsp"><i class="fas fa-sign-out-alt"></i>Cerrar Sesion</li></a>
			<div class="add-function">
			<a href="#add-modal"><button class="button">Reserva Individual</button></a>
			<a href="#addk-modal"><button class="button">Reserva Multiple</button></a>
			<a href=""><button></button></a>
			<a href=""><button></button></a>
			<a href=""><button></button></a>
			</div>
			
		</ul>
	</li>
	</div>
	
          <div id="add-modal" class="modal">
        <a href="#" class="close"></a>
        <div class="modal-content">
            <h2>Reserva Individual</h2>
            <a href="#" class="cross"><i class="fas fa-times"></i></a>
            <ul class="type">
                <li><input type=radio name="type" id="familiar" class="radio" value="familiar" onclick="toggle(this)"></input><label for="familiar">Familiar</label></li>
                <li><input type="radio" name="type" id="adultos" class="radio" value="adultos" onclick="toggle(this)"><label for="adultos">Adultos</label></li>
                <li><input type="radio" name="type" id="infantil" class="radio" value="infantil" onclick="toggle(this)"><label for="infantil">Infantil</label></li>
            </ul>
            
            <div id="familiar-form">
                <form action="../../IndividualReserva" method="POST">
                
                	<input type="hidden" name=type value="1">
                	
                    <input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                	                
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>

                    <select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>


                    <ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="number" id="children" placeholder="nº Niños" name="children" required></li>
                        <li><input type="number" id="adultos" placeholder="nº Adultos" name="adultos" required></li>
                    </ul>
                    <input type="submit" value="AÑADIR" class="submit">
                </form>
            </div>

            <div id="adultos-form">
                <form action="../../IndividualReserva" method="POST">
                
                	<input type="hidden" name=type value="2">
                	
                    <input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                	                
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>

                    <select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>


                    <ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="hidden" id="children" name="children" value="0" required></li>
                        <li><input type="number" id="adultos" placeholder="nº Adultos" name="adultos" required></li>
                    </ul>
                    
                    <input type="submit" value="AÑADIR" class="submit">
                </form>
            </div>

            <div id="infantil-form">
                <form action="../../IndividualReserva" method="POST">
                	
                	<input type="hidden" name=type value="3">
                	
                    <input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                	                
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>

                    <select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>


                    <ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="number" id="children" placeholder="nº Niños" name="children" required></li>
                        <li><input type="hidden" id="adultos" name="adultos" value="0" required></li>
                    </ul>
                    <input type="submit" value="AÑADIR" class="submit">
                </form>
            </div>

        </div>
    </div>
    
    
    <div id="addk-modal" class="modal">
        <a href="#" class="close"></a>
		<div class="modal-dialog" style="overflow-y: scroll;max-height:95%; margin-top:50px; margin-bottom:50px;">
        <div class="modal-content">
            <h2>Reserva Multiple</h2>
               		<div class="modal-body">
            
            <a href="#" class="cross"><i class="fas fa-times"></i></a>             

                <form action="../../MultipleReserva" method="POST">
              	
              	   <input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                   <input type="text" id="pista" placeholder="Pista" name="pista" required>
                	
                	
                	<select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>
					<select name="type">
								<option value="familiar">Familiar</option>
								<option value="adultos">Adultos</option>
								<option value="infantil">Infantil</option>
					</select>
					<ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="number" id="children" placeholder="nº Niños" name="children" required></li>
                        <li><input type="number" id="adultos" placeholder="nº Adultos" name="adultos" required></li>
                    </ul>
                     <div class="separator">
                    </div>
					<input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>
					
					<select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>
					<select name="type">
								<option value="familiar">Familiar</option>
								<option value="adultos">Adultos</option>
								<option value="infantil">Infantil</option>
					</select>
					<ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="number" id="children" placeholder="nº Niños" name="children" required></li>
                        <li><input type="number" id="adultos" placeholder="nº Adultos" name="adultos" required></li>
                    </ul>
                     <div class="separator">
                     </div>
                    <input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>
					<select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>
					<select name="type">
								<option value="familiar">Familiar</option>
								<option value="adultos">Adultos</option>
								<option value="infantil">Infantil</option>
					</select>
					<ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="number" id="children" placeholder="nº Niños" name="children" required></li>
                        <li><input type="number" id="adultos" placeholder="nº Adultos" name="adultos" required></li>
                    </ul>
                    <div class="separator">
                     </div>
                    <input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>
					<select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>
					<select name="type">
								<option value="familiar">Familiar</option>
								<option value="adultos">Adultos</option>
								<option value="infantil">Infantil</option>
					</select>
					<ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="number" id="children" placeholder="nº Niños" name="children" required></li>
                        <li><input type="number" id="adultos" placeholder="nº Adultos" name="adultos" required></li>
                    </ul>
                    <div class="separator">
                     </div>
                    <input type="hidden" id="id_user" name="id_user"  value="<jsp:getProperty property="correo" name="customerBean"/>" required>
                    <input type="text" id="pista" placeholder="Pista" name="pista" required>
					<select name="minutos">
								<option value="60">60 min</option>
								<option value="90">90 min</option>
								<option value="120">120 min</option>
					</select>
					<select name="type">
								<option value="familiar">Familiar</option>
								<option value="adultos">Adultos</option>
								<option value="infantil">Infantil</option>
					</select>
					<ul>
                        <li><input type="text" id="date" placeholder="Fecha" onfocus="(this.type='date')" name="date" required></li>
                        <li><input type="text" id="time" placeholder="Hora" onfocus="(this.type='time')" name="time" required></li>
                        <li><input type="number" id="children" placeholder="nº Niños" name="children" required></li>
                        <li><input type="number" id="adultos" placeholder="nº Adultos" name="adultos" required></li>
                    </ul>
					
                    <input type="submit" value="AÑADIR" class="submit">
                </form>
                </div>
                </div>
        </div>
    </div>
    
    
    
    
    <div class="spectacles">
            <h2>Mis Reservas</h2>
            <div class="spectacles-content">
                
                <div class="spectacles-result">
                
                	<%
                	
                    	ReservaDAO msd2 = new ReservaDAO();
        				ArrayList<ReservaDTO> reservas11 = new ArrayList<ReservaDTO>();
        				reservas11 = msd2.getAll1();
                    	SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yyyy HH:mm");
        				for(int i = 0; i<reservas11.size(); i++){
        					if(reservas11.get(i).getId_usuario().equals(customerBean.getCorreo())){
        						ReservaDTO reserva = new ReservaDTO();
            					reserva = reservas11.get(i);
        					
        					
                    %>
                    <div class="spectacle">
                        <h3>Id : <%= reserva.getId_reserva() %></h3>
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
               				<form name="form" id="delete-<%=reserva.getId_reserva()%>"action="../../DeleteReserva2" style="display:none" method="POST">
               					<input type="hidden" name="id_reserva" value="<%=reserva.getId_reserva()%>">				
               				</form>
               			</ul>
               			
                    </div>
                    <%}%>                    	
                    <%}%>
                
            </div>
        </div>
    </div>
<script type="text/javascript">
        function toggle(elemento) {
          if(elemento.value=="familiar") {
            document.getElementById("adultos-form").style.display = "none";
            document.getElementById("infantil-form").style.display = "none";
            document.getElementById("familiar-form").style.display = "block";
           }else{
               if(elemento.value=="adultos"){
                document.getElementById("infantil-form").style.display = "none";
                document.getElementById("familiar-form").style.display = "none";
                document.getElementById("adultos-form").style.display = "block";

               }else{
                   if(elemento.value=="infantil"){
                        document.getElementById("adultos-form").style.display = "none";
                        document.getElementById("familiar-form").style.display = "none";
                        document.getElementById("infantil-form").style.display = "block";
                    }
                }
            }
        }
</script>
<%}else{ %>
	<h1>Eres administrador, no puedes acceder a este menu</h1>
<%} %>
</body>
</html>