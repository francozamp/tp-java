package Entidades;

import java.util.List;

public class Libro {
	
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private String edicion;
	private String descripcion;
	private Estado estado;
	private List<Categoria> categorias;
	
	@SuppressWarnings("unchecked")
	public Libro(String isbn, String titulo, String autor, String editorial, String edicion, String descripcion, boolean disponible,List<?> list){
		this.isbn=isbn.replaceAll("\\s+", "");
		this.titulo=titulo;
		this.autor=autor;
		this.editorial=editorial;
		this.edicion=edicion;
		this.descripcion=descripcion;
		this.estado=(disponible) ? new Estado(Constantes.ID_ESTADO_ACTIVO, Constantes.NOMBRE_ESTADO_ACTIVO) : new Estado(Constantes.ID_ESTADO_BAJA, Constantes.NOMBRE_ESTADO_BAJA);
		try {
			this.categorias=(List<Categoria>) list;
		} catch (Exception e) {
			System.out.println("Se rompio algo al hacer lo de la categoria");
		}
	}

	public String getISBN() {
		// TODO Auto-generated method stub
		return this.isbn;
	}

	public String getTitulo() {
		// TODO Auto-generated method stub
		return this.titulo;
	}

	public String getAutor() {
		// TODO Auto-generated method stub
		return this.autor;
	}

	public String getEditorial() {
		// TODO Auto-generated method stub
		return this.editorial;
	}

	public String getEdicion() {
		// TODO Auto-generated method stub
		return this.edicion;
	}

	public String getDescripcion() {
		// TODO Auto-generated method stub
		return this.descripcion;
	}

	public int getIdEstado() {
		// TODO Auto-generated method stub
		return this.estado.getID();
	}

	public List<Categoria> getCategorias() {
		// TODO Auto-generated method stub
		return this.categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	

}
