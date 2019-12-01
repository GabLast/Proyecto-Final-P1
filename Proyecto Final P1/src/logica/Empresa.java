package logica;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.crypto.Data;


public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<User> misUsers;
	private static User loginUser;
	private static boolean firstTime;
	
	private static Empresa miEmpresa = null;
	private ArrayList<Persona> personasRegistradas;
	private ArrayList<Recurso> recursos;
	private ArrayList<Evento> eventos;
	private ArrayList<Comision> comisiones;
	private ArrayList<Trabajo> trabajos;
	
	private static int genIDEvento;
	private static int genIDComision;
	private static int genIDTrabajo;
	
	private  Empresa()
	{
		super();
		this.personasRegistradas = new ArrayList<>();
		this.recursos = new ArrayList<>();
		this.eventos = new ArrayList<>();
		this.misUsers = new ArrayList<>();	
		this.comisiones = new ArrayList<>();
		this.trabajos = new ArrayList<>();
		genIDEvento = 0;
		genIDComision = 0;
	}
	public static Empresa getInstance() 
	{		
		if (miEmpresa== null) {
			miEmpresa= new Empresa();	
		}
		return miEmpresa;
	}
	public static Empresa getMiEmpresa() {
		return miEmpresa;
	}
	public static void setMiEmpresa(Empresa miEmpresa) {
		Empresa.miEmpresa = miEmpresa;
	}
	public ArrayList<Persona> getPersonasRegistradas() {
		return personasRegistradas;
	}
	public void setPersonasRegistradas(ArrayList<Persona> personasRegistradas) {
		this.personasRegistradas = personasRegistradas;
	}
	public ArrayList<Recurso> getRecursos() {
		return recursos;
	}
	public void setRecursos(ArrayList<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	public ArrayList<Comision> getComisiones() {
		return comisiones;
	}
	public void setComisiones(ArrayList<Comision> comisiones) {
		this.comisiones = comisiones;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}
	
	public static int getGenIDEvento() {
		return genIDEvento;
	}
	public static void setGenIDEvento(int genIDEvento) {
		Empresa.genIDEvento = genIDEvento;
	}
	
	public static int getGenIDComision() {
		return genIDComision;
	}
	public static void setGenIDComision(int genIDComision) {
		Empresa.genIDComision = genIDComision;
	}
	public static int getGenIDTrabajo() {
		return genIDTrabajo;
	}
	public static void setGenIDTrabajo(int genIDTrabajo) {
		Empresa.genIDTrabajo = genIDTrabajo;
	}

	public void insertarPersona(Persona person) {

		personasRegistradas.add(person);
	}
	
	public void insertarRecurso(Recurso recurso) {

		recursos.add(recurso);
	}
	
	public void insertarEvento(Evento event) {

		eventos.add(event);
		genIDEvento++;
	}
	
	public ArrayList<User> getMisUsers() {
		return misUsers;
	}

	public void setMisUsers(ArrayList<User> misUsers) {
		this.misUsers = misUsers;
	}

	public static User getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(User loginUser) {
		Empresa.loginUser = loginUser;
	}

	public ArrayList<Trabajo> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(ArrayList<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}
	public void regUser(User user) {
		misUsers.add(user);

	}

	public static boolean isFirstTime() {
		return firstTime;
	}

	public static void setFirstTime(boolean firstTime) {
		Empresa.firstTime = firstTime;
	}
	
	public boolean confirmLogin(String username, String password) {
		boolean login = false;
		for (User user : misUsers) {
			if(user.getUserName().equalsIgnoreCase(username) && user.getPass().equals(password))
			{
				loginUser = user;
				login = true;
			}
		}
		return login;
	}


	public Persona searchPersonabyCedula (String id) {
		Persona persona = null;
		
		for (Persona aux : personasRegistradas) {
			if (aux.getCedula().equalsIgnoreCase(id)) {
				persona = aux;
			}
			
		}
		return persona;
		
	}
	
	public Jurado buscarJuezByID (String id) {
		Jurado persona = null;
		
		for (Persona aux : personasRegistradas) {
			
			if(aux instanceof Jurado)
			{
				if (aux.getCedula().equalsIgnoreCase(id)) 
				{
					persona = (Jurado) aux;
					return persona;
				}
			}
		}
		return persona;
		
	}
	
	public Jurado buscarJuezByName (String id) {
		Jurado persona = null;
		
		for (Persona aux : personasRegistradas) {
			
			if(aux instanceof Jurado)
			{
				if (aux.getNombre().equalsIgnoreCase(id)) 
				{
					persona = (Jurado) aux;
					return persona;
				}
			}
		}
		return persona;
		
	}
	
	public Participante buscarParticipanteByName (String id) {
		Participante persona = null;
		
		for (Persona aux : personasRegistradas) {
			
			if(aux instanceof Participante)
			{
				if (aux.getNombre().equalsIgnoreCase(id)) 
				{
					persona = (Participante) aux;
					return persona;
				}
			}
		}
		return persona;
		
	}
	
	public Evento searchEventoByID (String id) {
		Evento event = null;
		
		for (Evento aux : eventos) {
			if (aux.getId().equalsIgnoreCase(id)) {
				event = aux;
				
			}
		}
		return event;
	}
	
	public Recurso searchRecursoByTipo (String tipo) {
		Recurso resource = null;
		
		for (Recurso aux : recursos) {
			if (aux.getTipo().equalsIgnoreCase(tipo)) {
				resource = aux;
				
			}
		}
		return resource;
	}
	
	
	public ArrayList<Jurado> buscarJuecesPorArea(String area)
	{
		ArrayList<Jurado> personas = new ArrayList();
		
		
		for(Persona ppl : personasRegistradas)
		{
			if(ppl instanceof Jurado)
			{
				if(((Jurado) ppl).getAreaEstudio().equalsIgnoreCase(area))
				{
					personas.add((Jurado)ppl);
				}
			}
		}
		
		return personas;
	}
	
	public void retornoRecursosDesdeEvento(Recurso eventRec)
	{
		for(Recurso recs : recursos)
		{
			if(recs.getTipo().equalsIgnoreCase(eventRec.getTipo()))
			{
				recs.devolverRecursoTomado(eventRec.getCantUsadaEvento());
			}
		}
	}
	
	public void deleteEvento(Evento event) {
		
		returnAllResourcesBeforeModifyingEvent(event);
		
		eventos.remove(event);
	}
	
	public void returnAllResourcesBeforeModifyingEvent(Evento event) {

		for(Recurso recs : recursos)
		{
			for(Recurso recEvento : event.getRecursosUsados())
			{
				if(recs.getTipo().equalsIgnoreCase(recEvento.getTipo()))
				{
					recs.devolverRecursoTomado(recEvento.getCantUsadaEvento());
				}
			}
		}
	}
	
	public void returnAllResourcesBeforeModifyingEventCANCEL(Evento event) {

		for(Recurso recs : recursos)
		{
			for(Recurso recEvento : event.getRecursosUsados())
			{
				if(recs.getTipo().equalsIgnoreCase(recEvento.getTipo()))
				{
					recs.devolverRecursoTomadoCANCEL(recEvento.getCantUsadaEvento());
				}
			}
		}
	}
	
	public boolean verificarRecursoUnico(String tipo)
	{
		boolean existe = false;
		
		for(Recurso recs : recursos)
		{
			if(recs.getTipo().equalsIgnoreCase(tipo))
			{
				existe = true;
				return existe;
			}
		}
		
		return existe;
	}
	
	public Comision searchComisionByID (String id) {
		Comision comi = null;
		
		for (Comision aux : comisiones) {
			if (aux.getId().equalsIgnoreCase(id)) {
				comi = aux;
				
			}
		}
		return comi;
	}



	public void deleteComision(Comision comi) {
		comisiones.remove(comi);
	}



	public void deleteRecurso(Recurso rec) {
		recursos.remove(rec);
	}




	public void deletePersona(Persona persona) {
		personasRegistradas.remove(persona);
	}


	public boolean verificarCedulaUnica (String id) {
		boolean existe = false;

		for (Persona aux : personasRegistradas ) {
			if (aux.getCedula().equalsIgnoreCase(id)) {
				existe = true;
				return existe;
			}
		}
		return existe;
	}
	
	public int[] cantidadTrabajosPorArea()
	{	
		int mate = 0;
		int quimi = 0;
		int bio = 0;
		int hist = 0;
		int fisi = 0;
		int ing = 0;
		int i;
		
		
		for(Persona parti : personasRegistradas)
		{
			if(parti instanceof Participante)
			{	
				for(i = 0; i < ((Participante)parti).getMisTrabajos().size(); i++)
				{
					if(((Participante) parti).getMisTrabajos().get(i).getArea().equalsIgnoreCase("Matemáticas"))
					{
						++mate;
					}
					if(((Participante) parti).getMisTrabajos().get(i).getArea().equalsIgnoreCase("Química"))
					{
						++quimi;
					}
					if(((Participante) parti).getMisTrabajos().get(i).getArea().equalsIgnoreCase("Biología"))
					{
						++bio;
					}
					if(((Participante) parti).getMisTrabajos().get(i).getArea().equalsIgnoreCase("Historia"))
					{
						++hist;
					}
					if(((Participante) parti).getMisTrabajos().get(i).getArea().equalsIgnoreCase("Física"))
					{
						++fisi;
					}
					if(((Participante) parti).getMisTrabajos().get(i).getArea().equalsIgnoreCase("Ingeniería"))
					{
						++ing;
					}
				}
			}
		}
		
		int[] totales = {mate, quimi, bio, hist, fisi, ing};
		
		return totales;
	}
	
	public  void insertionSort() 
	{
	    for (int i = 1; i < eventos.size(); i++) 
	    {
	        Evento current = eventos.get(i);
	        
	        int j = i - 1;
	       
	        while(j >= 0 && current.totalTrabajos() > eventos.get(j).totalTrabajos()) 
	        {
	            eventos.set(j+1, eventos.get(j));
	            j--;
	        }
	        
	        eventos.set(j+1, current);
	    }
	}
	
	public  void insertionSortJueces(ArrayList<Jurado> juecesTodos) 
	{
	    for (int i = 1; i < juecesTodos.size(); i++) 
	    {
	        Jurado current = juecesTodos.get(i);
	        
	        int j = i - 1;
	       
	        while(j >= 0 && current.getParticipaciones() > juecesTodos.get(j).getParticipaciones()) 
	        {
	        	juecesTodos.set(j+1, juecesTodos.get(j));
	            j--;
	        }
	        
	        juecesTodos.set(j+1, current);
	    }
	}
	
	public ArrayList<Jurado> juecesMasPopulares()
	{
		ArrayList<Jurado> juecesPop = new ArrayList<>();
		
		
		for(Persona juez : personasRegistradas)
		{
			if(juez instanceof Jurado)
			{
				juecesPop.add((Jurado) juez);
			}
		}
		
		insertionSortJueces(juecesPop);
		
		return juecesPop;
	}
	
	public int[] cantidadPersonasRegistradasPorGenero()
	{	
		int hombres = 0;
		int mujeres = 0;
		
		
		for(Persona persona : personasRegistradas)
		{
			if(persona.getSexo().equalsIgnoreCase("Masculino"))
			{
				hombres++;
			}
			else
			{
				mujeres++;
			}
		}
		
		int[] totales = {hombres, mujeres};
		
		return totales;
	}

}

