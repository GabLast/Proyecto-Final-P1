package logica;

import java.io.Serializable;

public class Trabajo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Persona duenio;
	private String id;
	private String nombreTrabajo;
	private String area;
	private String descripcion;
	
	public Trabajo(Persona duenio, String id, String nombreTrabajo, String area, String descripcion) {
		super();
		this.duenio = duenio;
		this.id = id;
		this.nombreTrabajo = nombreTrabajo;
		this.area = area;
		this.descripcion = descripcion;
	}
	
	public Persona getDuenio() {
		return duenio;
	}
	public void setDuenio(Persona duenio) {
		this.duenio = duenio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreTrabajo() {
		return nombreTrabajo;
	}
	public void setNombreTrabajo(String nombreTrabajo) {
		this.nombreTrabajo = nombreTrabajo;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
