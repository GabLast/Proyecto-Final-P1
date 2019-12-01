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
	private String tipo;
	private String lugar;
	private Date fecha;
	private boolean estado;
	
	public Evento(ArrayList<Recurso> recursosUsados, ArrayList<Participante> participantes, String nombre, String id, String tipo, String lugar, Date fecha) {
		super();
		
		this.recursosUsados = recursosUsados;
		this.participantes = participantes;
		this.nombre = nombre;
		this.id = id;
		this.tipo = tipo;
		this.lugar = lugar;
		this.fecha = fecha;
		this.estado = true;
		misComisiones = new ArrayList<Comision>();
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
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

	public void insertarComision(Comision c1) {

		for(Jurado juez : c1.getMiJurado())
		{
			juez.setParticipaciones(juez.getParticipaciones()+1);
		}
		misComisiones.add(c1);
		Empresa.getInstance().getComisiones().add(c1);
		Empresa.getInstance().setGenIDComision(Empresa.getInstance().getComisiones().size());
	}
	
	public void deleteComision(Comision c1) {

		for(Jurado juez : c1.getMiJurado())
		{
			juez.setParticipaciones(juez.getParticipaciones()-1);
		}
		misComisiones.remove(c1);
	}
	
	public void verificarFin() 
	{
		Date actual = new Date();
		if(actual.getTime() > fecha.getTime()) 
		{
			estado = false;
		}
		else
			estado = true;
		
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
	
	public Recurso searchRecursoByTipo (String tipo) {
		Recurso resource = null;
		
		for (Recurso aux : recursosUsados) {
			if (aux.getTipo().equalsIgnoreCase(tipo)) {
				resource = aux;
				
			}
		}
		return resource;
	}
	
	public Trabajo buscandoTrabajoEntreMisParticipantesByName(String id)
	{
		Trabajo job = null;
		
		for(Participante parti : participantes)
		{
			job = parti.buscarTrabajoByName(id);
			if(job != null)
			{
				return job;
			}
		}
		
		return job;
	}

	public int[] cantidadPersonasPorGenero()
	{	
		int hombres = 0;
		int mujeres = 0;
		
		
		for(Participante parti : participantes)
		{
			if(parti.getSexo().equalsIgnoreCase("Masculino"))
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
	
	public int totalTrabajos()
	{
		int total = 0;
		
		for(Comision comi : misComisiones)
		{
			total += comi.getTrabajosParticipantes().size();
		}
		
		return total;
	}

}