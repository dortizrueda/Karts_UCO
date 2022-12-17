package es.uco.pw.business.DTO;

import java.util.Date;

import es.uco.pw.business.ValueObject.Tipo_Reserva;
/**
 * Clase abstracta creadora de tipos de reserva de la que se heredará
 * @author David Ortiz Rueda
 * @author Teodor Gabriel Propescu
 * @author Javier Romero Ramos
 * @author Alicia Zamora Martín
 */
public abstract class ReservaCreator {

	public abstract ReservaAdultos crear_individual(int id_usuario, Date fecha_hora, int minutos, int id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children);
	public abstract ReservaAdultos crear_bono(int id_usuario, Date fecha_hora, int minutos, int id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children);
	
	public abstract ReservaInfantil crear_individual1(int id_usuario, Date fecha_hora, int minutos, int id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children);
	public abstract ReservaInfantil crear_bono1(int id_usuario, Date fecha_hora, int minutos, int id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children);
	
	public abstract ReservaFamiliar crear_individual2(int id_usuario, Date fecha_hora, int minutos, int id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children);
	public abstract ReservaFamiliar crear_bono2(int id_usuario, Date fecha_hora, int minutos, int id_pista, float precio_reserva, int descuento, Tipo_Reserva reserva,int num_adultos,int num_children);
}
