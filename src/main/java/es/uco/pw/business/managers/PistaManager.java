package es.uco.pw.business.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import es.uco.pw.business.DTO.KartDTO;
import es.uco.pw.business.DTO.PistaDTO;
import es.uco.pw.business.ValueObject.Dificultad_Pista;
import es.uco.pw.business.ValueObject.Disponibilidad;
import es.uco.pw.business.ValueObject.Estado_Kart;
import es.uco.pw.business.ValueObject.Tipo_Kart;
import es.uco.pw.data.DAO.PistaDAO;
import es.uco.pw.data.DAO.UsuarioDAO;

/**
* Clase creada para realizar las operaciones con la base de datos
* @author David Ortiz Rueda
* @author Teodor Gabriel Propescu
* @author Javier Romero Ramos
* @author Alicia Zamora Martín
* @version 1.0
*/
public class PistaManager {
private static PistaManager instance = null;
	
	private static ArrayList<PistaDTO>list_pistas = new ArrayList<PistaDTO>();
	private static ArrayList<KartDTO>lista_karts = new ArrayList<KartDTO>();
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sin = new Scanner(System.in);
	private static Properties prop;
	static PistaDAO msu = new PistaDAO();

	PistaManager() {
		
	}
	
	public static PistaManager getInstance() {
		// TODO Auto-generated method stub
		if(instance==null) {
			instance=new PistaManager();
		}
		return instance;
	}
	/**
     * Clase que se encarga de crear una pista y asignarle todos sus atributos, entre los que se encuentra la dificultad de la pista
     * paragm none
	 * @throws Exception 
    */