//public ArrayList<Evento> eventosMasPopularesRespectoTrabajos()
//{
//	ArrayList<Evento> evePopulares = new ArrayList<>();
//	
//	int i = 4;
//	
//	for(Evento event : eventos)
//	{
//		if(event.totalTrabajos() > evePopulares.get(i).totalTrabajos())
//		{
//			evePopulares.add(i+1, event);
//			evePopulares.add(i, event);
//		}
//		i--;
//		if(i < 0)
//			i = 4;
//	}
//	
//	return evePopulares;
//}

//public ArrayList<Jurado> juecesMasPopulares()
//{
//	ArrayList<Jurado> juecesPop = new ArrayList<>();
//	ArrayList<Integer> cantidades = new ArrayList<>();
//	int i = 4;
//	int participaciones = 0;
//	
//	for(Persona juez : personasRegistradas)
//	{
//		if(juez instanceof Jurado)
//		{
//			for(Comision comiPertenece : comisiones)
//			{
//				if(comiPertenece.participaComision(juez.getCedula()))
//				{
//					participaciones++;
//				}
//			}
//			
//			((Jurado) juez).setParticipaciones(participaciones);
//			
//			if(cantidades.get(i) < participaciones)
//			{
//				cantidades.add(i+1, cantidades.get(i));
//				juecesPop.add(i+1, juecesPop.get(i));
//				cantidades.add(i, participaciones);
//				juecesPop.add(i, (Jurado) juez);
//			}
//			i--;
//			
//			if(i < 0)
//				i = 4;
//		}
//	}
//	if(juecesPop.size() == 0)
//		return null;
//	return juecesPop;
//}
