package Negocio;

import java.util.List;

import Datos.DatosValoracion;
import Entidades.Usuario;
import Entidades.Valoracion;
import Helpers.Sesion;

public class NegocioValoracion {

	public Valoracion guardarValoracion(Integer idValoracion,Integer puntaje, String comentario) {
		Valoracion valoracion = this.findById(idValoracion);
		
		if(valoracion != null){
			valoracion.setComentario(comentario);
			valoracion.setPuntaje(puntaje);
		}
		
		return new DatosValoracion().guardarValoracion(valoracion);
	}
	
	public Valoracion guardarValoracion(Valoracion valoracion) {
		return new DatosValoracion().guardarValoracion(valoracion);
	}

	public List<Valoracion> findByUsuario(Usuario usuario) {
		return new DatosValoracion().findByUsuario(usuario);
	}
	
	public List<Valoracion> findByLibro(Integer idLibro) {
		return new DatosValoracion().findByLibro(idLibro);
	}
	
	public Integer getPuntajePromedio(List<Valoracion> valoraciones){
		Integer promedio = 0;
		Integer suma = 0;
		Integer cantidad = 0;
		
		for(Valoracion v : valoraciones){
			Integer puntaje = v.getPuntaje();
			if(puntaje != null){
				suma += puntaje;
				cantidad++;
			}
		}
		
		if(cantidad > 0){
			promedio = Math.round(suma / cantidad);
		}
		
		return promedio;
	}
	
	public Valoracion findById(Integer idValoracion) {
		return new DatosValoracion().findById(idValoracion);
	}
	
	public Float getPromedioByIdLibro(Integer idLibro) {
		return new DatosValoracion().getPromedioByIdLibro(idLibro);
	}
	
	
}
