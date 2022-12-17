package es.uco.pw.business.DTO;

import java.util.Date;

/**
* Clase que representa el bono de reserva que pueden adquirir los clientes
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/

public class BonoDTO {
	private int id_bono;
	private Date fecha_reserva;
	public BonoDTO() {
		
	}
	/**
	* Constructor de la clase
	* @param id_bono, fecha_reserva
	*/

	public BonoDTO(int id_bono, Date fecha_reserva) {
		super();
		this.id_bono = id_bono;
		this.fecha_reserva = fecha_reserva;
	}

	/**
	* Devuelve el identificador del bono
	* @param none
	* @return int identificador del bono
	*/

	public int getId_bono() {
		return id_bono;
	}
	/**
	* Devuelve la fecha de reserva
	* @param none
	* @return Date fecha de la reserva
	*/

	public Date getFecha_reserva() {
		return fecha_reserva;
	}
	/**
    * Modifica el identificador del bono
	* @param int Nuevo identificador bono
	* @return none
	*/
	public void setId_bono(int id_bono) {
		this.id_bono = id_bono;
	}

	/**
    * Modifica la fecha de reserva asociada al bono
	* @param Date nueva fecha de la reserva
	* @return none
	*/

	public void setFecha_reserva(Date fecha_reserva) {
		this.fecha_reserva = fecha_reserva;
	}
	
}