	public static void crearPista() throws Exception {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
        PistaDTO p;
        p= new PistaDTO();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nombre;
        String dispo;
        Disponibilidad disponibilidad;
        String dificultad;
        int numero_karts;
        ArrayList<KartDTO>list_karts;
     
		System.out.println("Introduzca el nombre: ");
		nombre = reader.readLine();
        p.setNombre(nombre);
		System.out.println("Introduzca la disponibilidad de la pista(disponible/no_disponible): ");
		dispo = teclado.nextLine();
		if(dispo.compareTo("disponible")==0) {
	        p.setDisponibilidad(Disponibilidad.disponible);
		}else if(dispo.compareTo("no_disponible")==0) {
	        p.setDisponibilidad(Disponibilidad.no_disponible);
		}
		System.out.println("Introduzca la dificultad de la pista: ");
		dificultad = teclado.nextLine();
		if(dificultad.compareTo("infantil")==0) {
	        p.setDificultad(Dificultad_Pista.infantil);
		}else if(dificultad.compareTo("adultos")==0) {
	        p.setDificultad(Dificultad_Pista.adultos);
		}else if(dificultad.compareTo("familiar")==0) {
	        p.setDificultad(Dificultad_Pista.familiar);
		}
		System.out.println("Introduzca el numero de karts: ");
		numero_karts = teclado.nextInt();
        p.setNumero_karts(numero_karts);
        
        

        list_pistas.add(p);
//        msu.insertar(p, prop);
	}
	 /**
     * Clase que se encarga de mostar el subconjunto de pistas disponibles
     * @throws FileNotFoundException
	  * @throws IOException
	  * */
	public static void cargar_fichero() throws FileNotFoundException, IOException {
		
		String dificultad,nombre,disponibilidad;
		Disponibilidad dispo = null;
		int numero_karts;
		Dificultad_Pista dificult=null;
		
		ArrayList<String>ficheros=new ArrayList<String>();
		ficheros=Properties_Fich();
		File fich_pistas;
		fich_pistas=new File(ficheros.get(1));
		if(fich_pistas.exists()) {
		try {
			FileReader lector = new FileReader(ficheros.get(1));
			BufferedReader BR = new BufferedReader(lector);
			
			while((nombre=BR.readLine())!=null) {
				disponibilidad=BR.readLine();
				if(disponibilidad.compareTo("disponible")==0) {
					dispo=Disponibilidad.disponible;
				}else if(disponibilidad.compareTo("no_disponible")==0) {
					dispo=Disponibilidad.no_disponible;
				}else {
					dispo=Disponibilidad.no_disponible;
				}
				dificultad=BR.readLine();
				if(dificultad.compareTo("infantil")==0){
					dificult=Dificultad_Pista.infantil;
				}else if(dificultad.compareTo("familiar")==0){
					dificult=Dificultad_Pista.familiar;
				}else if(dificultad.compareTo("adultos")==0){
					dificult=Dificultad_Pista.adultos;
				}
				numero_karts=Integer.parseInt(BR.readLine());
				
	
				PistaDTO p= new PistaDTO();
				p.setNombre(nombre);
				p.setDisponibilidad(dispo);
				p.setDificultad(dificult);
				p.setNumero_karts(numero_karts);
				list_pistas.add(p);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
		
	}
	/**
     * Clase que se encarga de actualizar el fichero de karts disponibles
	  * */
	public static void actualizar_fichero() {
		try {
			ArrayList<String>ficheros=new ArrayList<String>();
			ficheros=Properties_Fich();
			FileWriter fichero = new FileWriter(ficheros.get(1));
			for(int i=0;i<list_pistas.size();i++) {
				fichero.write(list_pistas.get(i).getNombre()+"\n");
				fichero.write(list_pistas.get(i).isDisponibilidad()+"\n");
				fichero.write(list_pistas.get(i).getDificultad()+"\n");
				fichero.write(list_pistas.get(i).getNumero_karts()+"\n");				
			}
			fichero.close();
		}catch(Exception ex) {ex.printStackTrace();}
	}
	
	
	public static ArrayList<PistaDTO> listado_pistas() {
		// TODO Auto-generated method stub
		return list_pistas;
	}
	/**
     * Clase que se encarga de crear los karts y añadir sus atributos
	 * @throws Exception 
	  * */
	public static void crearKart() throws Exception {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
        KartDTO k;
        k= new KartDTO();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int id_kart;
        String tipo;
        Tipo_Kart tipo1;
        String estado1;
        
		System.out.println("Introduzca el id del kart: ");
		id_kart = Integer.parseInt(reader.readLine());
		if(exist_id_kart(id_kart)==true) {
			System.out.println("Ya existe este id.");
		}else {
			k.setId_kart(id_kart);
	        System.out.println(id_kart);
			System.out.println("Introduzca el tipo de kart (adultos/niños): ");
			tipo = teclado.nextLine();
	        if(tipo.compareTo("adultos")==0) {
	    		k.setTipo(Tipo_Kart.adultos);

	        }else if(tipo.compareTo("niños")==0) {
	        	k.setTipo(Tipo_Kart.children);
	        }
			System.out.println("Introduzca el estado del kart(disponible/reservado/mantenimiento): ");
			estado1 = teclado.nextLine();
			if(estado1.compareTo("disponible")==0) {
				k.setEstado(Estado_Kart.disponible);
			}else if(estado1.compareTo("reservado")==0) {
				k.setEstado(Estado_Kart.reservado);
			}else if(estado1.compareTo("mantenimiento")==0) {
		        k.setEstado(Estado_Kart.mantenimiento);

			}
			System.out.println("Introduzca el nombre de la pista: ");
			String pista=reader.readLine();
			k.setPista(pista);

			PistaDTO p= new PistaDTO();
			p.asociarKarts(k);
	        lista_karts.add(k);
//	        msu.insertar2(k, prop);
		}
		
	}
	/**
     * Clase que se encarga de comprobar que un kart exista
	  * @paragm none
	  * return true si se encuentra, false si no está en la lista
	  * */
	private static boolean exist_id_kart(int id_kart) {
		// TODO Auto-generated method stub
		for(int i=0;i<lista_karts.size();i++) {
			if(lista_karts.get(i).getId_kart()==id_kart) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<String>Properties_Fich() throws FileNotFoundException, IOException{
		Properties p = new Properties();
		ArrayList<String>salida=new ArrayList<String>();
		String path="config.properties";
	
			BufferedReader br = new BufferedReader(new FileReader(new File(path))); 
			p.load(br);
			
			String usuario,pista,reservas,karts;
			usuario=p.getProperty("usuarios");
			pista=p.getProperty("pista");
			reservas=p.getProperty("reservas");
			karts=p.getProperty("karts");
			salida.add(usuario);
			salida.add(pista);
			salida.add(reservas);
			salida.add(karts);
		
			return salida;
		
	}
	
	
	
	public ArrayList<KartDTO> listado_karts() {
		// TODO Auto-generated method stub
		return this.lista_karts;
	}
	public ArrayList<PistaDTO>listado_pistas1(){
		return this.list_pistas;
	}
	
	public static void mostrar_pista(ArrayList<PistaDTO> pista) {
		for(int indice=0;indice<pista.size();indice++) {
			System.out.println(pista.get(indice));
		}
	}

	public static void checkPistasMantenimiento(ArrayList<PistaDTO> pista) {
		// TODO Auto-generated method stub
		for(int indice=0;indice<pista.size();indice++) {
			if(pista.get(indice).isDisponibilidad().equals(Disponibilidad.no_disponible))
			System.out.println(pista.get(indice));
		}
	}

	public static void checkPistasLibres(ArrayList<PistaDTO> pista) {
		// TODO Auto-generated method stub
		for(int indice=0;indice<pista.size();indice++) {
			if(pista.get(indice).isDisponibilidad().equals(Disponibilidad.disponible))
			System.out.println(pista.get(indice));
		}
	}
	/**
     * Clase que se encarga de cargar los karts y asociarlos a las pistas disponibles
     * @throws FileNotFoundException
	  * @throws IOException
	  * */ 
	public static void cargar_karts() throws FileNotFoundException, IOException {
		int id_karts;
		String ids;
		String tipo_kart;
		Tipo_Kart tipo = null;
		String estado1;
		String pista;
		Estado_Kart estado = null;
		ArrayList<String>ficheros=new ArrayList<String>();
		ficheros=Properties_Fich();
		File fich_karts;
		fich_karts=new File(ficheros.get(3));
		if(fich_karts.exists()) {
		try {
			FileReader lector = new FileReader(ficheros.get(3));
			BufferedReader BR = new BufferedReader(lector);
			
			while((ids=BR.readLine())!=null) {
				id_karts=Integer.parseInt(ids);
				tipo_kart=BR.readLine();
				if(tipo_kart.compareTo("adultos")==0) {
						tipo = Tipo_Kart.adultos;
				}else if(tipo_kart.compareTo("niños")==0) {
					tipo = Tipo_Kart.children;
				}
				estado1=BR.readLine();
				if(estado1.compareTo("disponible")==0){
					estado = Estado_Kart.disponible;
				}else if(estado1.compareTo("mantenimiento")==0){
					estado=Estado_Kart.mantenimiento;
				}else if(estado1.compareTo("reservado")==0){
					estado= Estado_Kart.reservado;
				}
				pista=BR.readLine();
				
				PistaDTO p1= new PistaDTO();

				KartDTO p= new KartDTO();
				p.setId_kart(id_karts);
				p.setTipo(tipo);
				p.setEstado(estado);
				p.setPista(pista);
				p1.asociarKarts(p);
				lista_karts.add(p);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
		
	}
	public static void actualizar_fichero_karts() {
		try {
			ArrayList<String>ficheros=new ArrayList<String>();
			ficheros=Properties_Fich();
			FileWriter fichero = new FileWriter(ficheros.get(3));
			for(int i=0;i<lista_karts.size();i++) {
				fichero.write(lista_karts.get(i).getId_kart()+"\n");
				fichero.write(lista_karts.get(i).isTipo()+"\n");
				fichero.write(lista_karts.get(i).getEstado()+"\n");
				}
			fichero.close();
		}catch(Exception ex) {ex.printStackTrace();}
	}
	/**
     * Clase que se encarga de listar los karts disponibles
	 * @param karts 
	  * */
	public static void listar_karts(ArrayList<KartDTO> karts) {
		// TODO Auto-generated method stub
		System.out.println("Introduce la categoria de la pista, de la que desea buscar karts");
		System.out.println("1.- Infantil");
		System.out.println("2.- Adulto");
		System.out.println("3.- Familiar");
		int seleccion=sin.nextInt();
		if(seleccion==1) {
			for(int indice=0;indice<karts.size();indice++) {
				if(karts.get(indice).isTipo().equals(Tipo_Kart.children)) {
				System.out.println(karts.get(indice));
				}
			}
		}else if(seleccion==2) {
			for(int indice=0;indice<karts.size();indice++) {
				if(karts.get(indice).isTipo().equals(Tipo_Kart.adultos)) {
				System.out.println(karts.get(indice));
				}
			}
		}else if(seleccion==3) {
			for(int indice=0;indice<karts.size();indice++) {	
				System.out.println(karts.get(indice));
			}
		}
		
		
	}
	/**
     * Clase que se encarga de buscar una pista según las características que busca el usuario
	 * @param pista 
	  * */

	public static void buscarPista(ArrayList<PistaDTO> pista) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("¿De cuantos karts, estas buscando la pista?");
		int num = Integer.parseInt(reader.readLine());
		System.out.println("¿Que tipo de pista?");
		System.out.println("1.-infantil ");
		System.out.println("2.-familiar ");
		System.out.println("3.-adultos ");
		int seleccion=sin.nextInt();
		if(seleccion==1) {
			for(int indice=0;indice<pista.size();indice++) {
				if(pista.get(indice).getDificultad().equals(Dificultad_Pista.infantil))
					if(pista.get(indice).getNumero_karts() >= num) {
						System.out.println(pista.get(indice));
					}
			}
		}else if(seleccion==2) {
			for(int indice=0;indice<pista.size();indice++) {
				if(pista.get(indice).getDificultad().equals(Dificultad_Pista.familiar))
					if(pista.get(indice).getNumero_karts() >= num) {
						System.out.println(pista.get(indice));
					}			
				}
		}else if(seleccion==3) {
			for(int indice=0;indice<pista.size();indice++) {
				if(pista.get(indice).getDificultad().equals(Dificultad_Pista.adultos))
					if(pista.get(indice).getNumero_karts() >= num) {
						System.out.println(pista.get(indice));
					}
				}
		}
		
	}


}
