package Negocio;

import java.util.List;

import Datos.DatosCategoria;
import Entidades.Categoria;

public class NegocioCategoria {

	public Categoria guardarCategoria(String nombre, String detalle) {
		
		Categoria categoria = new Categoria(nombre, detalle);
		DatosCategoria dCategoria = new DatosCategoria();
		
		categoria = dCategoria.guardarCategoria(categoria);
		
		return categoria;
	}

	public List<Categoria> getCategorias() {
		
		List<Categoria> categorias=null;
		
		DatosCategoria dCategoria = new DatosCategoria();
		categorias=dCategoria.getCategorias();
		
		return categorias;
	}

	public Categoria guardarCategoria(int id, String nombre, String descripcion) {
		
		Categoria categoria = new Categoria(nombre, descripcion);
		categoria.setId(id);
		DatosCategoria dCategoria = new DatosCategoria();
		
		categoria = dCategoria.guardarCategoria(categoria);
		
		return categoria;
	}

	public Categoria getCategoria(int idCategoria) {
		
		DatosCategoria datosCategoria = new DatosCategoria();
		Categoria categoria = datosCategoria.getCategoria(idCategoria);
		
		return categoria;
		
	}

	public List<Categoria> findByDescripcion(String descripcion) {
		return new DatosCategoria().findByDescripcion(descripcion);
	}

}
