package es.uco.pw.business.DTO;

import java.util.ArrayList;
import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo_Bono;
import es.uco.pw.business.ValueObject.Tipo_Reserva;

/**
* Clase que representa las reservas que se realizan sobre las pistas
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/

public class ReservaDTO {
	private int id_reserva;
	private String Id_usuario;
	private Date fecha_hora;
	private int minutos;
	private String id_pista;
	private float precio_reserva;
	private int descuento;
	private Tipo_Reserva reserva;
	private int num_adults;
	private int num_children;
	private Tipo_Bono bono;
	private ArrayList<BonoDTO>lista_bono;

	/**
	* Constructor de la clase vacío
	* @param none
	*/
	public ReservaDTO() {
		
	}
	/**
	* Constructor de la clase Reserva
	* @param id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva, num_adults, num_children
	*/
	public ReservaDTO(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva, int descuento,
			Tipo_Reserva reserva,int num_adults, int num_children, Tipo_Bono bono) {
		super();
		this.id_reserva=id_reserva;
		Id_usuario = id_usuario;
		this.fecha_hora = fecha_hora;
		this.minutos = minutos;
		this.id_pista = id_pista;
		this.precio_reserva = precio_reserva;
		this.descuento = descuento;
		this.reserva = reserva;
		this.num_adults=num_adults;
		this.num_children=num_children;
		this.bono=bono;
	}

    /**
	* Devuelve el identificador del usuario que reserva
	* @param none
	* @return String identificador del usuario
	*/
	public String getId_usuario() {
		return Id_usuario;
	}

	/**
	* Devuelve la fecha y hora de la reserva
	* @param none
	* @return Date fecha y hora reserva
	*/

	public Date getFecha_hora() {
		return fecha_hora;
	}
    /**
	* Devuelve la duración de la reserva en minutos
	* @param none
	* @return int duración de la reserva
	*/
	public int getMinutos() {
		return minutos;
	}

	/**
	* Devuelve el identificador de la pista reseervada
	* @param none
	* @return String identificador pista reserva
	*/

	public String getId_pista() {
		return id_pista;
	}
    /**
	* Devuelve el precio de la reserva
	* @param none
	* @return float precio de la reserva
	*/
	public float getPrecio_reserva() {
		return precio_reserva;
	}
    /**
	* Devuelve el descuento que se le ha aplicado a la reserva
	* @param none
	* @return int descuento de la reserva
	*/
	public int getDescuento() {
		return descuento;
	}
    /**
	* Devuelve el tipo de reserva que se ha realizado, infantil,familiar,adultos
	* @param none
	* @return Tipo_Reserva tipo de reserva efectuada
	*/
	public Tipo_Reserva getReserva() {
		return reserva;
	}
    /**
	* Devuelve el número de sesión del bono
	* @param none
	* @return ArrayList<Bono> sesión del bono
	*/
	public ArrayList<BonoDTO> getLista_bono() {
		return lista_bono;
	}
    /**
    * Modifica la sesión del bono 
	* @param ArrayList<bono> nueva sesión del bono
	* @return none
	*/
	public void setLista_bono(ArrayList<BonoDTO> lista_bono) {
		this.lista_bono = lista_bono;
	}
     /**
    * Modifica el identificador del usuario
	* @param int nuevo identificador del usuario asociado a la reserva
	* @return none
	*/
	public void setId_usuario(String id_usuario) {
		Id_usuario = id_usuario;
	}
     /**
    * Modifica la fecha y hora de la reserva realizada
	* @param Date nueva fecha y hora de la reserva
	* @return none
	*/
	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
    /**
    * Modifica la duración de la reserva
	* @param int nueva duración de la reserva
	* @return none
	*/
	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
    /**
    * Modifica el identificador de la pista
	* @param int nuevo identificador de la pista
	* @return none
	*/
	public void setId_pista(String id_pista) {
		this.id_pista = id_pista;
	}
    /**
    * Modifica el precio de la reserva
	* @param int nuevo precio de la reserva
	* @return none
	*/
	public void setPrecio_reserva(float precio_reserva) {
		this.precio_reserva = precio_reserva;
	}
    /**
    * Modifica el descuento aplicado en la reserva
	* @param int nuevo descuento aplicado
	* @return none
	*/
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
    /**
    * Modifica el tipo de reserva efectuada
	* @param Tipo_Reserva nuevo tipo de la reserva realizada
	* @return none
	*/
	public void setReserva(Tipo_Reserva reserva) {
		this.reserva = reserva;
	}
	/**
    * Devuelve el número de adultos en la reserva
	* @param none
	* @return int número de adultos en la reserva
	*/
	public int getNum_adults() {
		return num_adults;
	}
    /**
    * Modifica el número de adultos en la reserva
	* @param int nuevo número de adultos en la reserva
	* @return none
	*/
	public void setNum_adults(int num_adults) {
		this.num_adults = num_adults;
	}
	/**
    * Devuelve el número de niños en la reserva
	* @param none
	* @return int número de niños en la reserva
	*/
	public int getNum_children() {
		return num_children;
	}
	/**
    * Modifica el número de niños en la reserva
	* @param int nuevo número de niños en la reserva
	* @return none
	*/

	public void setNum_children(int num_children) {
		this.num_children=num_children;
	}
	public int getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	/**
    * Imprime la información de la reserva
	* @param none
	* @return int id del usuario, Date fecha y hora de la reserva, int duración de la reserva, int identificador de la pista, float precio de la reserva, int descuento de la reserva
	*/
	@Override
	public String toString() {
		return "Reserva [Id_usuario=" + Id_usuario + ", fecha_hora=" + fecha_hora + ", minutos=" + minutos
				+ ", id_pista=" + id_pista + ", precio_reserva=" + precio_reserva + ", descuento=" + descuento
				+ ", reserva=" + reserva + "]";
	}
	public Tipo_Bono getBono() {
		return bono;
	}
	public void setBono(Tipo_Bono bono) {
		this.bono = bono;
	}

	
}
