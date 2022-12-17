package es.uco.pw.business.DTO;

import es.uco.pw.business.ValueObject.Estado_Kart;
import es.uco.pw.business.ValueObject.Tipo_Kart;

/**
* Clase que representa el vehículo que utilizan los usuarios para recorrer las pistas
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/

public class KartDTO {
	
	private int id_kart;
	private Tipo_Kart tipo;
	private Estado_Kart estado;
	private String pista;

/**
	* Constructor de la clase
	* @param id_kart, tipo, estado
	*/
	
	public KartDTO(int id_kart, Tipo_Kart tipo, Estado_Kart estado, String pista) {
		super();
		this.id_kart = id_kart;
		this.tipo = tipo;
		this.estado = estado;
		this.pista=pista;
	}

/**
	* Constructor de la clase vacío
	* @param none
	*/
	
	public KartDTO() {
		
	}

	/**
	* Devuelve el identificador del kart
	* @param none
	* @return int identificador del kart
	*/

	
	public int getId_kart() {
		return id_kart;
	}

	/**
	* Devuelve el tipo del kart
	* @param none
	* @return Tipo_Kart tipo del kart
	*/

	public Tipo_Kart isTipo() {
		return tipo;
	}

	/**
	* Devuelve el estado del kart
	* @param none
	* @return Estado_Kart identificador del kart
	*/

	public Estado_Kart getEstado() {
		return estado;
	}

	/**
    * Modifica el identificador del kart
	* @param int nuevo identificador kart
	* @return none
	*/
	
	public String getPista() {
		return pista;
	}
	public void setPista(String pista) {
		this.pista = pista;
	}
	
	public void setId_kart(int id_kart) {
		this.id_kart = id_kart;
	}

	/**
    * Modifica el tipo del kart
	* @param Tipo_Kart nuevo tipo del kart
	* @return none
	*/

	public void setTipo(Tipo_Kart tipo) {
		this.tipo = tipo;
	}

	/**
    * Modifica el estado del kart
	* @param Estado_Kart nuevo estado del kart
	* @return none
	*/

	public void setEstado(Estado_Kart estado) {
		this.estado = estado;
	}
	
	/**
    * Imprime la información del kart
	* @param none
	* @return int identificador del kart, Estado_Kart estado del kart, Tipo_Kart tipo del kart
	*/
	@Override
	public String toString() {
		return "Kart [id_kart=" + id_kart + ", tipo=" + tipo + ", estado=" + estado + "]";
	}
}
