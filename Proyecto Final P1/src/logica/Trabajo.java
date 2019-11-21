package logica;

import java.io.Serializable;

public class Trabajo implements Serializable{

	private static final long serialVersionUID = 1L;
	private static int idTrabajo = 0;
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
		idTrabajo++;
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
	public String getNombreTrabajo() {
		return tema;
	}
	public void setNombreTrabajo(String nombreTrabajo) {
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

	public static int getIdTrabajo() {
		return idTrabajo;
	}

	public static void setIdTrabajo(int idTrabajo) {
		Trabajo.idTrabajo = idTrabajo;
	}
	
}
