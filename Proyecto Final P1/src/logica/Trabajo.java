package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Trabajo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Participante duenio;
	private String id;
	private String tema;
	private String area;
	private String descripcion;
	
	public Trabajo(Participante duenio, String id, String tema, String area, String descripcion) {
		super();
		this.duenio = duenio;
		this.id = id;
		this.tema = tema;
		this.area = area;
		this.descripcion = descripcion;
	}
	
	public Participante getDuenio() {
		return duenio;
	}
	public void setDuenio(Participante duenio) {
		this.duenio = duenio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String nombreTrabajo) {
		this.tema = nombreTrabajo;
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
