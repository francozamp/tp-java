package Negocio;

import Datos.DatosPrecio;
import Entidades.Precio;

public class NegocioPrecio {

	public Precio getPrecioActualPorLibroId(int idLibro) {
		return new DatosPrecio().getPrecioActualPorLibroId(idLibro);
	}

}
