package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Negocio.NegocioPrecio;

public class Libro {
	
	private int id;
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private String edicion;
	private String descripcion;
	private String urlImagen;
	private Estado estado;
	private Date fechaAlta;
	private List<Categoria> categorias;
	private Precio precioActual;
	
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

	public Libro(ResultSet rs) throws SQLException {
		
		this.id=rs.getInt("id");
		this.isbn=rs.getString("ISBN").replaceAll("\\s+", "");
		this.titulo=rs.getString("titulo");
		this.autor=rs.getString("autor");
		this.editorial=rs.getString("editorial");
		this.edicion=rs.getString("edicion");
		this.descripcion=rs.getString("descripcion");
		this.urlImagen=rs.getString("imagen");
		
		boolean disponible = false;
		if (rs.getInt("estados_idestados")==Constantes.ID_ESTADO_ACTIVO) {
			disponible = true;
		}
		
		this.estado=(disponible) ? new Estado(Constantes.ID_ESTADO_ACTIVO, Constantes.NOMBRE_ESTADO_ACTIVO) : new Estado(Constantes.ID_ESTADO_BAJA, Constantes.NOMBRE_ESTADO_BAJA);
		
		this.fechaAlta=rs.getDate("fechaAlta");
		
		categorias =  new ArrayList<>();
		
		this.precioActual = new NegocioPrecio().getPrecioActualPorLibroId(this.id);
		
//		try {
//			this.categorias=(List<Categoria>) list;
//		} catch (Exception e) {
//			System.out.println("Se rompio algo al hacer lo de la categoria");
//		}
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

	public String getUrlImagen() {
		return urlImagen;
	}
	
	public Date getFechaAlta(){
		return fechaAlta;
	}

	public int getId() {
		return id;
	}

	public void addCategoria(Categoria categoria) {
		categorias.add(categoria);
	}

	public Precio getPrecioActual() {
		return this.precioActual;
	}
	
	public String getPrecioView(){
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(this.getPrecioActual().getPrecio());
	}

	public void setPrecioActual(Precio precioActual) {
		this.precioActual = precioActual;
	}
	

}
