package Negocio;

import java.util.List;

import Datos.DatosTipoUsuario;
import Entidades.TipoUsuario;

public class NegocioTipoUsuario {

	public List<TipoUsuario> getTipoUsuarioList() {
		return new DatosTipoUsuario().getTipoUsuarioList();
	}

	public TipoUsuario getTipoUsuario(int idTipoUsuario) {
		return new DatosTipoUsuario().getTipoUsuario(idTipoUsuario);
	}

	
}
