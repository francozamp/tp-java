package Entidades;

import java.text.DecimalFormat;

public class LineaPedido {
	
	private int id;
	private int cantidad;
	private Libro libro;
	
	public LineaPedido(Libro libro, int cantidad) {
		this.setLibro(libro);
		this.setCantidad(cantidad);
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public double getMontoLinea(){
		return this.libro.getPrecioActual().getPrecio() * this.cantidad;
	}
	
	public String getMontoLineaView(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(this.getMontoLinea());
	}

}
