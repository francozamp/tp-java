package Entidades;

public class Estado {
	
	private int id;
	private String nombre;
	
	public Estado(int idEstado, String nombreEstado) {
		
		id = idEstado;
		nombre = nombreEstado;
		
	}

	public Estado(int idEstadoActivo) {
		this.id = idEstadoActivo;
	}

	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

}
