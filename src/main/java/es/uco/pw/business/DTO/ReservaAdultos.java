package es.uco.pw.business.DTO;

import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo_Bono;
import es.uco.pw.business.ValueObject.Tipo_Reserva;

/**
* Clase que hereda de reserva y representa las reservas realizadas para pistas de adultos
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/

public class ReservaAdultos extends ReservaDTO {

	public ReservaAdultos(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva,
			int descuento, Tipo_Reserva reserva, int num_adultos, int num_children, Tipo_Bono bono) {
		super(id_reserva,id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva,num_adultos,0,bono);
	
	}
	
}
