package logica;

import java.util.ArrayList;

public class Empresa {

	private static Empresa miEmpresa = null;
	private ArrayList<Persona> personasRegistradas;
	private ArrayList<Recurso> recursos;
	private ArrayList<Evento> eventos;
	
	
	private  Empresa()
	{
		super();
		this.personasRegistradas = new ArrayList();
		this.recursos = new ArrayList();
		this.eventos = new ArrayList();
		
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
	
	public void insertarPersona(Persona person) {

		personasRegistradas.add(person);
	}
	
	public void insertarRecurso(Recurso recurso) {

		recursos.add(recurso);
	}
	
	public void insertarEvento(Evento event) {

		eventos.add(event);
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
	
}
