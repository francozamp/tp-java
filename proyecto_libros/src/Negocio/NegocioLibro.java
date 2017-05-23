package Negocio;

import java.util.List;

import Datos.DatosLibro;
import Entidades.Libro;

public class NegocioLibro {
	
	public Libro guardarLibro(Libro libro){
		
		DatosLibro datosLibro = new DatosLibro();
		libro = datosLibro.guardarLibro(libro);
		
		return libro;
	}

	public List<Libro> getRecientes() {
		
		List<Libro> librosRecientes = null;
		DatosLibro datosLibro = new DatosLibro();
		librosRecientes = datosLibro.getRecientes();
		
		return librosRecientes;
	}

	public List<Libro> getLibrosPorCategoria(int idCategoria) {
		
		List<Libro> librosPorCategoria = null;
		DatosLibro datosLibro = new DatosLibro();
		librosPorCategoria = datosLibro.getLibrosPorCategoria(idCategoria);
		
		return librosPorCategoria;
		
	}
	
	public List<Libro> getLibros(){
		
		DatosLibro datosLibro = new DatosLibro();
		List<Libro> libros = datosLibro.getLibros();
		
		return libros;
		
	}

	public Libro getLibroById(int idLibro) {

		DatosLibro datosLibro = new DatosLibro();
		return datosLibro.getLibroById(idLibro);
	}

}
