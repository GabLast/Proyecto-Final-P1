package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;
	private static int genIDComision = 0;
	private static int genIDRecursos = 0;
	private ArrayList<Comision> misComisiones;
	private ArrayList<Recurso> recursosUsados;
	private ArrayList<Participante> participantes;
	private String nombre;
	private String id;
	private String tipo;
	private String lugar;
	private Date fecha;
	private boolean estado;
	
	public Evento(ArrayList<Recurso> recursosUsados, ArrayList<Participante> participantes, String nombre, String tipo, String lugar, String id, Date fecha) {
		super();
		misComisiones = new ArrayList<>();
		this.recursosUsados = recursosUsados;
		this.participantes = participantes;
		this.nombre = nombre;
		this.id = id;
		this.tipo = tipo;
		this.lugar = lugar;
		this.fecha = fecha;
		this.estado = true;
	}

	public ArrayList<Comision> getMisComisiones() {
		return misComisiones;
	}

	public void setMisComisiones(ArrayList<Comision> misComisiones) {
		this.misComisiones = misComisiones;
	}

	public ArrayList<Recurso> getRecursosUsados() {
		return recursosUsados;
	}

	public void setRecursosUsados(ArrayList<Recurso> recursosUsados) {
		this.recursosUsados = recursosUsados;
	}

	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(ArrayList<Participante> participantes) {
		this.participantes = participantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isEstado() {
		return estado;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}	
	
	public static int getGenIDComision() {
		return genIDComision;
	}

	public static void setGenIDComision(int genIDComision) {
		Evento.genIDComision = genIDComision;
	}

	public static int getGenIDRecursos() {
		return genIDRecursos;
	}

	public static void setGenIDRecursos(int genIDRecursos) {
		Evento.genIDRecursos = genIDRecursos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void insertarComision(Comision c1) {

		misComisiones.add(c1);
		genIDComision++;
	}
	
	public void verificarFin() 
	{
		Date actual = new Date();
		if(actual.getTime() > fecha.getTime()) 
		{
			estado = false;
		}
		
	}
	
	public Comision buscarComisionByID (String id) {
		Comision comi = null;
		
		for (Comision aux : misComisiones) {
			if (aux.getId().equalsIgnoreCase(id)) {
				comi = aux;
				
			}
		}
		return comi;
	}
	
	public Trabajo buscandoTrabajoEntreMisParticipantesByName(String id)
	{
		Trabajo job = null;
		
		for(Participante parti : participantes)
		{
			job = parti.buscarTrabajoByName(id);
		}
		
		return job;
	}


}
