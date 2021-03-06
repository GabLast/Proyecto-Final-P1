package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Comision implements Serializable{


	private static final long serialVersionUID = 1L;
	private String id;
	private ArrayList<Jurado> miJurado;
	private Jurado presidente;
	private ArrayList<Trabajo> trabajosParticipantes;
	private String area; 
	private Date fechaCreacion;

	public Comision(String id, Jurado presidente, String area) {
		super();
		this.id = id;
		this.miJurado = new ArrayList<>();
		this.presidente = presidente;
		this.trabajosParticipantes = new ArrayList<>();
		this.area = area;
		this.fechaCreacion = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Jurado> getMiJurado() {
		return miJurado;
	}

	public void setMiJurado(ArrayList<Jurado> miJurado) {
		this.miJurado = miJurado;
	}

	public Jurado getPresidente() {
		return presidente;
	}

	public void setPresidente(Jurado presidente) {
		this.presidente = presidente;
	}

	public ArrayList<Trabajo> getTrabajosParticipantes() {
		return trabajosParticipantes;
	}

	public void setTrabajosParticipantes(ArrayList<Trabajo> trabajosParticipantes) {
		this.trabajosParticipantes = trabajosParticipantes;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String tipo) {
		this.area = tipo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean verificarPresidente()
	{
		boolean hayPresidente = false;

		for(Jurado jur : miJurado)
		{
			if(jur.isPresidente())
			{
				hayPresidente = true;
				return hayPresidente;
			}
		}

		return hayPresidente;
	}

	public void deleteJuez(Jurado juez) {

		boolean nuevoPresidente = false;

		juez.setParticipaciones(juez.getParticipaciones()-1);

		if(juez.isPresidente() && miJurado.size() > 0)
		{

			for(Jurado ju : miJurado)
			{
				if(!nuevoPresidente)
				{
					if(!ju.getCedula().equalsIgnoreCase(juez.getCedula()))
					{
						ju.setPresidente(true);
						presidente = ju;
						nuevoPresidente = true;
					}
				}

			}

		}
		miJurado.remove(juez);
	}

	public Jurado buscarJuezBYID(String id) {
		Jurado persona = null;

		for (Jurado aux : miJurado) {
			if (aux.getCedula().equalsIgnoreCase(id)) 
			{
				persona = aux;
				return persona;
			}
		}
		return persona;

	}

	public Trabajo buscarTrabajoByName(String name) 
	{
		Trabajo job = null;

		for (Trabajo aux : trabajosParticipantes) 
		{
			if (aux.getTema().equalsIgnoreCase(name)) 
			{
				job = aux;
				return job;
			}
		}
		return job;

	}
	
	public boolean participaComision(String id) {
		boolean participa = false;

		for (Jurado aux : miJurado) {
			if (aux.getCedula().equalsIgnoreCase(id)) 
			{
				participa = true;
				return participa;
			}
		}
		return participa;

	}

	public void deleteTrabajo(Trabajo job) {

		trabajosParticipantes.remove(job);
	}
}
