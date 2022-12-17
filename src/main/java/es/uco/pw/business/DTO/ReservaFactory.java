package es.uco.pw.business.DTO;

import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo_Bono;
import es.uco.pw.business.ValueObject.Tipo_Reserva;
/**
 * Clase que deriva de la clase abstracta AbstractReserva y que usa sus atributos para crear una reserva familiar, de adultos o infantil
 * @author David Ortiz Rueda
 * @author Teodor Gabriel Propescu
 * @author Javier Romero Ramos
 * @author Alicia Zamora Martín
 */

public class ReservaFactory  {

	public ReservaAdultos crear_reserva1(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva,
			int descuento, Tipo_Reserva reserva, int num_adultos, int num_children,Tipo_Bono bono) {
		// TODO Auto-generated method stub
		ReservaAdultos p = new ReservaAdultos(id_reserva,id_usuario,fecha_hora,minutos,id_pista,precio_reserva,descuento,reserva,num_adultos,num_children,bono);
		return p;
	}

	
	public ReservaInfantil crear_reserva2(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista,
			float precio_reserva, int descuento, Tipo_Reserva reserva, int num_adultos,int num_children,Tipo_Bono bono) {
		// TODO Auto-generated method stub
		ReservaInfantil p = new ReservaInfantil(id_reserva,id_usuario,fecha_hora,minutos,id_pista,precio_reserva,descuento,reserva,num_adultos,num_children,bono);
		return p;
	}

	
	public ReservaFamiliar crear_reserva3(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista,
			float precio_reserva, int descuento, Tipo_Reserva reserva, int num_adultos, int num_children, Tipo_Bono bono) {
		// TODO Auto-generated method stub
		ReservaFamiliar p = new ReservaFamiliar(id_reserva,id_usuario,fecha_hora,minutos,id_pista,precio_reserva,descuento,reserva,num_adultos,num_children,bono);
		return p;
	}

}
