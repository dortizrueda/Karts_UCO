package es.uco.pw.business.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.business.DTO.UsuarioDTO;
import es.uco.pw.business.ValueObject.Tipo;
import es.uco.pw.data.DAO.UsuarioDAO;
/**
* Clase creada para realizar las operaciones con la base de datos
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/
public class UsuarioManager {
	private static Properties prop;
	private static Properties sqlProp;


	private static UsuarioManager instance = null;
	static UsuarioDAO msu = new UsuarioDAO();
	private static ArrayList<UsuarioDTO>list_users = new ArrayList<UsuarioDTO>();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static Properties getSqlProperties() {
		return sqlProp;
		
	}
	public static void setSqlProperties(Properties prop) {
			sqlProp=prop;
		
	}
	public static UsuarioManager getInstance()throws FileNotFoundException,IOException{
		if(instance==null) {
			instance=new UsuarioManager();
		}	
		return instance;
		
	}
	/**
     * Clase que se encarga de crear un usuario y asignarle sus atributos
     * paragm none
	 * @throws Exception 
    */

	public static boolean crearUsuario(UsuarioDTO p, String urlbd, String userbd, String passwordbd) throws Exception {
		// TODO Auto-generated method stub
		prop=getSqlProperties();

		ArrayList<UsuarioDTO> users = msu.getAll(prop, urlbd, userbd, passwordbd);
	    for (int i = 0; i < users.size(); i++) {
	      if (users.get(i).getCorreo().equals(p.getCorreo())) {
	        return false;
	      }
	    }
		msu.insertar(p, prop,urlbd,userbd,passwordbd);
		return true;
	}

	public static void actualizar_fichero() {
		try {
			ArrayList<String>ficheros=new ArrayList<String>();
			ficheros=Properties_Fich();
			FileWriter fichero = new FileWriter(ficheros.get(0));
			SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
			for(int i=0;i<list_users.size();i++) {
				fichero.write(list_users.get(i).getNombre()+"\n");
				fichero.write(list_users.get(i).getApellidos()+"\n");
				fichero.write(sd.format(list_users.get(i).getFecha_nacimiento())+"\n");
				fichero.write(sd.format(list_users.get(i).getFecha_inscripcion())+"\n");
				fichero.write(list_users.get(i).getCorreo()+"\n");
				fichero.write(list_users.get(i).getPassword()+"\n");
				fichero.write(list_users.get(i).getTipo()+"\n");
			}
			fichero.close();
		}catch(Exception ex) {ex.printStackTrace();}
	}
	

	
	public static void cargar_fichero() throws FileNotFoundException, IOException {
		String nombre,apellidos,correo,password;
		String fecha_nacimiento;
		String fecha_ins;
		
		ArrayList<String>ficheros=new ArrayList<String>();
		ficheros=Properties_Fich();
		File fich_usuarios,fich_pistas;
		fich_usuarios=new File(ficheros.get(0));
		fich_pistas=new File(ficheros.get(1));
		if(fich_usuarios.exists()) {
		try {
			FileReader lector = new FileReader(ficheros.get(0));
			BufferedReader BR = new BufferedReader(lector);
			
			while((nombre=BR.readLine())!=null) {
				apellidos=BR.readLine();
				fecha_nacimiento=BR.readLine();
				fecha_ins=BR.readLine();
				correo=BR.readLine();
				password=BR.readLine();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date fecha= sdf.parse(fecha_ins);
				Date fecha2=sdf.parse(fecha_nacimiento);
				UsuarioDTO p = new UsuarioDTO();
				p.setNombre(nombre);
				p.setApellidos(apellidos);
				p.setFecha_nacimiento(fecha2);
				p.setFecha_inscripcion(fecha);
				p.setCorreo(correo);
				p.setPassword(password);
				
				list_users.add(p);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
		
	}

	public static void ListarUsuarios(ArrayList<UsuarioDTO> user) {
		// TODO Auto-generated method stub
		for(int indice=0;indice<user.size();indice++) {
			System.out.println(user.get(indice));
		}
	}
	/**
     * Clase que permite modificar la información asociada a un usuario
	 * @throws Exception 
    */
	public static void modificarUsuario(UsuarioDTO user,Properties sqlProp,String urlbd,String userbd,String passwordbd) throws Exception {
		// TODO Auto-generated method stub
		sqlProp=getSqlProperties();
		msu.modificar_user(user, sqlProp, urlbd, userbd, passwordbd);
	}

	
	public static ArrayList<String>Properties_Fich() throws FileNotFoundException, IOException{
		Properties p = new Properties();
		ArrayList<String>salida=new ArrayList<String>();
		String path="config.properties";
	
			BufferedReader br = new BufferedReader(new FileReader(new File(path))); 
			p.load(br);
			
			String usuario,pista,reservas;
			usuario=p.getProperty("usuarios");
			pista=p.getProperty("pista");
			reservas=p.getProperty("reservas");
			salida.add(usuario);
			salida.add(pista);
			salida.add(reservas);
		
			return salida;
		
	}

	public static void mostrar_datos() {
		// TODO Auto-generated method stub
		for(int i = 0;i<list_users.size();i++) {
			System.out.println(list_users.get(i)+"");			
		}
	}



	 /**
     * Clase que permite al usuario acceder al sistema
     * paragm nick, pass, p1
     * return devuelve el menú al que se esté accediendo
	 * @param user 
	 * @throws IOException 
	 * @throws SQLException 
    */
//	public static  int iniciar_sesion(String nick, String pass) throws IOException {
//		// TODO Auto-generated method stub
////		ArrayList<DTOUsuario> user=msu.getAll(prop);
//		for(int i = 0;i<user.size();i++) {
//			if((user.get(i).getPassword().equals(pass))&&(user.get(i).getCorreo().equals(nick))){
//				if(user.get(i).getCorreo().equals("admin@uco.es")||user.get(i).getTipo().equals(Tipo.admin)) {
//					return 1;
//				}else {
//					return 2;
//				}
//			}
//		}
//		return 0;
//	}
	public boolean iniciar_sesion1(String nick, String pass, String urlbd, String userbd, String passwordbd) throws IOException, SQLException {
		// TODO Auto-generated method stub
		prop=getSqlProperties();
		ArrayList<UsuarioDTO> user=msu.getAll(prop,urlbd,userbd,passwordbd);
		for(int i = 0;i<user.size();i++) {
			if((user.get(i).getPassword().equals(pass))&&(user.get(i).getCorreo().equals(nick))){
				return true;
			}
		}
		return false;
	}
	public static UsuarioDTO buscarUser(String correo,ArrayList<UsuarioDTO> users) {
		
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getCorreo().equals(correo)) {
				return users.get(i);
			}
		}
		return null;
		
	}
	public static ArrayList<UsuarioDTO> listado_users() {
		// TODO Auto-generated method stub
		return list_users;
	}
}
