package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Categoria {
	
	
	private int id;
	private String nombre;
	private String detalle;
	private List<Libro> libros;
	
	public Categoria(String nombre, String detalle) {
		
		this.nombre=nombre;
		this.detalle=detalle;
		
	}

	public Categoria(ResultSet rs) throws SQLException {
		this.id = rs.getInt("idcategorias");
		this.nombre = rs.getString("nombre");
		this.detalle = rs.getString("descripcion");
	}

	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id=id;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}
	
	public String getDescripcion(){
		return detalle;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public List<Libro> getLibros() {
		return this.libros;
	}

}
