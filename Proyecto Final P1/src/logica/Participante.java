package logica;

import java.util.ArrayList;

public class Participante extends Persona {

	private ArrayList<Trabajo> misTrabajos;//misma interfaz para registrar a un participante con su trabajo

	public Participante(String cedula, String nombre, String telefono, String direccion, String sexo,
			String gradoAcademico, Evento evento, ArrayList<Trabajo> misTrabajos) {
		super(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
		this.misTrabajos = misTrabajos;
	}

	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	public void setMisTrabajos(ArrayList<Trabajo> misTrabajos) {
		this.misTrabajos = misTrabajos;
	}
	
	public Trabajo buscarTrabajoByName(String id)
	{
		Trabajo aBuscar = null;
		
		for(Trabajo job : misTrabajos)
		{
			if(job.getId().equalsIgnoreCase(id))
			{
				aBuscar = job;
				return aBuscar;
			}
		}
		
		return aBuscar;
	}
	
}
