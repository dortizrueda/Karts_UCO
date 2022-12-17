package es.uco.pw.business.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import es.uco.pw.data.DAO.PistaDAO;
import es.uco.pw.data.DAO.ReservaDAO;
import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.DTO.ReservaDTO;
import es.uco.pw.business.DTO.UsuarioDTO;
import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.business.ValueObject.Tipo_Reserva;
import es.uco.pw.business.managers.PistaManager;
import es.uco.pw.business.managers.UsuarioManager;
/**
* Clase creada para realizar las operaciones con la base de datos
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/
public class ReservaManager {
	private static int idcounter=1;
	static Properties sqlProp;

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	DAOUsuario usuario=DAOUsuario.getInstance();
	static ArrayList<ReservaDTO>list_reservas=new ArrayList<ReservaDTO>();
	static ArrayList<UsuarioDTO>list_users=new ArrayList<UsuarioDTO>();
	static ReservaDAO msu = new ReservaDAO();
	private static ReservaManager instance = null;
	private static Properties prop;
	
	public static ReservaManager getInstance()throws FileNotFoundException,IOException{
		if(instance==null) {
			instance=new ReservaManager();
		}	
		return instance;
		
	}

	public ReservaManager() {
		
	}
	/**
     * Clase que se encarga de crear una reserva y asignarle sus atributos
     * paragm none
	 * @param pistas 
	 * @param user 
	 * @throws Exception 
    */
	public static void crearReserva(ArrayList<PistaDTO> pistas, ArrayList<UsuarioDTO> user) throws Exception {
		ReservaDTO p = new ReservaDTO();
		ReservaDTO [] reservas = new ReservaDTO[5];
		Tipo_Reserva tipo;
		float precio;
		float descuento=1;

		System.out.println("Introduzca tu identificador de usuario (correo electronico): ");
		String id_user=reader.readLine();
		p.setId_usuario(id_user);
		System.out.println("¿Desea realizar una reserva individual o prefiere un bono de 5 reservas?");
		System.out.println("1.- Individual");
		System.out.println("2.- Bono de 5 reservas");
		System.out.println("Seleccione 1/2");
		int seleccion=Integer.parseInt(reader.readLine());
		if(seleccion==1) { //Bono Individual
			System.out.println("Introduzca la fecha-hora de la reserva(yyyy-MM-dd HH:mm): ");
			String fecha_hora=reader.readLine();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date fecha= sdf.parse(fecha_hora);
			p.setFecha_hora(fecha);
			if(antiguedad_user(id_user,user)>730) {
				descuento=(float) 0.90;
				p.setDescuento(10);
			}
			System.out.println("¿Que tipo de reserva desea realizar?");
			System.out.println("1.- Familiar");
			System.out.println("2.- Adultos");
			System.out.println("3.- Infantil");
			System.out.println("Seleccione 1/2/3");
			int seleccion2=Integer.parseInt(reader.readLine());
			if(seleccion2==1) { //Familiar
				 tipo= Tipo_Reserva.familiar;
				 p.setReserva(tipo);
				 System.out.println("¿Qué pista deseas reservar?");
				 System.out.println("Seleccione su identificador (Nombre): ");
				 
				 String id_pista=reader.readLine();
				 p.setId_pista(id_pista);
				 if(check_pista(tipo,id_pista,pistas)==false) {
					 System.out.println("Error esta pista no existe, o no pertenece a la misma categoria");
				 }else {
					 System.out.println("¿Durante cuanto tiempo deseas reservar la pista?");
					 System.out.println("60 - 90 - 120 minutos");
					 int minutos=Integer.parseInt(reader.readLine());
					 p.setMinutos(minutos);
					 if(minutos==60) {
						 precio=20*descuento;
						 p.setPrecio_reserva(precio);
					 }else if(minutos==90) {
						 precio=30*descuento;
						 p.setPrecio_reserva(precio);
					 }else if(minutos==120) {
						 precio=40*descuento;
						 p.setPrecio_reserva(precio);
					 }
					 System.out.println("¿Cuantos adultos sois?");
					 int adultos=Integer.parseInt(reader.readLine());
					 System.out.println("¿Cuantos niños sois?");
					 int children=Integer.parseInt(reader.readLine());
					 int suma = adultos + children;
					 if(check_aforo(suma,id_pista,pistas)==false) {
						 System.out.println("Error en el aforo...");

					 }else {
						 p.setNum_adults(adultos);
						 p.setNum_children(children);
						 
						 list_reservas.add(p);
//						 msu.insertar(p, prop);
						 
					 }
				 }
				
			}else if(seleccion2==2) { //Adultos
				tipo=Tipo_Reserva.adultos;
				p.setReserva(tipo);

				System.out.println("¿Qué pista deseas reservar?");
				 System.out.println("Seleccione su identificador (Nombre): ");
				 String id_pista=reader.readLine();
				 p.setId_pista(id_pista);
				 if(check_pista(tipo,id_pista,pistas)==false) {
					 System.out.println("Error esta pista no existe, o no pertenece a la misma categoria");
				 }else {
					 System.out.println("¿Durante cuanto tiempo deseas reservar la pista?");
					 System.out.println("60 - 90 - 120 minutos");
					 int minutos=Integer.parseInt(reader.readLine());
					 p.setMinutos(minutos);
					 if(minutos==60) {
						 precio=20*descuento;
						 p.setPrecio_reserva(precio);

					 }else if(minutos==90) {
						 precio=30*descuento;
						 p.setPrecio_reserva(precio);

					 }else if(minutos==120) {
						 precio=40*descuento;
						 p.setPrecio_reserva(precio);

					 }
					 System.out.println("¿Cuantos adultos sois?");
					 int adultos=Integer.parseInt(reader.readLine());
					 int children=0;
					 if(check_aforo(adultos,id_pista,pistas)==false) {
						 System.out.println("Error en el aforo...");

					 }else {
						 p.setNum_adults(adultos);
						 p.setNum_children(children);
						 
						 list_reservas.add(p);
//						 msu.insertar(p, prop);
					 }
				 }
				
			}else if(seleccion2==3) { //Infantil
				tipo=Tipo_Reserva.infantil;
				p.setReserva(tipo);
				System.out.println("¿Qué pista deseas reservar?");
				 System.out.println("Seleccione su identificador (Nombre): ");
				 String id_pista=reader.readLine();
				 p.setId_pista(id_pista);

				 if(check_pista(tipo,id_pista,pistas)==false) {
					 System.out.println("Error esta pista no existe, o no pertenece a la misma categoria");
				 }else {
					 System.out.println("¿Durante cuanto tiempo deseas reservar la pista?");
					 System.out.println("60 - 90 - 120 minutos");
					 int minutos=Integer.parseInt(reader.readLine());
					 p.setMinutos(minutos);
					 if(minutos==60) {
						 precio=20*descuento;
						 p.setPrecio_reserva(precio);

					 }else if(minutos==90) {
						 precio=30*descuento;
						 p.setPrecio_reserva(precio);

					 }else if(minutos==120) {
						 precio=40*descuento;
						 p.setPrecio_reserva(precio);

					 }
					 System.out.println("¿Cuantos niños sois?");
					 int children=Integer.parseInt(reader.readLine());
					 int adultos=0;
					 if(check_aforo(children,id_pista,pistas)==false) {
						 System.out.println("Error en el aforo...");

					 }else {
						 p.setNum_adults(adultos);
						 p.setNum_children(children);
						 
						 list_reservas.add(p);
//						 msu.insertar(p, prop);
					 }
					 
				 }	
			}else {
				System.out.println("Error en la seleccion de reserva");
			}

		}else if(seleccion==2){ // Bono 5 reservas
			System.out.println("¿Que tipo de reserva desea realizar?");
			System.out.println("1.- Familiar");
			System.out.println("2.- Adultos");
			System.out.println("3.- Infantil");
			System.out.println("Seleccione 1/2/3");
			int seleccion2=Integer.parseInt(reader.readLine());
			
				
				if(seleccion2==1) { //Familiar
					for(int i=0;i<5;i++) {
						reservas[i] = new ReservaDTO();
						reservas[i].setId_usuario(id_user);

					 tipo= Tipo_Reserva.familiar;
					 reservas[i].setReserva(tipo);
					 System.out.println("Introduzca la fecha-hora de la reserva(yyyy-MM-dd HH:mm): ");
						String fecha_hora=reader.readLine();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						Date fecha= sdf.parse(fecha_hora);
						reservas[i].setFecha_hora(fecha);
					 System.out.println("¿Qué pista deseas reservar?");
					 System.out.println("Seleccione su identificador (Nombre): ");
					 String id_pista=reader.readLine();
					 reservas[i].setId_pista(id_pista);
					 if(check_pista(tipo,id_pista,pistas)==false) {
						 System.out.println("Error esta pista no existe, o no pertenece a la misma categoria");
					 }else {

						 System.out.println("¿Durante cuanto tiempo deseas reservar la pista?");
						 System.out.println("60 - 90 - 120 minutos");
						 int minutos=Integer.parseInt(reader.readLine());
						 reservas[i].setMinutos(minutos);
						 descuento=(float) 0.95;
						 reservas[i].setDescuento(5);
						 if(minutos==60) {
							 precio=19;
							 reservas[i].setPrecio_reserva(precio);
						 }else if(minutos==90) {
							 precio=(float) 28.5;
							 reservas[i].setPrecio_reserva(precio);

						 }else if(minutos==120) {
							 precio=38;
							 reservas[i].setPrecio_reserva(precio);

						 }
						 System.out.println("¿Cuantos adultos sois?");
						 int adultos=Integer.parseInt(reader.readLine());
						 System.out.println("¿Cuantos niños sois?");
						 int children=Integer.parseInt(reader.readLine());
						 int suma=adultos+children;
						 if(check_aforo(suma,id_pista,pistas)==false) {
							 System.out.println("Error en el aforo...");

						 }else {
							 reservas[i].setNum_adults(adultos);
							 reservas[i].setNum_children(children);
							 
							 list_reservas.add(reservas[i]);
//							 msu.insertar(reservas[i], prop);
						 }
						 
					 }


					}
				}else if(seleccion2==2) { //Adultos
					
					for(int i=0;i<5;i++) {
					reservas[i] = new ReservaDTO();
					reservas[i].setId_usuario(id_user);
					System.out.println("Introduzca la fecha-hora de la reserva(yyyy-MM-dd HH:mm): ");
					String fecha_hora=reader.readLine();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date fecha= sdf.parse(fecha_hora);
					reservas[i].setFecha_hora(fecha);
					tipo=Tipo_Reserva.adultos;
					 reservas[i].setReserva(tipo);
					System.out.println("¿Qué pista deseas reservar?");
					 System.out.println("Seleccione su identificador (Nombre): ");
					 String id_pista=reader.readLine();
					 reservas[i].setId_pista(id_pista);

					 if(check_pista(tipo,id_pista,pistas)==false) {
						 System.out.println("Error esta pista no existe, o no pertenece a la misma categoria");
					 }else {

						 System.out.println("¿Durante cuanto tiempo deseas reservar la pista?");
						 System.out.println("60 - 90 - 120 minutos");
						 int minutos=Integer.parseInt(reader.readLine());
						 reservas[i].setMinutos(minutos);
						 descuento=(float) 0.95;
						 reservas[i].setDescuento(5);
						 if(minutos==60) {
							 precio=19;
							 reservas[i].setPrecio_reserva(precio);
						 }else if(minutos==90) {
							 precio=(float) 28.5;
							 reservas[i].setPrecio_reserva(precio);

						 }else if(minutos==120) {
							 precio=38;
							 reservas[i].setPrecio_reserva(precio);

						 }
						 System.out.println("¿Cuantos adultos sois?");
						 int adultos=Integer.parseInt(reader.readLine());
						 int children=0;
						 if(check_aforo(adultos,id_pista,pistas)==false) {
							 System.out.println("Error en el aforo...");

						 }else {
							 reservas[i].setNum_adults(adultos);
							 reservas[i].setNum_children(children);
							 
							 list_reservas.add(reservas[i]);
//							 msu.insertar(reservas[i], prop);

						 }
						 
					 }
					}
				}else if(seleccion2==3) { //Infantil
					for(int i=0;i<5;i++) {

						reservas[i] = new ReservaDTO();
						reservas[i].setId_usuario(id_user);
					System.out.println("Introduzca la fecha-hora de la reserva(yyyy-MM-dd HH:mm): ");
					String fecha_hora=reader.readLine();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date fecha= sdf.parse(fecha_hora);
					reservas[i].setFecha_hora(fecha);
					tipo=Tipo_Reserva.infantil;
					 reservas[i].setReserva(tipo);
					System.out.println("¿Qué pista deseas reservar?");
					 System.out.println("Seleccione su identificador (Nombre): ");
					 String id_pista=reader.readLine();
					 reservas[i].setId_pista(id_pista);

					 if(check_pista(tipo,id_pista,pistas)==false) {
						 System.out.println("Error esta pista no existe, o no pertenece a la misma categoria");
					 }else {

						 System.out.println("¿Durante cuanto tiempo deseas reservar la pista?");
						 System.out.println("60 - 90 - 120 minutos");
						 int minutos=Integer.parseInt(reader.readLine());
						 reservas[i].setMinutos(minutos);
						 descuento=(float) 0.95;
						 reservas[i].setDescuento(5);
						 if(minutos==60) {
							 precio=19;
							 reservas[i].setPrecio_reserva(precio);
						 }else if(minutos==90) {
							 precio=(float) 28.5;
							 reservas[i].setPrecio_reserva(precio);

						 }else if(minutos==120) {
							 precio=38;
							 reservas[i].setPrecio_reserva(precio);

						 }
						 int adultos=0;
						 System.out.println("¿Cuantos niños sois?");
						 int children=Integer.parseInt(reader.readLine());
						 if(check_aforo(children,id_pista,pistas)==false) {
							 System.out.println("Error en el aforo...");
						 }else {
							 reservas[i].setNum_adults(adultos);
							 reservas[i].setNum_children(children);
							 
							 list_reservas.add(reservas[i]);
//							 msu.insertar(reservas[i], prop);
						 }
					 }
					}
				}else {
					System.out.println("Error en la seleccion de reserva");
				}

			}
			
//		actualizar_fichero1();
	
	}
	/**
     * Clase que controla el aforo y no dar más coches de la cuenta
     * paragm none
	  * return True si quedan coches para reservar, false si no quedan 
	 * @param pistas 
    */
