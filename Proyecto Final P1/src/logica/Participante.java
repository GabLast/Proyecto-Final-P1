package logica;

import java.util.ArrayList;

public class Participante extends Persona {

	private ArrayList<Trabajo> misTrabajos;

	public Participante(String cedula, String nombre, String telefono, String direccion, String sexo,
			String gradoAcademico) {
		super(cedula, nombre, telefono, direccion, sexo, gradoAcademico);
		misTrabajos = new ArrayList<>();
	}

	public ArrayList<Trabajo> getMisTrabajos() {
		return misTrabajos;
	}

	public void setMisTrabajos(ArrayList<Trabajo> misTrabajos) {
		this.misTrabajos = misTrabajos;
	}
	
	public void insertarTrabajo(Trabajo t) {

		misTrabajos.add(t);
		Empresa.getInstance().getTrabajos().add(t);
		Empresa.getInstance().setGenIDTrabajo(Empresa.getInstance().getTrabajos().size());
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
	
	public void deleteTrabajo(Trabajo trabajo) {
		misTrabajos.remove(trabajo);
	}
	
}
