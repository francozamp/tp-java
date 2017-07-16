package Negocio;

import java.util.List;

import Datos.DatosValoracion;
import Entidades.Usuario;
import Entidades.Valoracion;

public class NegocioValoracion {

	public Valoracion guardarValoracion(Valoracion valoracion) {
		return new DatosValoracion().guardarValoracion(valoracion);
	}

	public List<Valoracion> findByUsuario(Usuario usuario) {
		return new DatosValoracion().findByUsuario(usuario);
	}
	
}