public static boolean check_aforo(int suma, String id_pista, ArrayList<PistaDTO> pistas) {
		// TODO Auto-generated method stub
	PistaManager p = new PistaManager();
	
	for(int i=0;i<pistas.size();i++) {
		if(pistas.get(i).getNombre().equals(id_pista)) {
			if(suma<=pistas.get(i).getNumero_karts()) {
				return true;
			}
		}
	}
		return false;
	}
/**
 * Clase que comprueba los datos de la pista elegida mediante su tipo y nombre
 * paragm none
 * return True si quedan coches para reservar, false si no quedan 
 * @param pistas 
*/
public static boolean check_pista(Tipo_Reserva tipo, String nombre_pista, ArrayList<PistaDTO> pistas) {
		// TODO Auto-generated method stub
		PistaManager p = new PistaManager();
		Dificultad_Pista dp = null;
		if(tipo.compareTo(Tipo_Reserva.adultos)==0) {
			dp=Dificultad_Pista.adultos;
		}else if(tipo.compareTo(Tipo_Reserva.familiar)==0) {
			dp=Dificultad_Pista.familiar;
		}else if(tipo.compareTo(Tipo_Reserva.infantil)==0) {
			dp=Dificultad_Pista.infantil;
		}
		for(int i=0;i<pistas.size();i++) {
			if(pistas.get(i).getNombre().equals(nombre_pista) && pistas.get(i).getDificultad().equals(dp)) {
	
					return true;
				}
		}
		return false;
	}
