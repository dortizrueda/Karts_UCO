package es.uco.pw.business.DTO;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo;


/**
* Clase que representa el usuario que entra al sistema para realizar la reserva
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/

public class UsuarioDTO {
	private String Nombre;
	private String Apellidos;
	private Date fecha_inscripcion;
	private Date fecha_nacimiento;
	private String correo;
	private String password;
	private Tipo tipo;
	
    public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	/**
	* Constructor de la clase vacío
	* @param none
	*/
	public UsuarioDTO(){
		
	}
	/**
	* Constructor de la clase
	* @param nombre, apellidos, fecha_inscripcion, fecha_nacimiento, correo, password
	*/
	public UsuarioDTO(String nombre, String apellidos, Date fecha_inscripcion,Date fecha_nacimiento ,String correo,String password, Tipo tipo) {
		super();
		Nombre = nombre;
		Apellidos = apellidos;
		this.fecha_inscripcion = fecha_inscripcion;
		this.fecha_nacimiento=fecha_nacimiento;
		this.correo = correo;
		this.password = password;
		this.tipo = tipo;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public Date getFecha_inscripcion() {
		return fecha_inscripcion;
	}

	public String getCorreo() {
		return correo;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public String getPassword() {
		return password;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public void setFecha_inscripcion(Date fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setPassword(String password) {
		this.password = password;
	}
   
    /**
     * Clase que representa la pista por la que correran
     * @author David Ortiz Rueda
     * @author Teodor Gabriel Propescu
     * @author Javier Romero Ramos
     * @author Alicia Zamora Martín
     * @version 1.0
    */
	public int calcularAntiguedad(Date fecha_inscripcion) {
		Date fecha_actual=new Date(System.currentTimeMillis());
		int milisecondsByDay=86400000;
		int dias = (int) ((fecha_actual.getTime()-fecha_inscripcion.getTime())/milisecondsByDay);
		return dias;
	}
	

	/**
    * Imprime la información del usuario 
	* @param none
	* @return String nombre usuario, String apellidos, Date fecha inscripción, Date fecha nacimiento, String correo usuario, String contraseña sistema
	*/
	@Override

	public String toString() {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    String userInfo =
	    		"Nombre: " +
	    			       this.Nombre+
	    		" " +
	       this.Apellidos+
	      "; Fecha_Registro: " +
	       sdf.format(this.fecha_inscripcion)+
	      "; Rol: " +
	      this.tipo +
	      "; Correo: " +
	      this.correo +
	      "; Antiguedad: "+
	      calcularAntiguedad(fecha_inscripcion)/365 +
	      " años; ";
	    return userInfo;
	  }
	
	
	
}
