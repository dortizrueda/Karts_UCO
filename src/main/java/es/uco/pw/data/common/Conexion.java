package es.uco.pw.data.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
* Clase que establece la conexión con la base de datos
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/

public class Conexion {
	protected static Connection conex=null;
	protected static String urlbd;
	protected static String passwordbd;
	protected static String userbd;
	protected static String driverbd;
	/**
     * Funcion que conecta la E/S de la aplicación a la base de datos.
	 * @param passwordbd 
	 * @param userbd 
	 * @param urlbd 
     * @return Devuelve si se ha conectado correctamente
     * @throws FileNotFoundException
     * @throws IOException
    */
	public static Connection get_Conexion(String urlbd, String userbd, String passwordbd) throws FileNotFoundException, IOException {

        Connection conex=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conex=DriverManager.getConnection(urlbd,userbd,passwordbd);
        }catch(Exception e) {
            System.out.println(e);
        }

        return conex;
		
	}

	public static Connection get_Conexion1() throws SQLException {
		// TODO Auto-generated method stub
		try {
			Context env=(Context)new InitialContext().lookup("java:comp/env");
			urlbd=(String)env.lookup("url");
			userbd=(String)env.lookup("userbd");
			passwordbd=(String)env.lookup("passwordbd");
			driverbd=(String)env.lookup("driverbd");
			
		}catch(Exception a) {
			
		}
		try {
			Class.forName(driverbd);
				conex=(Connection) DriverManager.getConnection(urlbd,userbd,passwordbd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		return conex;
	}
}