/**
 * Clase que comprueba si el usuario está registrado y desde cuendo
 * paragm id_user
 * return fecha de la inscripción el usuario
 * @param user 
*/

public static int antiguedad_user(String id_user, ArrayList<UsuarioDTO> user) {
		// TODO Auto-generated method stub
		int num=0;
		UsuarioDTO p = new UsuarioDTO();
		for(int i=0;i<user.size();i++) {
			if(user.get(i).getCorreo().equals(id_user)) {
				 num = p.calcularAntiguedad(user.get(i).getFecha_inscripcion());
			}
		}
		return num;
	}

//public static void cargar_fichero() throws FileNotFoundException, IOException {
//		
//		String id_user,id_pista,fecha_hora,disponibilidad;
//		Disponibilidad dispo = null;
//		int minutos;
//		float precio_reserva;
//		float descuento;
//		int num_adultos;
//		String reserva;
//		Tipo_Reserva tipo = null;
//		int num_children;
//		
//		ArrayList<String>ficheros=new ArrayList<String>();
//		ficheros=Properties_Fich();
//		File fich_reserva;
//		fich_reserva=new File(ficheros.get(2));
//		if(fich_reserva.exists()) {
//		try {
//			FileReader lector = new FileReader(ficheros.get(2));
//			BufferedReader BR = new BufferedReader(lector);
//			
//			while((id_user=BR.readLine())!=null) {
//				fecha_hora=BR.readLine();
//				minutos=Integer.parseInt(BR.readLine());
//				id_pista=BR.readLine();
//				precio_reserva=Float.parseFloat(BR.readLine());
//				descuento=Float.parseFloat(BR.readLine());
//				reserva=BR.readLine();
//				if(reserva.compareTo("infantil")==0){
//					tipo=Tipo_Reserva.infantil;
//				}else if(reserva.compareTo("familiar")==0){
//					tipo=Tipo_Reserva.familiar;
//				}else if(reserva.compareTo("adultos")==0){
//					tipo=Tipo_Reserva.adultos;
//				}
//				num_adultos=Integer.parseInt(BR.readLine());
//				num_children=Integer.parseInt(BR.readLine());
//				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//
//				Date fech_h=sdf.parse(fecha_hora);
//				int descuento1= (int) (descuento);
//				Reserva p= new Reserva();
//				p.setDescuento(descuento1);
//				p.setFecha_hora(fech_h);
//				p.setId_pista(id_pista);
//				p.setId_usuario(id_user);
//				p.setMinutos(minutos);
//				p.setNum_adults(num_adultos);
//				p.setPrecio_reserva(precio_reserva);
//				p.setReserva(tipo);
//				p.setNum_children(num_children);
//				list_reservas.add(p);
//				
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//		
//	}

