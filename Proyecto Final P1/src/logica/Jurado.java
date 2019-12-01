package logica;

public class Jurado extends Persona {

	private String areaEstudio;
	private boolean presidente;
	private int participaciones;

	public Jurado(String cedula, String nombre, String telefono, String direccion, String sexo, String gradoAcademico,
			Evento evento, String areaEstudio, boolean presidente) {
		super(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
		// TODO Auto-generated constructor stub
		this.areaEstudio = areaEstudio;
		this.presidente = presidente;
		this.participaciones = 0;
	}
	
	public String getAreaEstudio() {
		return areaEstudio;
	}

	public void setAreaEstudio(String areaEstudio) {
		this.areaEstudio = areaEstudio;
	}

	public boolean isPresidente() {
		return presidente;
	}

	public void setPresidente(boolean presidente) {
		this.presidente = presidente;
	}

	public int getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(int participaciones) {
		this.participaciones = participaciones;
	}

	
}
