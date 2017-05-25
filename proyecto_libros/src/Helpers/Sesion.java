package Helpers;

import javax.servlet.http.HttpSession;

import Entidades.Carro;
import Entidades.Usuario;
import Negocio.NegocioUsuario;

public class Sesion {
	
	private static HttpSession session;

	public static Usuario getUsuario() {
		
		Usuario usuario = null;
		
		if(session.getAttribute("usuario") != null){
			int idUsuario = ((Usuario)session.getAttribute("usuario")).getId();
			NegocioUsuario negocioUsuario = new NegocioUsuario();
			usuario = negocioUsuario.getUsuarioPorId((int)idUsuario);
		}
		
		return usuario;
	}
	
	public static Carro getCarro(){
		if(session.getAttribute("carro")==null){
			Carro carro = new Carro();
			session.setAttribute("carro", carro);
		}
		
		return (Carro)session.getAttribute("carro");
	}

	public static HttpSession getSession() {
		return session;
	}

	public static void setSession(HttpSession session) {
		Sesion.session = session;
	}

	public static void setCarro(Carro carro) {
		session.setAttribute("carro", carro);
	}
	

}
