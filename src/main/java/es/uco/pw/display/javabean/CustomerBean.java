package es.uco.pw.display.javabean;

import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo;
public class CustomerBean {
	private String Nombre;
	private String Apellidos;
	private Date fecha_inscripcion;
	private Date fecha_nacimiento;
	private String correo="";
	private String password;
	private Tipo tipo;
	public String getNombre() {
		return Nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public Date getFecha_inscripcion() {
		return fecha_inscripcion;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public String getCorreo() {
		return correo;
	}
	public String getPassword() {
		return password;
	}
	public Tipo getTipo() {
		return tipo;
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
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}
