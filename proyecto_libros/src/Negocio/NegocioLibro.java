package Negocio;

import java.util.List;
import java.util.Set;

import Datos.DatosLibro;
import Entidades.Libro;
import Entidades.Usuario;

public class NegocioLibro {
	
	public Libro guardarLibro(Libro libro){
		
		DatosLibro datosLibro = new DatosLibro();
		
		//Si no tiene id y el isbn ya existe quiere decir que se intenta crear un libro con un isbn repetido
		//entonces devuelve null. Manejarlo de mejor manera para avisar por que no se guarda el libro
		if(libro.getId() == null && datosLibro.existeLibro(libro.getISBN())){
			return null;
		}
			
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

	public List<Libro> getLibroPorTituloYCategoria(String tituloLibro, int idCategoria) {
		return new DatosLibro().getLibroPorTituloYCategoria(tituloLibro,idCategoria);
	}

	public List<Libro> findByDescripcion(String descripcion) {
		return new DatosLibro().findByDescripcion(descripcion);
	}

	public Set<Integer> findIdLibrosPorUsuario(Usuario usuario) {
		return new DatosLibro().findIdLibrosPorUsuario(usuario);
	}

}
