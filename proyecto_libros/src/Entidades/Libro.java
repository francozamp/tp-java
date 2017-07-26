package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Negocio.NegocioPrecio;

public class Libro {
	
	private Integer id;
	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private String edicion;
	private String descripcion;
	private String extension;
	private Estado estado;
	private Date fechaAlta;
	private List<Categoria> categorias;
	private Precio precioActual;
	
	private Float puntajePromedio;
	private List<Valoracion> valoracionesList;
	
	@SuppressWarnings("unchecked")
	public Libro(String isbn, String titulo, String autor, String editorial, String edicion, String descripcion, boolean disponible,List<Categoria> list, String extension){
		this.isbn=isbn.replaceAll("\\s+", "");
		this.titulo=titulo;
		this.autor=autor;
		this.editorial=editorial;
		this.edicion=edicion;
		this.descripcion=descripcion;
		this.extension = extension;
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
		this.extension=rs.getString("imagen");
		
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

	public Libro() {
		// TODO Auto-generated constructor stub
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
//		return Constantes.RUTA_IMAGEN_TAPA_LIBRO + this.isbn + this.extension;
		return this.extension;
	}
	
	public Date getFechaAlta(){
		return fechaAlta;
	}

	public void setId(Integer idLibro){
		this.id = idLibro;
	}
	
	public Integer getId() {
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

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	public void setDescripcion(String descripcon) {
		this.descripcion = descripcon;
	}
	
	public String getExtension(){
		return this.extension;
	}
	
	public void setExtension(String extension){
		this.extension = extension;
	}

	public Float getPuntajePromedio() {
		return puntajePromedio;
	}

	public void setPuntajePromedio(Float puntajePromedio) {
		this.puntajePromedio = puntajePromedio;
	}
	
	public int getPuntajeEntero(){
		return Math.round(this.puntajePromedio);
	}

	public List<Valoracion> getValoracionesList() {
		return valoracionesList;
	}

	public void setValoracionesList(List<Valoracion> valoracionesList) {
		this.valoracionesList = valoracionesList;
	}
}
