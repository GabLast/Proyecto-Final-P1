package logica;

public class Jurado extends Persona {

	private String areaEstudio;
	private boolean presidente;
	
	public Jurado(String cedula, String nombre, String telefono, String direccion, String sexo, String gradoAcademico,
			String areaEstudio, boolean presidente) {
		super(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
		
		this.areaEstudio = areaEstudio;
		this.presidente = presidente;
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

	
}
