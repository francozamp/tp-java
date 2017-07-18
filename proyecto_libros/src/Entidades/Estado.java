package Entidades;

public class Estado {
	
	private int id;
	private String nombre;
	
	public Estado(int idEstado, String nombreEstado) {
		
		this.id = idEstado;
		this.nombre = nombreEstado;
		
	}

	public Estado(int idEstadoActivo) {
		this.id = idEstadoActivo;
	}

	public int getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
