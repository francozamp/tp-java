package Negocio;

import Datos.DatosLibro;
import Entidades.Libro;

public class NegocioLibro {
	
	public Libro guardarLibro(Libro libro){
		
		DatosLibro datosLibro = new DatosLibro();
		libro = datosLibro.guardarLibro(libro);
		
		return libro;
	}

}
