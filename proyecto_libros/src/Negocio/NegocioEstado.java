package Negocio;

import java.util.List;

import Datos.DatosEstado;
import Entidades.Estado;

public class NegocioEstado {
	
	public List<Estado> getEstados(){

		DatosEstado dEstado = new DatosEstado();
		List<Estado> estados = dEstado.getEstados();
		
		return estados;
		
	}

	public Estado getEstadoPorId(int idEstado) {		
		return new DatosEstado().getEstado(idEstado);
	}

}
