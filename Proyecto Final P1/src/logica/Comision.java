package logica;

import java.util.ArrayList;
import java.util.Date;

public class Comision {

	private String id;
	private ArrayList<Jurado> miJurado;
	private Jurado presidente;
	private ArrayList<Trabajo> trabajosParticipantes;
	private String area; //investigacion y practica
	private Date fechaCreacion;

	public Comision(ArrayList<Jurado> miJurado, Jurado presidente, ArrayList<Trabajo> trabajosParticipantes,
			String area) {
		super();
		this.miJurado = miJurado; //max es 4
		this.presidente = presidente;
//		for(Jurado uno : miJurado)
//		{
//			if(uno.isPresidente())
//			{
//				this.presidente = presidente;
//			}
//		}
		this.trabajosParticipantes = trabajosParticipantes;
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

}
