package es.uco.pw.business.DTO;

import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo_Bono;
import es.uco.pw.business.ValueObject.Tipo_Reserva;

/**
 * Clase abstracta reserva que reune los atributos de los que heredaran reserva familiar,reserva infantil y reserva adultos
 * @author David Ortiz Rueda
 * @author Teodor Gabriel Propescu
 * @author Javier Romero Ramos
 * @author Alicia Zamora Martín
 */
public abstract class IndividualBono_Creator extends ReservaCreator {

	
	public ReservaAdultos crear_individual(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children,Tipo_Bono bono) {
		ReservaAdultos p = new ReservaAdultos(id_reserva,id_usuario,fecha_hora,minutos,id_pista,precio_reserva,descuento,reserva,num_adultos,num_children,bono);
		return p;
	}

	public ReservaAdultos crear_bono(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children,Tipo_Bono bono) {
		// TODO Auto-generated method stub
		ReservaAdultos p = new ReservaAdultos(id_reserva,id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva,num_adultos,num_children,bono);
		return p;
	}
	public ReservaInfantil crear_individual1(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children,Tipo_Bono bono) {
		ReservaInfantil p = new ReservaInfantil(id_reserva,id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva,num_adultos,num_children,bono);
		return p;
		
	}
	public ReservaInfantil crear_bono1(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children,Tipo_Bono bono) {
		ReservaInfantil p = new ReservaInfantil(id_reserva,id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva,num_adultos,num_children,bono);
		return p;
		
	}
	public ReservaFamiliar crear_individual2(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children,Tipo_Bono bono) {
		ReservaFamiliar p = new ReservaFamiliar(id_reserva,id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva,num_adultos,num_children,bono);
		return p;
		
	}
	public ReservaFamiliar crear_bono2(int id_reserva,String id_usuario, Date fecha_hora, int minutos, String id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children,Tipo_Bono bono) {
		ReservaFamiliar p = new ReservaFamiliar(id_reserva,id_usuario, fecha_hora, minutos, id_pista, precio_reserva, descuento, reserva,num_adultos,num_children,bono);
		return p;
		
	}

}