//	public static ArrayList<String>Properties_Fich() throws FileNotFoundException, IOException{
//		Properties p = new Properties();
//		ArrayList<String>salida=new ArrayList<String>();
//		String path="config.properties";
//	
//			BufferedReader br = new BufferedReader(new FileReader(new File(path))); 
//			p.load(br);
//			
//			String usuario,pista,reservas,karts;
//			usuario=p.getProperty("usuarios");
//			pista=p.getProperty("pista");
//			reservas=p.getProperty("reservas");
//			karts=p.getProperty("karts");
//			salida.add(usuario);
//			salida.add(pista);
//			salida.add(reservas);
//			salida.add(karts);
//		
//			return salida;
//		
//	}
//	public static void actualizar_fichero1() {
//		try {
//			ArrayList<String>ficheros=new ArrayList<String>();
//			ficheros=Properties_Fich();
//			FileWriter fichero = new FileWriter(ficheros.get(2));
//			SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy HH:mm");
//			for(int i=0;i<list_reservas.size();i++) {
//				fichero.write(list_reservas.get(i).getId_usuario()+"\n");
//				fichero.write(sd.format(list_reservas.get(i).getFecha_hora())+"\n");
//				fichero.write(list_reservas.get(i).getMinutos()+"\n");
//				fichero.write(list_reservas.get(i).getId_pista()+"\n");
//				fichero.write(list_reservas.get(i).getPrecio_reserva()+"\n");
//				fichero.write(list_reservas.get(i).getDescuento()+"\n");
//				fichero.write(list_reservas.get(i).getReserva()+"\n");
//				fichero.write(list_reservas.get(i).getNum_adults()+"\n");
//				fichero.write(list_reservas.get(i).getNum_children()+"\n");
//			}
//			fichero.close();
//		}catch(Exception ex) {ex.printStackTrace();}
//	}
/**
 * Clase que permite mostrar una reserva hecha por el usuario
 * @param reservas 
 * @throws Exception 
 * @throws IOExceptiom
*/
	public static void mostrarReserva(ArrayList<ReservaDTO> reservas) {
		// TODO Auto-generated method stub
		for(int i=0;i<reservas.size();i++) {
			System.out.println(reservas.get(i));
		}
	}
	 /**
     * Clase que permite cancelar una reserva hecha por el usuario
	 * @param reservas 
	 * @throws Exception 
     * @throws IOExceptiom
    */
	public static void cancelarReserva(ArrayList<ReservaDTO> reservas) throws Exception {
		// TODO Auto-generated method stub
		

	
		System.out.println("Introduzca su identificador(correo electronico): ");
		String id_user=reader.readLine();
		
			for(int i=0;i<reservas.size();i++) {
				if(reservas.get(i).getId_usuario().equals(id_user)) {
					reservas.remove(i);
//					msu.eliminar( null, id_user ,prop);
				}
//			actualizar_fichero1();
		}
		
	}
	/**
     * Clase que permite modificar una reserva hecha por el usuario
     * @param reservas
     * @throws IOExceptiom
     * @throws ParseException
    */

	public static void modificarReserva(ArrayList<ReservaDTO> reservas) throws ParseException, IOException {
		// TODO Auto-generated method stub
		System.out.println("\nIntroduzca tu identificador de usuario: ");
		String titulo=reader.readLine(); 
		System.out.println("Introduzca la fecha de reserva que desea modificar (dd/MM/yyyy HH:mm): ");
		String fecha=reader.readLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date fecha1=sdf.parse(fecha);
		
		System.out.println("\nIntroduzca el atributo que deseas modificar 1-Pista 2-Minutos : ");
		int opcion=Integer.parseInt(reader.readLine());
		switch(opcion) {
		case 1:
			System.out.println("Introduzca el nombre nuevo: ");
			String new_titulo=reader.readLine(); 
			for(int i=0;i<list_reservas.size();i++) {
				if(list_reservas.get(i).getId_usuario().equals(titulo) && list_reservas.get(i).getFecha_hora().equals(fecha1)) {
					list_reservas.get(i).setId_pista(new_titulo);
				}
			}

			break;
		case 2:
			System.out.println("Introduzca los minutos nuevos: ");
			System.out.println("60 - 90 -120");
			int new_descripcion=Integer.parseInt(reader.readLine()); 
			for(int i=0;i<list_reservas.size();i++) {
				if(list_reservas.get(i).getId_usuario().equals(titulo)&& list_reservas.get(i).getFecha_hora().equals(fecha1)) {
					list_reservas.get(i).setMinutos(new_descripcion);
				}
			}

			break;
		}
	}
	/**
     * Clase que devuelve el número de reservas que puede realizar aún el usuario
     * @param reservas
     * @throws ParseException
     * return número de reservas futuras
    */
	public static int mostarReservasFuturas(ArrayList<ReservaDTO> reservas) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		int cont=0;
		for(int i=0;i<reservas.size();i++) {
			cont ++;
		}
		return cont;
	}
	/**
     * Clase que consulta una de las reservas del usuario
     * @param reservas
     * @param pista
     * @throws ParseException
    */
	public static void consultaReservas(String pista, ArrayList<ReservaDTO> reservas) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		for(int i=0;i<reservas.size();i++) {
			if(reservas.get(i).getId_pista().equals(pista)){
				System.out.println(reservas.get(i));
			}
		}
	}
	
	public static ArrayList<ReservaDTO> nextReserva(ArrayList<ReservaDTO>reservas) {
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");

		for(int i=0;i<reservas.size()-1;i++) {
			for(int j=i+1;j<reservas.size();j++)
				if(reservas.get(i).getFecha_hora().before(reservas.get(j).getFecha_hora())) {
					ReservaDTO aux= reservas.get(i);
					reservas.get(i).equals(reservas.get(j));
					reservas.get(j).equals(aux);
			}
			
		}
		return reservas;
		
	}
	public static int numero_Reservas(String correo, ArrayList<ReservaDTO>reservas) {
		int numero=0;
		for(int i=0;i<reservas.size();i++) {
			if(reservas.get(i).getId_usuario().equals(correo)) {
				numero ++;
			}
		}
		return numero;
	}
	public static Date fech_nextReserva(String correo,ArrayList<ReservaDTO>reservas) {
		for(int i=0;i<reservas.size();i++) {
			if(reservas.get(i).getId_usuario().equals(correo)) {
				return reservas.get(i).getFecha_hora();
			}
		}
		return null;
		
	}

	public static Properties getSqlProperties() {
		// TODO Auto-generated method stub
		return sqlProp;
	}
	public static void setSqlProperties(Properties sqlProp) {
		// TODO Auto-generated method stub
			sqlProp=sqlProp;
	}
	
}
