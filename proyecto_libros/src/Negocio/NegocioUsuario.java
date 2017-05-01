package Negocio;

import Datos.DatosUsuario;
import Entidades.Usuario;

public class NegocioUsuario {
	
	public Usuario hacerLogin(String email, String pass){
		
		Usuario usu = null;
		
		DatosUsuario dUsuario=new DatosUsuario();
		usu=dUsuario.hacerLogin(email, pass);
		
		return usu;	
	}

	public Usuario getUsuarioPorId(int idUsuario) {
		
		DatosUsuario datosUsuario = new DatosUsuario();
		Usuario usuario =datosUsuario.getUsuarioPorId(idUsuario);
		
		return usuario;
	}

}
