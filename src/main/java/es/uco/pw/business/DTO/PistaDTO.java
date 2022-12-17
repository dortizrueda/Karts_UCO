package es.uco.pw.business.DTO;

import java.util.ArrayList;

import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.business.ValueObject.Estado_Kart;
import es.uco.pw.business.ValueObject.Tipo_Kart;

/**
* Clase que representa la pista por la que correran
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/

public class PistaDTO {
	
	private String nombre;
	private Disponibilidad disponibilidad;
	private Dificultad_Pista dificultad;
	private int numero_karts;
	protected ArrayList<KartDTO>list_karts=new ArrayList<KartDTO>();
	protected ArrayList<KartDTO>list_karts_adulto=new ArrayList<KartDTO>();
	protected ArrayList<KartDTO>list_karts_children=new ArrayList<KartDTO>();


	/**
	* Constructor de la clase
	* @param nombre, dispo, dificult, numero_karts2
	*/
	public PistaDTO(String nombre, Disponibilidad dispo, Dificultad_Pista dificult, int numero_karts2) {
		super();
		this.nombre = nombre;
		this.disponibilidad = dispo;
		this.dificultad =dificult;
		this.numero_karts = numero_karts2;
	}
	
	/**
	* Constructor de la clase incluyendo el fichero de karts 
	* @param nombre, dispo, dificult, numero_karts2, list_karts
	*/
	public PistaDTO(String nombre, Disponibilidad disponibilidad, Dificultad_Pista dificultad, int numero_karts,
			ArrayList<KartDTO> list_karts) {
		super();
		this.nombre = nombre;
		this.disponibilidad = disponibilidad;
		this.dificultad = dificultad;
		this.numero_karts = numero_karts;
		this.list_karts = list_karts;
	}

   /**
	* Constructor de la clase vacío
	* @param none
	*/

	public PistaDTO() {
		
	}

    /**
	* Devuelve el nombre de la pista
	* @param none
	* @return String nombre de la pista
	*/
	public String getNombre() {
		return nombre;
	}
	/**
	* Devuelve la disponibilidad de la pista seleccionada
	* @param none
	* @return Disponibilidad devuelve cómo se encuentra la pista
	*/
	public Disponibilidad isDisponibilidad() {
		return disponibilidad;
	}

	/**
	* Devuelve la dificultad de la pista
	* @param none
	* @return Dificultad_Pista  dificultad de la pista
	*/

	public Dificultad_Pista getDificultad() {
		return dificultad;
	}
	/**
	* Devuelve el número máximo de karts autorizados a usar la pista a la vez
	* @param none
	* @return int número de karts autorizados
	*/


	public int getNumero_karts() {
		return numero_karts;
	}

	/**
	* Devuelve los karts que hay en el sistema 
	* @param none
	* @return ArrayList<Kart> karts que se encuentran en el sistema 
	*/

	public ArrayList<KartDTO> getlist_karts() {
		return list_karts;
	}

	/**
    * Modifica el nombre de la pista
	* @param String nuevo nombre de la pista
	* @return none
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
    * Modifica la disponibilidad de la pista
	* @param Disponibilidad nueva disponibilidad de la pista
	* @return none
	*/
	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	/**
    * Modifica la dificultad de la pista
	* @param Dificultad_Pista nueva dificultad de la pista
	* @return none
	*/

	public void setDificultad(Dificultad_Pista dificultad) {
		this.dificultad = dificultad;
	}
	/**
    * Modifica el número de karts autorizados a correr en pista 
	* @param int nuevo número de karts autorizados
	* @return none
	*/
	public void setNumero_karts(int numero_karts) {
		this.numero_karts = numero_karts;
	}
	/**
    * Modifica la lista de karts que se encuentran registrados
	* @param ArrayList<Kart> nueva lista de karts
	* @return none
	*/
	public void setlist_karts(ArrayList<KartDTO> list_karts) {
		this.list_karts = list_karts;
	}
	
	/**
    * Se encarga de devolver una lista de karts que no estén en mantenimiento ni reservados
	* @param none
	* @return ArrayList<Kart> lista con los karts que se encuentran disponibles para ser utilizados
	*/
	public ArrayList<KartDTO> consultarKartsDisponibles()
	{
		ArrayList<KartDTO>disponibles = new ArrayList<>();
		for(int k=0;k<list_karts.size();k++) 	
		{
			if(list_karts.get(k).getEstado().equals(Estado_Kart.disponible)) {
				disponibles.add(list_karts.get(k));
			}
		}
		return disponibles;
		
	}

	/**
    * Se encarga de añadir karts del tipo adecuado para la categoría de pista seleccionada
	* @param kart
	* @return none
	*/

	public void asociarKarts(KartDTO kart) {
		if(getDificultad()==Dificultad_Pista.familiar){
			list_karts.add(kart);
		}
		if(getDificultad()==Dificultad_Pista.adultos) {
			if(kart.isTipo().equals(Tipo_Kart.adultos)) {
				list_karts_adulto.add(kart);

			}
		}
		if(getDificultad()==Dificultad_Pista.infantil) {
			if(kart.isTipo().equals(Tipo_Kart.children)) {
				list_karts_children.add(kart);

			}
		}
	}

	/**
    * Imprime la información de la pista
	* @param none
	* @return String nombre de la pista, Disponibilidad disponibilidad de la pista, Dificultad_Pista dificultad de la pista, int número de karts autorizados
	*/

	public String toString() {
		return "Pista [nombre=" + nombre + ", disponibilidad=" + disponibilidad + ", dificultad=" + dificultad
				+ ", numero_karts=" + numero_karts + "]";
	}
	
}
