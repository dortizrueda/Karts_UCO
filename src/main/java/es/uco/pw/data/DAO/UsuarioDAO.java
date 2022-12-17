package es.uco.pw.data.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import es.uco.pw.business.DTO.ReservaDTO;
import es.uco.pw.business.DTO.UsuarioDTO;
import es.uco.pw.business.ValueObject.Tipo;
import es.uco.pw.business.ValueObject.Tipo_Reserva;
import es.uco.pw.business.managers.UsuarioManager;
import es.uco.pw.data.common.Conexion;
/**
* Clase creada para realizar las operaciones con la base de datos
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/
public class UsuarioDAO {
	
	static ArrayList<UsuarioDTO>list_users=new ArrayList<UsuarioDTO>();
	/**
     * Función que inserta valores a todos los atributos de la clase Usuario
     * @param p
     * @param sqlProp
     * @throws Exception
    */
	public void insertar(UsuarioDTO p, Properties sqlProp,  String urlbd, String userbd, String passwordbd) throws Exception {
		PreparedStatement ps = null;
    	Connection conexion=Conexion.get_Conexion(urlbd,userbd,passwordbd); 
    	UsuarioManager p1 = new UsuarioManager();
    	sqlProp=p1.getSqlProperties();
       	 String SQL=sqlProp.getProperty("INSERT.USER");
		
        try {
     		ps=conexion.prepareStatement(SQL);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellidos());
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getFecha_inscripcion()));
            ps.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getFecha_nacimiento()));
            ps.setString(5, p.getCorreo());
            ps.setString(6, p.getPassword());
            ps.setString(7, p.getTipo().toString());

            if(ps.executeUpdate()==0) {
                System.out.println("Es posible que no se haya guardado nada");
            }

        }catch(Exception ex) {
            throw new Exception("Error en SQL",ex);
        }finally {
            if(ps!=null) {
                try {
                    ps.close();
                }catch(Exception ex) {
                    throw new Exception("Error en SQL",ex);
                }
            }
        }
	}
	/**
     * Función que vuelca a una lista, los elementos de Usuario
     * @param sqlProp
	 * @throws SQLException 
     * @throws Exception
    */
	public ArrayList<UsuarioDTO>getAll(Properties sqlProp,  String urlbd, String userbd, String passwordbd) throws IOException, SQLException{
		PreparedStatement ps = null;
        ResultSet rs = null;
    	Connection conexion=Conexion.get_Conexion(urlbd,userbd,passwordbd); 
    	UsuarioManager p1 = new UsuarioManager();
    	sqlProp=p1.getSqlProperties();
       	 String SQL=sqlProp.getProperty("GETALL.USER");
    		 ps=conexion.prepareStatement(SQL);
            rs = ps.executeQuery();
            ArrayList<UsuarioDTO>espectadores=new ArrayList<>();

        try {
            Statement statement = conexion.createStatement();
            rs = ps.executeQuery();
            while(rs.next()) {

                UsuarioDTO p = new UsuarioDTO();
                p.setNombre(rs.getString("Nombre"));
                p.setApellidos(rs.getString("Apellidos"));
                String fecha_inscripcion=rs.getString("Fecha_inscripcion");
                String fecha_nacimiento=rs.getString("Fecha_nacimiento");
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm");

                Date fecha_inscri=sdf2.parse(fecha_inscripcion);
                Date fecha_nacimiento1=sdf.parse(fecha_nacimiento);
                p.setFecha_inscripcion(fecha_inscri);
                p.setFecha_nacimiento(fecha_nacimiento1);
                p.setCorreo(rs.getString("Correo"));
                p.setPassword(rs.getString("Password"));
                String tipo=rs.getString("Tipo");
                if(tipo.compareTo("admin")==0) {
                	p.setTipo(Tipo.admin);
                }else if(tipo.compareTo("no_admin")==0) {
                	p.setTipo(Tipo.no_admin);
                }

                espectadores.add(p);

            }
            if(statement!=null) statement.close();
            return espectadores;
        }catch(Exception e) {
            System.out.println(e);
            }
        return null;		
	}
	public ArrayList<UsuarioDTO>getAll1(){
		ArrayList<UsuarioDTO>reservas=new ArrayList<UsuarioDTO>();
		try {
			Conexion conex=new Conexion();
			Connection connection=Conexion.get_Conexion1();
			String SQL="";
			Properties SQLproperties = new Properties();
				try {
					Context env= (Context)new InitialContext().lookup("java:comp/env");
					String ruta = (String)env.lookup("Sql");
					ClassLoader classLoad=Thread.currentThread().getContextClassLoader();
					java.io.InputStream inp=classLoad.getResourceAsStream(ruta);
					SQLproperties.load(inp);
					SQL=SQLproperties.getProperty("GETALL.USER");
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
				Statement stmt = connection.createStatement();
				ResultSet rs = (ResultSet)stmt.executeQuery(SQL);
				while(rs.next()) {
					UsuarioDTO p = new UsuarioDTO();
	                p.setNombre(rs.getString("Nombre"));
	                p.setApellidos(rs.getString("Apellidos"));
	                String fecha_inscripcion=rs.getString("Fecha_inscripcion");
	                String fecha_nacimiento=rs.getString("Fecha_nacimiento");
	                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
	                SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm");

	                Date fecha_inscri=sdf2.parse(fecha_inscripcion);
	                Date fecha_nacimiento1=sdf.parse(fecha_nacimiento);
	                p.setFecha_inscripcion(fecha_inscri);
	                p.setFecha_nacimiento(fecha_nacimiento1);
	                p.setCorreo(rs.getString("Correo"));
	                p.setPassword(rs.getString("Password"));
	                String tipo=rs.getString("Tipo");
	                if(tipo.compareTo("admin")==0) {
	                	p.setTipo(Tipo.admin);
	                }else if(tipo.compareTo("no_admin")==0) {
	                	p.setTipo(Tipo.no_admin);
	                }
		                
		                reservas.add(p);
				}
			
			if(stmt!=null) {
				stmt.close();
			}
			rs.close();
			connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return reservas;	
	}
	/**
     * Funcion que modifica el nombre de Usuario
     * @param user
     * @param new_name
     * @param sqlProp
     * @throws Exception
    */
	public void modificar_nombre(String user, String new_name,Properties sqlProp,  String urlbd, String userbd, String passwordbd) throws Exception {
        PreparedStatement ps=null;
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        try {
            String SQL=sqlProp.getProperty("UPDATE.PASSWORD");
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
    		ps=connection.prepareStatement(SQL);
            ps.setString(1, new_name);
            ps.setString(2, user);

            if(ps.executeUpdate()==0) {
                System.out.println("Es posible que no se haya guardado nada");
            }
            ps.close();

        }catch(Exception ex) {
            throw new Exception("Error en SQL",ex);
        }
    }
	/**
     *Funcion que modifica el correo de un Usuario
     * @param user
     * @param correo
     * @param sqlProp
     * @throws Exception
    */
	public void modificar_correo(String user, String correo,Properties sqlProp, String urlbd, String userbd, String passwordbd) throws Exception {
        PreparedStatement ps=null;
        String ruta="sql.properties";
    	UsuarioManager p1 = new UsuarioManager();
    	sqlProp=p1.getSqlProperties();
        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        try {
            String SQL=sqlProp.getProperty("UPDATE.CORREO");
        	 Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
     		ps=connection.prepareStatement(SQL);

            ps.setString(1, correo );
            ps.setString(2, user);

            if(ps.executeUpdate()==0) {
                System.out.println("Es posible que no se haya guardado nada");
            }
            ps.close();

        }catch(Exception ex) {
            throw new Exception("Error en SQL",ex);
        }
    }
	public ArrayList<UsuarioDTO> getAll1(Properties prop, String pass, String urlbd, String userbd, String passwordbd) throws IOException {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<UsuarioDTO>espectadores=new ArrayList<>();
        prop=new Properties();
        String ruta="sql.properties";
       

        FileInputStream proper = new FileInputStream(ruta);
        prop.load(proper);
        try {
            String SQL=prop.getProperty("GETALL.USER");
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
    		ps=connection.prepareStatement(SQL);
            Statement statement = connection.createStatement();
            rs = ps.executeQuery();
            while(rs.next()) {

                UsuarioDTO p = new UsuarioDTO();
                p.setNombre(rs.getString("Nombre"));
                p.setApellidos(rs.getString("Apellidos"));
                String fecha_inscripcion=rs.getString("Fecha_inscripcion");
                String fecha_nacimiento=rs.getString("Fecha_nacimiento");
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm");

                Date fecha_inscri=sdf2.parse(fecha_inscripcion);
                Date fecha_nacimiento1=sdf.parse(fecha_nacimiento);
                p.setFecha_inscripcion(fecha_inscri);
                p.setFecha_nacimiento(fecha_nacimiento1);
                p.setCorreo(rs.getString("Correo"));
                p.setPassword(rs.getString("Password"));
                String tipo=rs.getString("Tipo");
                if(tipo.compareTo("admin")==0) {
                	p.setTipo(Tipo.admin);
                }else if(tipo.compareTo("no_admin")==0) {
                	p.setTipo(Tipo.no_admin);
                }

                espectadores.add(p);

            }
            if(statement!=null) statement.close();
            return espectadores;
        }catch(Exception e) {
            System.out.println(e);
            }
        return null;		
        }
	public void modificar_user(UsuarioDTO user, Properties sqlProp, String urlbd, String userbd, String passwordbd) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
    	Connection conexion=Conexion.get_Conexion(urlbd,userbd,passwordbd); 
    	UsuarioManager p1 = new UsuarioManager();
    	sqlProp=p1.getSqlProperties();
       	 String SQL=sqlProp.getProperty("UPDATE.USER");
        try {
     		ps=conexion.prepareStatement(SQL);

            ps.setString(1, user.getNombre() );
            ps.setString(2, user.getApellidos());
            ps.setString(3, new SimpleDateFormat("yyyy-MM-dd").format(user.getFecha_nacimiento()));
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getCorreo());
            

            if(ps.executeUpdate()==0) {
                System.out.println("Es posible que no se haya guardado nada");
            }
            ps.close();

        }catch(Exception ex) {
            throw new Exception("Error en SQL",ex);
        }
	}
	public UsuarioDTO iniciar_sesion(String correo){
		// TODO Auto-generated method stub
		UsuarioDTO p= new UsuarioDTO();
		try {
			
		Conexion conex=new Conexion();
		Connection connection=Conexion.get_Conexion1();
		String SQL="";
		Properties SQLproperties = new Properties();
			try {
				Context env= (Context)new InitialContext().lookup("java:comp/env");
				String ruta = (String)env.lookup("Sql");
				ClassLoader classLoad=Thread.currentThread().getContextClassLoader();
				java.io.InputStream inp=classLoad.getResourceAsStream(ruta);
				SQLproperties.load(inp);
				SQL=SQLproperties.getProperty("LOGIN.USER");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		ps.setString(1, correo);
		ResultSet rs = (ResultSet)ps.executeQuery();
		while(rs.next()) {
			p.setNombre(rs.getString("Nombre"));
            p.setApellidos(rs.getString("Apellidos"));
			String fecha_inscripcion=rs.getString("Fecha_inscripcion");
            String fecha_nacimiento=rs.getString("Fecha_nacimiento");
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date fecha_inscri=sdf2.parse(fecha_inscripcion);
            Date fecha_nacimiento1=sdf.parse(fecha_nacimiento);
            p.setFecha_inscripcion(fecha_inscri);
            p.setFecha_nacimiento(fecha_nacimiento1);
            p.setCorreo(rs.getString("Correo"));
            p.setPassword(rs.getString("Password"));
            String tipo=rs.getString("Tipo");
            if(tipo.compareTo("admin")==0) {
            	p.setTipo(Tipo.admin);
            }else if(tipo.compareTo("no_admin")==0) {
            	p.setTipo(Tipo.no_admin);
            }
		}
		ps.close();
		rs.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	public boolean check_mail(String correo) {
		// TODO Auto-generated method stub
		boolean check=true;
		UsuarioDTO p= new UsuarioDTO();
		try {
		Conexion conex=new Conexion();
		Connection connection=Conexion.get_Conexion1();
		String SQL="";
		Properties SQLproperties = new Properties();
			try {
				Context env= (Context)new InitialContext().lookup("java:comp/env");
				String ruta = (String)env.lookup("Sql");
				ClassLoader classLoad=Thread.currentThread().getContextClassLoader();
				java.io.InputStream inp=classLoad.getResourceAsStream(ruta);
				SQLproperties.load(inp);
				SQL=SQLproperties.getProperty("CHECK.USER");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		ps.setString(1, correo);
		String correo1="";
		ResultSet rs = (ResultSet)ps.executeQuery();
		while(rs.next()) {
			correo1=rs.getString("Correo");
		}
		if(correo.equals(correo1)) {
			check=false;
		}
		ps.close();
		rs.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	public int insertar1(UsuarioDTO p) {
		// TODO Auto-generated method stub
		int devuelto=0;
		try {
		Conexion conex=new Conexion();
		Connection connection=Conexion.get_Conexion1();
		String SQL="";
		Properties SQLproperties = new Properties();
			try {
				Context env= (Context)new InitialContext().lookup("java:comp/env");
				String ruta = (String)env.lookup("Sql");
				ClassLoader classLoad=Thread.currentThread().getContextClassLoader();
				java.io.InputStream inp=classLoad.getResourceAsStream(ruta);
				SQLproperties.load(inp);
				SQL=SQLproperties.getProperty("INSERT.USER");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		ps.setString(1, p.getNombre());
        ps.setString(2, p.getApellidos());
        ps.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getFecha_inscripcion()));
        ps.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(p.getFecha_nacimiento()));
        ps.setString(5, p.getCorreo());
        ps.setString(6, p.getPassword());
        ps.setString(7, p.getTipo().toString());
		devuelto=ps.executeUpdate();

		ps.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return devuelto;
		
	}
	public void update(String apellidos, String nombre, String password, String correo) {
		// TODO Auto-generated method stub
		try {
			Conexion conex=new Conexion();
			Connection connection=Conexion.get_Conexion1();
			String SQL="";
			Properties SQLproperties = new Properties();
				try {
					Context env= (Context)new InitialContext().lookup("java:comp/env");
					String ruta = (String)env.lookup("Sql");
					ClassLoader classLoad=Thread.currentThread().getContextClassLoader();
					java.io.InputStream inp=classLoad.getResourceAsStream(ruta);
					SQLproperties.load(inp);
					SQL=SQLproperties.getProperty("UPDATE.USER1");
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
			PreparedStatement ps=connection.prepareStatement(SQL);
			ps.setString(1, nombre);
	        ps.setString(2, apellidos);
	        ps.setString(3, password);
	        ps.setString(4, correo);
	       
	        ps.executeUpdate();
			ps.close();
			connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}
	
	

}
