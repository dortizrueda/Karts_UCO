package es.uco.pw.data.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import es.uco.pw.business.DTO.KartDTO;
import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.DTO.ReservaDTO;
import es.uco.pw.business.DTO.UsuarioDTO;
import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.business.ValueObject.Estado_Kart;
import es.uco.pw.business.ValueObject.Tipo_Kart;
import es.uco.pw.business.ValueObject.Tipo_Reserva;
import es.uco.pw.data.common.Conexion;
/**
* Clase creada para realizar las operaciones con la base de datos
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/
public class PistaDAO {
	/**
     * Funcion que muestra e inserta todos los atributos de la clase Pista
     * @param sqlProp
     * @return Todos los atributos de pista
     * @throws IOException
    */
	public ArrayList<PistaDTO>getAll(Properties sqlProp,String urlbd, String userbd, String passwordbd) throws IOException{
		PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<PistaDTO>espectadores=new ArrayList<>();
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        try {
            String SQL=sqlProp.getProperty("GETALL.PISTA");
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
            ps=connection.prepareStatement(SQL);
            Statement statement = connection.createStatement();
            rs = ps.executeQuery();
            while(rs.next()) {

                PistaDTO p = new PistaDTO();
                p.setNombre(rs.getString("nombre"));
                String dispo=rs.getString("disponibilidad");
                if(dispo.compareTo("disponible")==0) {
                	p.setDisponibilidad(Disponibilidad.disponible);
                }else if(dispo.compareTo("no_disponible")==0) {
                	p.setDisponibilidad(Disponibilidad.no_disponible);

                }
                String dificult=rs.getString("dificultad");
                if(dificult.compareTo("infantil")==0) {
                	p.setDificultad(Dificultad_Pista.infantil);
                }else if(dificult.compareTo("familiar")==0) {
                	p.setDificultad(Dificultad_Pista.familiar);
                }else if(dificult.compareTo("adultos")==0) {
                	p.setDificultad(Dificultad_Pista.adultos);
                }
                p.setNumero_karts(rs.getInt("numero_karts"));
                espectadores.add(p);

            }
            if(statement!=null) statement.close();
            return espectadores;
        }catch(Exception e) {
            System.out.println(e);
            }
        return null;		
	}
	/**
     * Funcion que inserta valores a todos los atributos de la clase Pista
     * @param p
     * @param sqlProp
     * @throws Exception
    */
	public void insertar(PistaDTO p, Properties sqlProp,String urlbd, String userbd, String passwordbd) throws Exception {
		PreparedStatement ps=null;
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        String SQL = sqlProp.getProperty("INSERT.PISTA");
        try {
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
            ps=connection.prepareStatement(SQL);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.isDisponibilidad().toString());
            ps.setString(3, p.getDificultad().toString());
            ps.setInt(4,p.getNumero_karts());
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
     * Funcion que vuelca los valores a una lista
     * @param p
     * @param sqlProp
     * @throws Exception
    */
	public ArrayList<KartDTO>getAll2(Properties sqlProp,String urlbd, String userbd, String passwordbd) throws IOException{
		PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<KartDTO>espectadores=new ArrayList<>();
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        try {
            String SQL=sqlProp.getProperty("GETALL.KART");
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
            ps=connection.prepareStatement(SQL);
            Statement statement = connection.createStatement();
            rs = ps.executeQuery();
            while(rs.next()) {

                KartDTO p = new KartDTO();
                p.setId_kart(rs.getInt("id_kart"));
                String tipo=rs.getString("tipo");
                if(tipo.compareTo("adultos")==0) {
                	p.setTipo(Tipo_Kart.adultos);
                }else if(tipo.compareTo("children")==0) {
                	p.setTipo(Tipo_Kart.children);

                }
                String estado=rs.getString("estado_kart");
                if(estado.compareTo("disponible")==0) {
                	p.setEstado(Estado_Kart.disponible);
                }else if(estado.compareTo("mantenimiento")==0){
                	p.setEstado(Estado_Kart.mantenimiento);
                }else if(estado.compareTo("reservado")==0) {
                	p.setEstado(Estado_Kart.reservado);
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
	/**
     * Funcion que inserta valores a todos los atributos de la clase Kart
     * @param p
     * @param sqlProp
     * @throws Exception
    */
	public void insertar2(KartDTO p, Properties sqlProp,String urlbd, String userbd, String passwordbd) throws Exception {
		PreparedStatement ps=null;
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        String SQL = sqlProp.getProperty("INSERT.KART");
        try {
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
            ps=connection.prepareStatement(SQL);

            ps.setInt(1, p.getId_kart());
            ps.setString(2,p.isTipo().toString());
            ps.setString(3, p.getEstado().toString());
            ps.setString(4, p.getPista());
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
	public boolean check_pista(String nombre) {
		// TODO Auto-generated method stub
		boolean check=true;
		PistaDTO p= new PistaDTO();
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
				SQL=SQLproperties.getProperty("CHECK.PISTA");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		ps.setString(1, nombre);
		String correo1="";
		ResultSet rs = (ResultSet)ps.executeQuery();
		while(rs.next()) {
			correo1=rs.getString("nombre");
		}
		if(nombre.equals(correo1)) {
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
	public int insertar1(PistaDTO p) {
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
				SQL=SQLproperties.getProperty("INSERT.PISTA");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		 ps.setString(1, p.getNombre());
         ps.setString(2, p.isDisponibilidad().toString());
         ps.setString(3, p.getDificultad().toString());
         ps.setInt(4,p.getNumero_karts());
		devuelto=ps.executeUpdate();

		ps.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return devuelto;
	}
	public boolean check_kart(int id_kart) {
		// TODO Auto-generated method stub
		boolean check=true;
		KartDTO p= new KartDTO();
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
				SQL=SQLproperties.getProperty("CHECK.IDKART");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		ps.setInt(1, id_kart);
		int correo1 = 0;
		ResultSet rs = (ResultSet)ps.executeQuery();
		while(rs.next()) {
			correo1=rs.getInt("id_kart");
		}
		if(id_kart==correo1) {
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
	public boolean check_asociarkart(String pista, String tipo) {
		// TODO Auto-generated method stub
		boolean check=false;
		if(tipo.equals("children")) {
			tipo="infantil";
		}
		ArrayList<PistaDTO>pistas=new ArrayList<PistaDTO>();
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
					SQL=SQLproperties.getProperty("CHECK.PISTKART");
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
			PreparedStatement ps=connection.prepareStatement(SQL);
			ps.setString(1, tipo);
			ResultSet rs = (ResultSet)ps.executeQuery();
			while(rs.next()) {
				PistaDTO p = new PistaDTO();
                p.setNombre(rs.getString("nombre"));
                String dispo=rs.getString("disponibilidad");
                if(dispo.compareTo("disponible")==0) {
                	p.setDisponibilidad(Disponibilidad.disponible);
                }else if(dispo.compareTo("no_disponible")==0) {
                	p.setDisponibilidad(Disponibilidad.no_disponible);

                }
                String dificult=rs.getString("dificultad");
                if(dificult.compareTo("infantil")==0) {
                	p.setDificultad(Dificultad_Pista.infantil);
                }else if(dificult.compareTo("familiar")==0) {
                	p.setDificultad(Dificultad_Pista.familiar);
                }else if(dificult.compareTo("adultos")==0) {
                	p.setDificultad(Dificultad_Pista.adultos);
                }
                p.setNumero_karts(rs.getInt("numero_karts"));
                pistas.add(p);
			}
			for(int i=0;i<pistas.size();i++) {
				if(pista.equals(pistas.get(i).getNombre())){
					check=true;
				}
			}
			
			ps.close();
			rs.close();
			connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return check;	
	}
	public int insertar2(KartDTO p) {
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
				SQL=SQLproperties.getProperty("INSERT.KART");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		 ps.setInt(1, p.getId_kart());
         ps.setString(2,p.isTipo().toString());
         ps.setString(3, p.getEstado().toString());
         ps.setString(4, p.getPista());
		devuelto=ps.executeUpdate();

		ps.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return devuelto;	}
	public int modify_disponibilidad(String nombre, Disponibilidad disponibilidad) {
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
				SQL=SQLproperties.getProperty("UPDATE_ESTADO.PISTA");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
         ps.setString(1, disponibilidad.toString());
		 ps.setString(2,nombre);

         devuelto=ps.executeUpdate();

		ps.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return devuelto;	
		}
	public int update_kart(int id_kart, Estado_Kart estado) {
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
				SQL=SQLproperties.getProperty("UPDATE_ESTADO.KART");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
         ps.setString(1, estado.toString());
		 ps.setInt(2,id_kart);

         devuelto=ps.executeUpdate();

		ps.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return devuelto;	
		}
	public ArrayList<PistaDTO> getAll1() {
		// TODO Auto-generated method stub
		ArrayList<PistaDTO>reservas=new ArrayList<PistaDTO>();
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
					SQL=SQLproperties.getProperty("GETALL.PISTA");
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
				Statement stmt = connection.createStatement();
				ResultSet rs = (ResultSet)stmt.executeQuery(SQL);
				while(rs.next()) {
					PistaDTO p = new PistaDTO();
	                p.setNombre(rs.getString("nombre"));
	                String dispo=rs.getString("disponibilidad");
	                if(dispo.compareTo("disponible")==0) {
	                	p.setDisponibilidad(Disponibilidad.disponible);
	                }else if(dispo.compareTo("no_disponible")==0) {
	                	p.setDisponibilidad(Disponibilidad.no_disponible);

	                }
	                String dificult=rs.getString("dificultad");
	                if(dificult.compareTo("infantil")==0) {
	                	p.setDificultad(Dificultad_Pista.infantil);
	                }else if(dificult.compareTo("familiar")==0) {
	                	p.setDificultad(Dificultad_Pista.familiar);
	                }else if(dificult.compareTo("adultos")==0) {
	                	p.setDificultad(Dificultad_Pista.adultos);
	                }
	                p.setNumero_karts(rs.getInt("numero_karts"));
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
	public ArrayList<PistaDTO> getAll3(String type) {
		// TODO Auto-generated method stub
		ArrayList<PistaDTO>reservas=new ArrayList<PistaDTO>();
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
					SQL=SQLproperties.getProperty("GETALL.PISTA");
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
				Statement stmt = connection.createStatement();
				ResultSet rs = (ResultSet)stmt.executeQuery("SELECT * FROM Pista WHERE dificultad='"+type+"'");
				while(rs.next()) {
					PistaDTO p = new PistaDTO();
	                p.setNombre(rs.getString("nombre"));
	                String dispo=rs.getString("disponibilidad");
	                if(dispo.compareTo("disponible")==0) {
	                	p.setDisponibilidad(Disponibilidad.disponible);
	                }else if(dispo.compareTo("no_disponible")==0) {
	                	p.setDisponibilidad(Disponibilidad.no_disponible);

	                }
	                String dificult=rs.getString("dificultad");
	                if(dificult.compareTo("infantil")==0) {
	                	p.setDificultad(Dificultad_Pista.infantil);
	                }else if(dificult.compareTo("familiar")==0) {
	                	p.setDificultad(Dificultad_Pista.familiar);
	                }else if(dificult.compareTo("adultos")==0) {
	                	p.setDificultad(Dificultad_Pista.adultos);
	                }
	                p.setNumero_karts(rs.getInt("numero_karts"));
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
}
