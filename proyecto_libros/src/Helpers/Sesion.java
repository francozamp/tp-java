package Helpers;

import javax.servlet.http.HttpSession;

import Entidades.Usuario;
import Negocio.NegocioUsuario;

public class Sesion {

	public static Usuario getUsuario(HttpSession session) {
		
		Usuario usuario = null;
		
		if(session.getAttribute("idUsuario") != null){
			int idUsuario = (int) session.getAttribute("idUsuario");
			NegocioUsuario negocioUsuario = new NegocioUsuario();
			usuario = negocioUsuario.getUsuarioPorId((int)idUsuario);
		}
		
		return usuario;
	}
	

}
