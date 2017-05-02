package Negocio;

import java.time.LocalDate;
import Datos.DatosUsuario;
import Entidades.Constantes;
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

    public Usuario guardarUsuario(Usuario usuario) {

        DatosUsuario datosUsuario = new DatosUsuario();
        
        usuario.setEstado(Constantes.ID_ESTADO_ACTIVO);
        usuario.setTipoUsuario(Constantes.ID_TIPO_COMUN);
        usuario.setFechaAlta(LocalDate.now());
        
        usuario = datosUsuario.guardarUsuario(usuario);

        return usuario;
    }

}
