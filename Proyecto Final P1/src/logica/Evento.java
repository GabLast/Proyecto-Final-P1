package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Comision> misComisiones;
	private ArrayList<Recurso> recursosUsados;
	private ArrayList<Participante> participantes;
	private String nombre;
	private String id;
	private Date fecha;
	private boolean estado;
	
	public Evento(ArrayList<Comision> misComisiones, ArrayList<Recurso> recursosUsados,
			ArrayList<Participante> participantes, String nombre, String id, Date fecha) {
		super();
		this.misComisiones = misComisiones; //hacer ventana para crear comision
		this.recursosUsados = recursosUsados; //hacer ventana para elegir recursos a usar
		this.participantes = participantes;
		this.nombre = nombre;
		this.id = id;
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

	public void setEstado(boolean estado) {
		this.estado = estado;
	}	
	
	
	
//	public void insertarComision(Comision c1) {
//
//		misComisiones.add(c1);
//	}
	
	public void verificarFin() 
	{
		Date actual = new Date();
		if(actual.getTime() > fecha.getTime()) 
		{
			estado = false;
		}
		
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
