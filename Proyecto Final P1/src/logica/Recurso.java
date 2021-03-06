package logica;

import java.io.Serializable;

public class Recurso implements Serializable{

	private static final long serialVersionUID = 1L;
	private String tipo;
	private int cantidad;

	
	public Recurso(String tipo, int cantidad) {
		super();
		this.tipo = tipo;
		this.cantidad = cantidad;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public boolean verificarDisponibilidad(int cantidadToUse)
	{
		boolean disponible = false;
		
		if(cantidad >= cantidadToUse)
		{
			disponible = true;
			cantidad -= cantidadToUse;
		}
		
		return disponible;
	}
	

	public void devolverRecursoTomado(int usado)
	{
		cantidad += usado;
	}
	
	public void devolverRecursoTomadoCANCEL(int usado)
	{
		cantidad -= usado;
	}
}
