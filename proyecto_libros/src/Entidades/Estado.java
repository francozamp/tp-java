package Entidades;

import java.sql.ResultSet;

public class Estado {
	
	private int id;
	private String nombre;
	
	public Estado(int idEstado, String nombreEstado) {
		
		id = idEstado;
		setNombre(nombreEstado);
		
	}

	public Estado(int idEstadoActivo) {
		this.id = idEstadoActivo;
	}

	public Estado(ResultSet rs) {
		
	}

	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
