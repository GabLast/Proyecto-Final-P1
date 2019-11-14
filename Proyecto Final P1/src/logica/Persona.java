package logica;

public abstract class Persona {

	protected String cedula;
	protected String nombre;
	protected String telefono;
	protected String direccion;
	protected String sexo;
	protected String gradoAcademico;
	protected Evento evento;
	
	public Persona(String cedula, String nombre, String telefono, String direccion, String sexo,
			String gradoAcademico, Evento evento) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.sexo = sexo;
		this.gradoAcademico = gradoAcademico;
		this.evento = evento;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getGradoAcademico() {
		return gradoAcademico;
	}

	public void setGradoAcademico(String gradoAcademico) {
		this.gradoAcademico = gradoAcademico;
	}
	
	
}
