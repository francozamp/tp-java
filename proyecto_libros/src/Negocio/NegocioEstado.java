package Negocio;

import java.util.List;

import Datos.DatosEstado;
import Entidades.Estado;

public class NegocioEstado {
	
	public List<Estado> getEstados(){
		
		List<Estado> estados = null;
		DatosEstado dEstado = new DatosEstado();
		estados = dEstado.getEstados();
		
		return estados;
		
	}

	public Estado getEstadoPorId(int idEstado) {
		
		return new DatosEstado().getEstado(idEstado);
	}

}
