package Entidades;

import java.util.Date;

public class Precio {
	
	private Date fechaDes;
	private Float precio;
	private int idLibro;
	
	public Precio(Float precio) {
		this.precio = precio;
	}

	public Float getPrecio() {
		return precio;
	}
	
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	public Date getFechaDes() {
		return fechaDes;
	}
	
	public void setFechaDes(Date fechaDes) {
		this.fechaDes = fechaDes;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

}
