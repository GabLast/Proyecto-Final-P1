package logica;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
	private static int genIDEvento = 0;
	
	private  Empresa()
	{
		super();
		this.personasRegistradas = new ArrayList();
		this.recursos = new ArrayList();
		this.eventos = new ArrayList();
		this.misUsers = new ArrayList();		
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



}
