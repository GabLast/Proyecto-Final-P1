package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<User> misUsers;
	private static User loginUser;
	private static boolean firstTime;
	
	private static Empresa miEmpresa = null;
	private ArrayList<Persona> personasRegistradas;
	private ArrayList<Recurso> recursos;
	private ArrayList<Evento> eventos;
	private ArrayList<Trabajo> trabajos;
	
	
	private  Empresa()
	{
		super();
		this.personasRegistradas = new ArrayList();
		this.recursos = new ArrayList();
		this.eventos = new ArrayList();
		this.misUsers = new ArrayList();
		this.trabajos = new ArrayList();
		
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
	
	public ArrayList<Trabajo> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(ArrayList<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}
	
	public void insertarPersona(Persona person) {

		personasRegistradas.add(person);
	}
	
	public void insertarRecurso(Recurso recurso) {

		recursos.add(recurso);
	}
	
	public void insertarEvento(Evento event) {

		eventos.add(event);
	}
	
	public void insertarTrabajo(Trabajo job) {

		trabajos.add(job);
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
			if(user.getUserName().equalsIgnoreCase(username) && user.getPass().equals(password)){
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
	
}
