package Negocio;

import java.time.LocalDate;
import java.util.List;

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
        
        if (usuario.getId() == null && datosUsuario.existeUsuario(usuario.getEmail())) {
			return null;
		}
        
        if(usuario.getEstado() == null){
        	usuario.setEstado(Constantes.ID_ESTADO_ACTIVO);
        }
        if(usuario.getTipoUsuario() == null){
        	usuario.setTipoUsuario(Constantes.ID_TIPO_COMUN);
        }
        if (usuario.getFechaAlta() == null) {
        	usuario.setFechaAlta(LocalDate.now());
		}
        
        usuario = datosUsuario.guardarUsuario(usuario);

        return usuario;
    }

	public List<Usuario> getUsuarios() {
		
		DatosUsuario datosUsuario = new DatosUsuario();
		return datosUsuario.getUsuarios();
	}

	public List<Usuario> findByDescripcion(String descripcion) {
		return new DatosUsuario().findByDescripcion(descripcion);
	}

}
