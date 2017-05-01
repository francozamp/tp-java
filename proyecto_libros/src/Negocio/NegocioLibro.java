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

}
