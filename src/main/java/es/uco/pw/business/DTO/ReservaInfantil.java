package es.uco.pw.business.DTO;

import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo_Bono;
import es.uco.pw.business.ValueObject.Tipo_Reserva;

/**
* Clase que hereda de reserva y representa las reservas realizadas para pistas de car�cter infantil
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Mart�n
* @version 1.0
*/

public class ReservaInfantil extends ReservaDTO{
	
	public ReservaInfantil(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva,
			int descuento, Tipo_Reserva reserva,int num_adultos,int num_children,Tipo_Bono bono) {
		super(id_reserva,id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva,0,num_children,bono);
		// TODO Auto-generated constructor stub
	}

	
	
}
