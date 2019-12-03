package logica;

import java.io.Serializable;

public class RecursoEvento implements Serializable{

	private static final long serialVersionUID = 1L;
	private String tipo;
	private int cantidadUsadaEvento;
	
	public RecursoEvento(String tipo, int cantidadUsadaEvento) {
		super();
		this.tipo = tipo;
		this.cantidadUsadaEvento = cantidadUsadaEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCantidadUsadaEvento() {
		return cantidadUsadaEvento;
	}

	public void setCantidadUsadaEvento(int cantidadUsadaEvento) {
		this.cantidadUsadaEvento = cantidadUsadaEvento;
	}
}
