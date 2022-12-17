package es.uco.pw.data.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.DTO.ReservaDTO;
import es.uco.pw.business.DTO.UsuarioDTO;
import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.business.ValueObject.Tipo_Bono;
import es.uco.pw.business.ValueObject.Tipo_Reserva;
import es.uco.pw.business.managers.ReservaManager;
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
public class ReservaDAO {
	/**
     * Funcion que vuelca los valores de Reserva, hacia una lista
     * @param sqlProp
     * @throws IOException
	 * @throws SQLException 
    */
	public ArrayList<ReservaDTO>getAll(Properties sqlProp,String urlbd, String userbd, String passwordbd) throws IOException, SQLException{
		PreparedStatement ps = null;
        ResultSet rs = null;
    	Connection conexion=Conexion.get_Conexion(urlbd,userbd,passwordbd); 
    	UsuarioManager p1 = new UsuarioManager();
    	sqlProp=UsuarioManager.getSqlProperties();
       	 String SQL=sqlProp.getProperty("GETALL.RESERVA");
    		 ps=conexion.prepareStatement(SQL);
            rs = ps.executeQuery();
            ArrayList<ReservaDTO>espectadores=new ArrayList<>();
        try {
            Statement statement = conexion.createStatement();
            while(rs.next()) {

                ReservaDTO p = new ReservaDTO();
                p.setId_usuario(rs.getString("Usuario"));
                String fecha_hora=rs.getString("Fecha_hora");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date fech_hora=sdf.parse(fecha_hora);
                p.setFecha_hora(fech_hora);
                p.setMinutos(rs.getInt("Minutos"));
                p.setId_pista(rs.getString("Id_pista"));
                p.setPrecio_reserva(rs.getFloat("Precio_reserva"));
                p.setDescuento(rs.getInt("Descuento"));
                String reserva= rs.getString("Tipo_Reserva");
                if(reserva.compareTo("infantil")==0) {
                	p.setReserva(Tipo_Reserva.infantil);
                }else if(reserva.compareTo("familiar")==0) {
                	p.setReserva(Tipo_Reserva.familiar);
                }else if(reserva.compareTo("adultos")==0) {
                	p.setReserva(Tipo_Reserva.adultos);
                }
                p.setNum_adults(rs.getInt("num_adultos"));
                p.setNum_children(rs.getInt("num_children"));

                espectadores.add(p);

            }
            if(statement!=null) statement.close();
            return espectadores;
        }catch(Exception e) {
            System.out.println(e);
            }
        return null;		
	}
	public ArrayList<ReservaDTO>getAll1(){
		ArrayList<ReservaDTO>reservas=new ArrayList<ReservaDTO>();
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
					SQL=SQLproperties.getProperty("GETALL.RESERVA");
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				}catch(IOException e) {
					e.printStackTrace();
				}
				Statement stmt = connection.createStatement();
				ResultSet rs = (ResultSet)stmt.executeQuery(SQL);
				while(rs.next()) {
					 ReservaDTO p = new ReservaDTO();
					 	p.setId_reserva(rs.getInt("id_reserva"));
		                p.setId_usuario(rs.getString("Usuario"));
		                String fecha_hora=rs.getString("Fecha_hora");
		                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		                Date fech_hora=sdf.parse(fecha_hora);
		                p.setFecha_hora(fech_hora);
		                p.setMinutos(rs.getInt("Minutos"));
		                p.setId_pista(rs.getString("Id_pista"));
		                p.setPrecio_reserva(rs.getFloat("Precio_reserva"));
		                p.setDescuento(rs.getInt("Descuento"));
		                String reserva= rs.getString("Tipo_Reserva");
		                if(reserva.compareTo("infantil")==0) {
		                	p.setReserva(Tipo_Reserva.infantil);
		                }else if(reserva.compareTo("familiar")==0) {
		                	p.setReserva(Tipo_Reserva.familiar);
		                }else if(reserva.compareTo("adultos")==0) {
		                	p.setReserva(Tipo_Reserva.adultos);
		                }
		                p.setNum_adults(rs.getInt("num_adultos"));
		                p.setNum_children(rs.getInt("num_children"));
		                String bono=rs.getString("bono");
		                if(bono.compareTo("bono")==0) {
			                p.setBono(Tipo_Bono.bono);
		                }else {
			                p.setBono(Tipo_Bono.individual);		                	
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
	public ReservaDTO getFecha_Recent(Properties sqlProp,String urlbd, String userbd, String passwordbd,String correo) throws IOException, SQLException, ParseException{
		PreparedStatement ps = null;
        ResultSet rs = null;
    	Connection conexion=Conexion.get_Conexion(urlbd,userbd,passwordbd); 
    	UsuarioManager p1 = new UsuarioManager();
    	sqlProp=UsuarioManager.getSqlProperties();
       	 String SQL=sqlProp.getProperty("SELECT.FECHA");
    		ps=conexion.prepareStatement("SELECT * FROM Reserva WHERE Fecha_hora = ( SELECT MIN(Fecha_hora) FROM Reserva WHERE Usuario = '"+correo+"')");
            rs = ps.executeQuery();
            ReservaDTO p = new ReservaDTO();
            Statement statement = conexion.createStatement();
            if(rs.next()==false) {
            	rs.close();
            	conexion.close();
            	ps.close();  
            }else {
            	p.setId_usuario(rs.getString("Usuario"));
                String fecha_hora=rs.getString("Fecha_hora");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date fech_hora=sdf.parse(fecha_hora);
                p.setFecha_hora(fech_hora);
                p.setMinutos(rs.getInt("Minutos"));
                p.setId_pista(rs.getString("Id_pista"));
                p.setPrecio_reserva(rs.getFloat("Precio_reserva"));
                p.setDescuento(rs.getInt("Descuento"));
                String reserva= rs.getString("Tipo_Reserva");
                if(reserva.compareTo("infantil")==0) {
                	p.setReserva(Tipo_Reserva.infantil);
                }else if(reserva.compareTo("familiar")==0) {
                	p.setReserva(Tipo_Reserva.familiar);
                }else if(reserva.compareTo("adultos")==0) {
                	p.setReserva(Tipo_Reserva.adultos);
                }
                p.setNum_adults(rs.getInt("num_adultos"));
                p.setNum_children(rs.getInt("num_children"));
                
                rs.close();
            	conexion.close();
            	ps.close();
                return p;		

            }
			return p;
	}
	public ReservaDTO getFecha_Recent1(String correo) throws IOException, SQLException, ParseException{
		int devuelto=0;
        ReservaDTO p = new ReservaDTO();

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
				SQL=SQLproperties.getProperty("INSERT.RESERVA");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement("SELECT * FROM Reserva WHERE Fecha_hora = ( SELECT MIN(Fecha_hora) FROM Reserva WHERE Usuario = '"+correo+"')");
		Statement stmt = connection.createStatement();
		ResultSet rs = (ResultSet)stmt.executeQuery("SELECT * FROM Reserva WHERE Fecha_hora = ( SELECT MIN(Fecha_hora) FROM Reserva WHERE Usuario = '"+correo+"')");
		if(rs.next()==false) {
			
		}else {
			p.setId_usuario(rs.getString("Usuario"));
	        String fecha_hora=rs.getString("Fecha_hora");
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        Date fech_hora=sdf.parse(fecha_hora);
	        p.setFecha_hora(fech_hora);
	        p.setMinutos(rs.getInt("Minutos"));
	        p.setId_pista(rs.getString("Id_pista"));
	        p.setPrecio_reserva(rs.getFloat("Precio_reserva"));
	        p.setDescuento(rs.getInt("Descuento"));
	        String reserva= rs.getString("Tipo_Reserva");
	        if(reserva.compareTo("infantil")==0) {
	        	p.setReserva(Tipo_Reserva.infantil);
	        }else if(reserva.compareTo("familiar")==0) {
	        	p.setReserva(Tipo_Reserva.familiar);
	        }else if(reserva.compareTo("adultos")==0) {
	        	p.setReserva(Tipo_Reserva.adultos);
	        }
	        p.setNum_adults(rs.getInt("num_adultos"));
	        p.setNum_children(rs.getInt("num_children"));
	        
	        

			ps.close();
			connection.close();
			return p;
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
			return p;
	}
	/**
     * Función que inserta valores a todos los atributos de la clase Reserva
     * @param p
     * @param sqlProp
     * @throws Exception
    */
	public void insertar(ReservaDTO p, Properties sqlProp, String urlbd, String userbd, String passwordbd) throws Exception {
		PreparedStatement ps=null;
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        String SQL = sqlProp.getProperty("INSERT.RESERVA");
        try {
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
            ps=connection.prepareStatement(SQL);

            ps.setString(1, p.getId_usuario());
            ps.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getFecha_hora()));
            ps.setInt(3, p.getMinutos());
            ps.setString(4, p.getId_pista());
            ps.setFloat(5, p.getPrecio_reserva());
            ps.setInt(6, p.getDescuento());
            ps.setString(7, p.getReserva().toString());
            ps.setInt(8,p.getNum_adults());
            ps.setInt(9, p.getNum_children());

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
     * Funcion que elimina los valores de los atributos de la clase Reserva por el usuario
     * @param fecha_hora
     * @param Titulo
     * @param sqlProp
     * @throws Exception
    */
	public void eliminar(Date fecha_hora, String Titulo, Properties sqlProp, String urlbd, String userbd, String passwordbd) throws Exception {
    	PreparedStatement ps=null;
    	sqlProp=new Properties();
		String ruta="sql.properties";
		
		FileInputStream proper = new FileInputStream(ruta);
		sqlProp.load(proper);
		String SQL=sqlProp.getProperty("DELETE.RESERVA");

    	try {
     		Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
     		ps=connection.prepareStatement(SQL);
     		
     		ps.setString(1, Titulo);
     		
     		ps.executeUpdate();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	/**
     * Clase que modifica los valores de los datos de la clase Reserva segun la pista y fecha por el usuario
     * @param user1
     * @param pista
     * @param fecha
     * @param sqlProp
     * @throws Exception
    */
	public void modificarPista(String user1, String pista,String fecha, Properties sqlProp, String urlbd, String userbd, String passwordbd) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        try {
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
            String SQL=sqlProp.getProperty("UPDATE.PISTA");
            ps=connection.prepareStatement(SQL);

            ps.setString(1, pista);
            ps.setString(2, fecha);
            ps.setString(3, user1);
            
            
            if(ps.executeUpdate()==0) {
                System.out.println("Es posible que no se haya guardado nada");
            }
            ps.close();

        }catch(Exception ex) {
            throw new Exception("Error en SQL",ex);
        }
	}
	/**
     * Clase que modifica los valores de los datos de la clase Reserva segun los minutos y fecha por el usuario
     * @param user1
     * @param minutos
     * @param fecha
     * @param sqlProp
     * @throws Exception
    */
	public void modificar_minutos(String user1, int minutos, String fecha, Properties sqlProp, String urlbd, String userbd, String passwordbd) throws Exception {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
        sqlProp=new Properties();
        String ruta="sql.properties";

        FileInputStream proper = new FileInputStream(ruta);
        sqlProp.load(proper);
        try {
            Connection connection=Conexion.get_Conexion(urlbd,userbd,passwordbd);
            String SQL=sqlProp.getProperty("UPDATE.MINUTOS");
            ps=connection.prepareStatement(SQL);

            ps.setInt(1, minutos);
            ps.setString(2, fecha);
            ps.setString(3, user1);
            
            
            if(ps.executeUpdate()==0) {
                System.out.println("Es posible que no se haya guardado nada");
            }
            ps.close();

        }catch(Exception ex) {
            throw new Exception("Error en SQL",ex);
        }
	}
	public int delete(int id_reserva) {
		// TODO Auto-generated method stub
		int devuelto = 0;
		
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
						SQL=SQLproperties.getProperty("DELETE.RESERVA");
					}catch(FileNotFoundException e) {
						e.printStackTrace();
					}catch(IOException e) {
						e.printStackTrace();
					}
				PreparedStatement ps=connection.prepareStatement(SQL);
				 
		         ps.setInt(1,id_reserva);
		         


				devuelto=ps.executeUpdate();

				ps.close();
				connection.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				return devuelto;
		}
	public int insertar1(ReservaDTO p) {
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
				SQL=SQLproperties.getProperty("INSERT.RESERVA");
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		PreparedStatement ps=connection.prepareStatement(SQL);
		 ps.setString(1, p.getId_usuario());
         ps.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getFecha_hora()));
         ps.setInt(3, p.getMinutos());
         ps.setString(4, p.getId_pista());
         ps.setFloat(5, p.getPrecio_reserva());
         ps.setInt(6, p.getDescuento());
         ps.setString(7, p.getReserva().toString());
         ps.setInt(8,p.getNum_adults());
         ps.setInt(9, p.getNum_children());
         ps.setString(10, p.getBono().toString());
		devuelto=ps.executeUpdate();

		ps.close();
		connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return devuelto;	
	}
	}

