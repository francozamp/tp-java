package Helpers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import Entidades.Carro;
import Entidades.Descuento;
import Entidades.Pedido;
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

	public static void vaciarCarro() {
		session.setAttribute("carro", null);
	}

	public static void setPedido(Pedido pedido) {
		session.setAttribute("pedido", pedido);
	}

	public static Pedido getPedido() {
		return (Pedido)session.getAttribute("pedido");
	}

	public static void removePedido() {
		session.removeAttribute("pedido");
	}

	public static void agregarDireccion(Map<String, String> direccionCompleta) {
		session.setAttribute("direccionCompleta", direccionCompleta);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> getDireccionCompleta() {
		return (Map<String, String>) session.getAttribute("direccionCompleta");
	}

	public static void removerDireccionCompleta() {
		session.removeAttribute("direccionCompleta");
	}
	
	public static void setDescuento(Descuento descuento){
		session.setAttribute("descuento", descuento);
	}
	
	public static Descuento getDescuento(){
		return (Descuento)session.getAttribute("descuento");
	}
	
	public static void removeDescuento(){
		session.removeAttribute("descuento");
	}
	

}
